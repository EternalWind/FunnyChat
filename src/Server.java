import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.funnyChat.core.Core;
import com.funnyChat.db.FriendDAO;
import com.funnyChat.db.PluginInfo;
import com.funnyChat.db.PluginInfoDAO;
import com.funnyChat.db.UserInfo;
import com.funnyChat.db.UserInfoDAO;
import com.funnyChat.event.AddFriendEvent;
import com.funnyChat.event.AddFriendResponseEvent;
import com.funnyChat.event.ChangeUserStateEvent;
import com.funnyChat.event.ChangeUserStateResponseEvent;
import com.funnyChat.event.ChatEvent;
import com.funnyChat.event.CheckLoginInfoEvent;
import com.funnyChat.event.CheckLoginInfoResponseEvent;
import com.funnyChat.event.ConnectedEvent;
import com.funnyChat.event.ConnectionFailedEvent;
import com.funnyChat.event.DeleteFriendEvent;
import com.funnyChat.event.DeleteFriendResponseEvent;
import com.funnyChat.event.Event;
import com.funnyChat.event.EventManager;
import com.funnyChat.event.GetFriendsEvent;
import com.funnyChat.event.GetFriendsResponseEvent;
import com.funnyChat.event.GetIpAndPortEvent;
import com.funnyChat.event.GetIpAndPortResponseEvent;
import com.funnyChat.event.GetPasswordEvent;
import com.funnyChat.event.GetPasswordResponseEvent;
import com.funnyChat.event.GetPluginListEvent;
import com.funnyChat.event.GetPluginListResponseEvent;
import com.funnyChat.event.RefreshUserInfoEvent;
import com.funnyChat.event.RefreshUserInfoResponseEvent;
import com.funnyChat.event.RegisterEvent;
import com.funnyChat.event.RegisterResponseEvent;
import com.funnyChat.event.ReleasePluginEvent;
import com.funnyChat.event.ReleasePluginResponseEvent;
import com.funnyChat.network.Connection;
import com.funnyChat.plugin.PluginAdapter;

public class Server extends PluginAdapter {
	private String pluginBasePath = "plugin/";
	private Date hourDate ;
	private Date dayDate ;
	private HashMap<Long, UserInfo> users;
	private int hourEventCount = 0;
	private int dayEventCount = 0;

	@Override
	public void onCreate() {
		hourDate = new Date();
		dayDate = new Date();
		mPanel = new ServerPanel();
		mPanel.setSize(400, 200);
		
		users = new HashMap<Long, UserInfo>();
		
		EventManager.getInstance().register(new CheckLoginInfoEvent());
		EventManager.getInstance().register(new ConnectionFailedEvent());
		EventManager.getInstance().register(new CheckLoginInfoEvent());
		EventManager.getInstance().register(new GetFriendsResponseEvent());
		EventManager.getInstance().register(new ChangeUserStateResponseEvent());
		EventManager.getInstance().register(new CheckLoginInfoResponseEvent());
		EventManager.getInstance().register(new RegisterResponseEvent());
		EventManager.getInstance().register(new AddFriendResponseEvent());
		EventManager.getInstance().register(new ChatEvent());
		EventManager.getInstance().register(new DeleteFriendResponseEvent());
		EventManager.getInstance().register(new RefreshUserInfoResponseEvent());
		EventManager.getInstance().register(new GetPasswordResponseEvent());
		EventManager.getInstance().register(new ConnectionFailedEvent());

		EventManager.getInstance().register(new GetFriendsEvent());
		EventManager.getInstance().register(new ChangeUserStateEvent());
		EventManager.getInstance().register(new CheckLoginInfoEvent());
		EventManager.getInstance().register(new RegisterEvent());
		EventManager.getInstance().register(new AddFriendEvent());
		EventManager.getInstance().register(new DeleteFriendEvent());
		EventManager.getInstance().register(new RefreshUserInfoEvent());
		EventManager.getInstance().register(new GetPasswordEvent());
	}

	@Override
	protected void onDestroy() {
	}

	@Override
	protected void onEnable() {
		Core.getInstance().getMainWindow()
		.registerPanel("server", mPanel);
refreshPanel();
	}

