import java.awt.Label;
import java.awt.Panel;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import com.funnyChat.plugin.PluginAdapter;
import com.funnyChat.db.FriendDAO;
import com.funnyChat.db.PluginInfo;
import com.funnyChat.db.PluginInfoDAO;
import com.funnyChat.db.UserInfo;
import com.funnyChat.db.UserInfoDAO;
import com.funnyChat.event.*;

public class Server extends PluginAdapter{
	private String pluginBasePath = "plugin/";
	private Date hourDate = new Date();
	private Date dayDate = new Date();
	private HashMap<Long,UserInfo> users = new HashMap<Long, UserInfo>();
	private int hourEventCount = 0;
	private int dayEventCount = 0;
	@Override
	public void onCreate() {}

	@Override
	protected void onDestroy() {}

	@Override
	protected void onEnable() {}

	public void refreshPanel(){
		mPanel = new Panel();
		Label label1 = new Label("当前在线人数:  "+users.size());
		mPanel.add(label1);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		Label label2 = new Label("目前注册人数:  "+userInfoDAO.getCount());
		mPanel.add(label2);
		Label label3 = new Label("本日事件量:  "+dayEventCount);
		mPanel.add(label3);
		Label label4 = new Label("本小时事件量:  "+hourEventCount);
		mPanel.add(label4);
		Date date = new Date();
		if(date.getDay()!=dayDate.getDay()){
			dayDate = date;
			dayEventCount = 0;
		}
		if(date.getHours()!=hourDate.getHours()){
			hourDate = date;
			hourEventCount = 0;
		}
	}
	@Override
	protected void execute() {
		//启动线程执行传过来的事件
		hourEventCount++;
		dayEventCount++;
		EventManager eventManager = EventManager.getInstance();
		if(mEvent instanceof AddFriendEvent){
			AddFriendEvent temp = (AddFriendEvent)mEvent;
			boolean result = addFriend(temp.getUId1(), temp.getUId2());
			AddFriendResponseEvent response = new AddFriendResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof ChangeUserStateEvent){
        	ChangeUserStateEvent temp = (ChangeUserStateEvent)mEvent;
        	boolean result = sendUserStateChange(temp.getUId(), temp.getState());
        	ChangeUserStateResponseEvent response = new ChangeUserStateResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof CheckLoginInfoEvent){
        	CheckLoginInfoEvent temp = (CheckLoginInfoEvent)mEvent;
        	boolean result = checkLoginInfo(temp.getName(), temp.getPassword());
        	CheckLoginInfoResponseEvent response = new CheckLoginInfoResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof ConnectedEvent){
        	ConnectedEvent temp = (ConnectedEvent)mEvent;
		}
        if(mEvent instanceof DeleteFriendEvent){
        	DeleteFriendEvent temp = (DeleteFriendEvent)mEvent;
        	boolean result = deleteFriend(temp.getUId1(), temp.getUId2());
        	DeleteFriendResponseEvent response = new DeleteFriendResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof GetFriendsEvent){
			GetFriendsEvent temp = (GetFriendsEvent) mEvent;
			List<UserInfo> result = getFriends(temp.getUId());
			GetFriendsResponseEvent response = new GetFriendsResponseEvent();
			response.setUsersInfo(result);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof GetIpAndPortEvent){
        	GetIpAndPortEvent temp = (GetIpAndPortEvent)mEvent;
        	String result = getIpAndPort(temp.getUId());
        	GetIpAndPortResponseEvent response = new GetIpAndPortResponseEvent();
        	response.setIpAndPort(result);
        	response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
        	eventManager.enqueue(response);
		}
        if(mEvent instanceof GetPluginListEvent){
        	GetPluginListEvent temp = (GetPluginListEvent)mEvent;
        	GetPluginListResponseEvent response = new GetPluginListResponseEvent();
        	response.setPluginsInfo(getPluginList());
        	response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
        	eventManager.enqueue(response);
		}
        if(mEvent instanceof RefreshUserInfoEvent){
        	RefreshUserInfoEvent temp = (RefreshUserInfoEvent)mEvent;
        	boolean result = refreshUserInfo(temp.getUserInfo());
        	RefreshUserInfoResponseEvent response = new RefreshUserInfoResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof RegisterEvent){
        	RegisterEvent temp = (RegisterEvent)mEvent;
        	UserInfo userInfo = new UserInfo();
        	userInfo.setIp(temp.getIp());
        	userInfo.setName(temp.getName());
        	userInfo.setPassword(temp.getPassword());
        	userInfo.setPort("WTF");
        	userInfo.setState("FFS");
        	boolean result = register(userInfo);
        	RegisterResponseEvent response = new RegisterResponseEvent();
			if(result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
        if(mEvent instanceof ReleasePluginEvent){
        	ReleasePluginEvent temp = (ReleasePluginEvent) mEvent;
			Date date = new Date();
			String path = pluginBasePath + date.getTime() + ".plugin";
			boolean result = false;
			try {
				File file = new File(path);
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(file));
				out.write(temp.getPlugins(), 0, temp.getPlugins().length);
				out.flush();
				out.close();
				PluginInfo pluginInfo = temp.getPluginInfo();
				pluginInfo.setPath(path);
				result = realeasePlugin(pluginInfo);
			} catch (Exception ex) {
				result = false;
			}
			ReleasePluginResponseEvent response = new ReleasePluginResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
	}
	//
	@Override
	protected void onDisable() {}

    public String getIpAndPort(long _uid){
    	String result = "";
    	try{
    		UserInfo userInfo = users.get(_uid);
    		result = userInfo.getIp()+" "+userInfo.getPort();
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    	return result;
    }
    public List<PluginInfo>getPluginList(){
    	PluginInfoDAO pluginInfo = new PluginInfoDAO();
    	return pluginInfo.find();
    }
    public boolean realeasePlugin(PluginInfo _plugin){
    	PluginInfoDAO pluginInfoDAO = new PluginInfoDAO();
    	return pluginInfoDAO.add(_plugin);
    }
    public boolean addFriend(long uid1, long uid2){
    	return false;
    }
    public List<UserInfo> getFriends(long _uid){
    	FriendDAO friendDAO = new FriendDAO();
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	List<Long> friends = friendDAO.find(_uid);
    	List<UserInfo> friendInfo = new ArrayList<UserInfo>();
    	for(int i=0;i<friends.size();i++)
    		friendInfo.add(userInfoDAO.find(friends.get(i)));
    	return friendInfo;
    }
    public boolean deleteFriend(long _uid1, long _uid2){
    	FriendDAO friendDAO = new FriendDAO();
        return friendDAO.delete(_uid1, _uid2);
    }
    public boolean sendUserStateChange(Long _uid, String state){
    	try{
    		users.get(_uid).setState(state);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return false;
    }
    public boolean checkLoginInfo(String _name, String _password){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	UserInfo userInfo = userInfoDAO.find(_name);
    	if(userInfo != null && userInfo.getPassword().equals(_password))
    		return true;
    	return false;
    }
    public boolean refreshUserInfo(UserInfo userInfo){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	userInfoDAO.update(userInfo);
    	users.put(userInfo.getUid(), userInfo);
    	return true;
    }
    public void checkAndwer(){
    	
    }
    public boolean register(UserInfo userInfo){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	if(userInfoDAO.find(userInfo.getName())!=null){
    		return false;
    	}
    	boolean succ = userInfoDAO.add(userInfo);
    	if(succ){
    		users.put(userInfo.getUid(), userInfo);
    		return true;
    	}
    	return false;
    }
    @Override
    public String getPluginName() {
	return "FunnyChat Server";
    }
}