	public void refreshPanel() {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		((ServerPanel)mPanel).refreshPanel(users.size(), userInfoDAO.getCount(),
				dayEventCount, dayEventCount);
		Date date = new Date();
		if (date.getDay() != dayDate.getDay()) {
			dayDate = date;
			dayEventCount = 0;
		}
		if (date.getHours() != hourDate.getHours()) {
			hourDate = date;
			hourEventCount = 0;
		}
		mPanel.validate();
	}

	@Override
	protected boolean isInterested(Event _event) {
		String type = _event.getEventType();
		if(type.equals("DisconnectedEvent") || type.equals("AddFriendEvent") || 
				type.equals("ChangeUserStateEvent") || type.equals("CheckLoginInfoEvent") ||
				type.equals("ConnectedEvent") || type.equals("DeleteFriendEvent") ||
				type.equals("GetFriendsEvent") || type.equals("GetIpAndPortEvent") ||
				type.equals("GetPluginListEvent") || type.equals("RefreshUserInfoEvent") ||
				type.equals("RegisterEvent") || type.equals("ReleasePluginEvent")) {
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void execute() {
		// 启动线程执行传过来的事件
		if (!hasWork()) {
			return;
		}
		System.out.println(mEvent.getEventType());
		hourEventCount++;
		dayEventCount++;
		EventManager eventManager = EventManager.getInstance();
		if(mEvent.getEventType().equals("DisconnectedEvent")) {
			Set<Entry<Long, UserInfo>> u = users.entrySet();
			Iterator<Entry<Long, UserInfo>> iter = u.iterator();
			while(iter.hasNext()) {
				Entry<Long, UserInfo> e = iter.next();
				String ip = "/" + e.getValue().getIp();
				String ip2 = mEvent.getSource().getIP().toString();
				if(ip.equals(ip2)) {
					users.remove(e.getKey());
					this.refreshPanel();
					break;
				}
			}
		}
		if (mEvent instanceof AddFriendEvent) {
			AddFriendEvent temp = (AddFriendEvent) mEvent;
			boolean result = addFriend(temp.getUId1(), temp.getUId2());
			AddFriendResponseEvent response = new AddFriendResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof ChangeUserStateEvent) {
			ChangeUserStateEvent temp = (ChangeUserStateEvent) mEvent;
			boolean result = sendUserStateChange(temp.getUId(), temp.getState());
			ChangeUserStateResponseEvent response = new ChangeUserStateResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof CheckLoginInfoEvent) {
			CheckLoginInfoEvent temp = (CheckLoginInfoEvent) mEvent;
			UserInfo userInfo = new UserInfo();
			long uid = checkLoginInfo(temp.getName(), temp.getPassword(), temp
					.getSource(), temp.getPort());
			CheckLoginInfoResponseEvent response = new CheckLoginInfoResponseEvent();
			if (uid == -1) {
				response.setResult("Failed");
				response.setUid(String.valueOf(-1));
			} else {
				response.setResult("Succeed");
				response.setUid(String.valueOf(uid));
			}
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof ConnectedEvent) {
			ConnectedEvent temp = (ConnectedEvent) mEvent;
		}
		if (mEvent instanceof DeleteFriendEvent) {
			DeleteFriendEvent temp = (DeleteFriendEvent) mEvent;
			boolean result = deleteFriend(temp.getUId1(), temp.getUId2());
			DeleteFriendResponseEvent response = new DeleteFriendResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof GetFriendsEvent) {
			GetFriendsEvent temp = (GetFriendsEvent) mEvent;
			List<UserInfo> result = getFriends(temp.getUId());
			GetFriendsResponseEvent response = new GetFriendsResponseEvent();
			response.setUsersInfo(result);
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof GetIpAndPortEvent) {
			GetIpAndPortEvent temp = (GetIpAndPortEvent) mEvent;
			String result = getIpAndPort(temp.getUId());
			GetIpAndPortResponseEvent response = new GetIpAndPortResponseEvent();
			response.setIsLocal(false);
			response.setIpAndPort(result);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof GetPluginListEvent) {
			GetPluginListEvent temp = (GetPluginListEvent) mEvent;
			GetPluginListResponseEvent response = new GetPluginListResponseEvent();
			response.setPluginsInfo(getPluginList());
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof RefreshUserInfoEvent) {
			RefreshUserInfoEvent temp = (RefreshUserInfoEvent) mEvent;
			boolean result = refreshUserInfo(temp.getUserInfo());
			RefreshUserInfoResponseEvent response = new RefreshUserInfoResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof RegisterEvent) {
			RegisterEvent temp = (RegisterEvent) mEvent;
			UserInfo userInfo = new UserInfo();
			userInfo.setIp(temp.getIp());
			userInfo.setName(temp.getName());
			userInfo.setPassword(temp.getPassword());
			userInfo.setPort("WTF");
			userInfo.setState("FFS");
			boolean result = register(userInfo);
			RegisterResponseEvent response = new RegisterResponseEvent();
			if (result)
				response.setResult("Succeed");
			else
				response.setResult("Failed");
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		if (mEvent instanceof ReleasePluginEvent) {
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
			response.setIsLocal(false);
			response.setTarget(temp.getSource());
			response.setSource(temp.getTarget());
			eventManager.enqueue(response);
		}
		refreshPanel();
		this.doneWork();
	}

	//
	@Override
	protected void onDisable() {
	}

	public String getIpAndPort(long _uid) {
		String result = "";
		try {
			UserInfo userInfo = users.get(_uid);
			result = userInfo.getIp() + " " + userInfo.getPort();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return result;
	}

	public List<PluginInfo> getPluginList() {
		PluginInfoDAO pluginInfo = new PluginInfoDAO();
		return pluginInfo.find();
	}

	public boolean realeasePlugin(PluginInfo _plugin) {
		PluginInfoDAO pluginInfoDAO = new PluginInfoDAO();
		return pluginInfoDAO.add(_plugin);
	}

	public boolean addFriend(long uid1, long uid2) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if (userInfoDAO.find(uid1) == null || userInfoDAO.find(uid2) == null)
			return false;
		FriendDAO friendDAO = new FriendDAO();
		return friendDAO.add(uid1, uid2);
	}

	public List<UserInfo> getFriends(long _uid) {
		FriendDAO friendDAO = new FriendDAO();
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		List<Long> friends = friendDAO.find(_uid);
		List<UserInfo> friendInfo = new ArrayList<UserInfo>();
		for (int i = 0; i < friends.size(); i++) {
			UserInfo userInfoDB = userInfoDAO.find(friends.get(i));
			UserInfo userInfoME = users.get(userInfoDB.getUid());
			if (userInfoME != null) {
				userInfoDB.setIp(userInfoME.getIp());
				userInfoDB.setPort(userInfoME.getPort());
				userInfoDB.setState(userInfoME.getState());
			} else {
				userInfoDB.setState("离线");
			}
			friendInfo.add(userInfoDB);
		}
		return friendInfo;
	}

	public boolean deleteFriend(long _uid1, long _uid2) {
		FriendDAO friendDAO = new FriendDAO();
		return friendDAO.delete(_uid1, _uid2);
	}

	public boolean sendUserStateChange(Long _uid, String state) {
		try {
			users.get(_uid).setState(state);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public long checkLoginInfo(String _name, String _password,
			Connection source, String port) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo userInfo = userInfoDAO.find(_name);
		if (userInfo != null && userInfo.getPassword()!=null && userInfo.getPassword().equals(_password)) {
			userInfo.setIp(source.getIP().toString().substring(1));
			userInfo.setPort(String.valueOf(port));
			users.put(userInfo.getUid(), userInfo);
			return userInfo.getUid();
		}
		return -1;
	}

	public boolean refreshUserInfo(UserInfo userInfo) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		userInfoDAO.update(userInfo);
		// users.put(userInfo.getUid(), userInfo);
		return true;
	}

	public void checkAndwer() {

	}

	public boolean register(UserInfo userInfo) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if (userInfoDAO.find(userInfo.getName()) != null) {
			return false;
		}
		boolean succ = userInfoDAO.add(userInfo);
		if (succ) {
			// users.put(userInfo.getUid(), userInfo); //由于注册后要重新登录,此处忽略
			return true;
		}
		return false;
	}

	@Override
	public String getPluginName() {
		return "FunnyChat Server";
	}

	public static void main(String[] args) {
		try {
			InetAddress i = InetAddress.getByName("192.169.1.13");
			System.out.println(i.toString().substring(1));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
