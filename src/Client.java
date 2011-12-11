

import java.awt.Panel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.funnyChat.core.Core;
import com.funnyChat.core.MainWindow2;
import com.funnyChat.db.UserInfo;
import com.funnyChat.event.ChatEvent;
import com.funnyChat.event.ConnectedEvent;
import com.funnyChat.event.Event;
import com.funnyChat.event.EventManager;
import com.funnyChat.plugin.*;
import com.funnyChat.network.*;
import com.funnyChat.event.*;
 

public class Client extends Plugin{
	private InetAddress serverIp;
	private int serverPort;
	private String localIp;
	private int localPort;	
	private NetworkManager network_manager;
	private EventManager event_manager;
	private String uid;
	private String state;
	private JFrame frame;
	private String tempId;
	private String tempState;
	private String userName;
	private String tempPassword;
	private String password;
	private MainWindow2 mTestWin;
//	private Vector<String> chatUsers;
	public Vector<ChatPanel> chatPanels; 
	private List<UserInfo> friendsInfo;
	private String[] eventList = {
			"ConnectedEvent", "GetFriendsEventResponse", "ChangeUserStateResponseEvent",
			"CheckLoginInfoResponseEvent", "RegisterResponseEvent", "AddFriendResponseEvent",
			"ChatEvent", "DeleteFriendResponseEvent", "RefreshUserInfoResponseEvent",
			"GetPasswordResponseEvent","ConnectionFailedEvent"
	};
	
	public Client(){
//		chatUsers = new Vector<String>();
		chatPanels = new Vector<ChatPanel>();
		friendsInfo = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		u.setName("asda");
		u.setState("asda");
		friendsInfo.add(u);
		uid = "1231312";
		state="aaa";
		Panel _panel = new loginPanel(this);
		setPanel(_panel);
		localIp = new String("123.123.123.123");
		localPort = 8888;
//		String ip = new String("192.168.1.102");
//		byte[] _addr = ip.getBytes();
		try {
			serverIp = InetAddress.getByName("192.168.1.103");
			//serverIp.getByAddress(_addr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverPort = 55555;	
	}
	@Override
	protected void onEnable() {
		// TODO Auto-generated method stub
		chatPanels = new Vector<ChatPanel>();
		friendsInfo = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		u.setName("asda");
		u.setState("asda");
		friendsInfo.add(u);
		uid = "1231312";
		state="aaa";
		Panel _panel = new loginPanel(this);
		_panel.setBounds(0, 0, 245, 285);
		setPanel(_panel);
		localIp = new String("123.123.123.123");
		localPort = 8888;
//		String ip = new String("192.168.1.102");
//		byte[] _addr = ip.getBytes();
		try {
			serverIp = InetAddress.getByName("192.168.1.103");
			//serverIp.getByAddress(_addr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverPort = 55555;		
		
		network_manager = NetworkManager.getInstance();
		network_manager.connect(serverIp, serverPort);
		event_manager = EventManager.getInstance();
		
		mTestWin = Core.getInstance().getMainWindow();	
		
		EventManager.getInstance().register(new CheckLoginInfoEvent());
//		EventManager.getInstance().register(new ConnectionFailedEvent());
//		EventManager.getInstance().register(new CheckLoginInfoEvent());
//		EventManager.getInstance().register(new CheckLoginInfoEvent());
	}

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(this.hasWork()){
			if(mEvent.getEventType().equals("ConnectionFailedEvent")){
				System.out.println("LYD DIE DIE DIE!");
			}
			if(mEvent.getEventType().equals("ConnectedEvent")){
				System.out.println("LYD DIE DIE DIE!!!");
			}
			if(mEvent.getEventType().equals("GetFriendsResponseEvent")){
				friendsInfo = ((GetFriendsResponseEvent)mEvent).getUsersInfo();
			}
			if(mEvent.getEventType().equals("GetPasswordResponseEvent")){
				if(!(((GetPasswordResponseEvent)mEvent).getPassword()).equals("")){
					JOptionPane.showMessageDialog(null, "ÄãµÄÃÜÂëÊÇ"+(((GetPasswordResponseEvent)mEvent).getPassword()));
					gotoLoginPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "È¡»ØÃÜÂëÊ§°Ü£¡");
					gotoLoginPanel();					
				}
			}			
//			if(mEvent.getEventType().equals("ChangeUserStateResponseEvent"))
//			{}
			if(mEvent.getEventType().equals("CheckLoginInfoResponseEvent")){
				if((((CheckLoginInfoResponseEvent)mEvent).getResult()).equals("Succeed")){
					uid = tempId;
					state = tempState;
					password = tempPassword;
					userName = "";
					UserInfo _userinfo = new UserInfo();
					_userinfo.setIp(localIp.toString());
					_userinfo.setPort(String.valueOf(localPort));
					_userinfo.setState(state);
					_userinfo.setUid(Long.parseLong(uid));
					_userinfo.setName(userName);
					_userinfo.setPassword(password);
					refreshUserInfo(_userinfo);
					getFriendInfo(Long.parseLong(uid));	
					gotoMainPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "·þÎñÆ÷¹ÊÕÏ£¬µÇÂ½Ê§°Ü£¡");
					//gotoLoginPanel();					
				}
			}
			if(mEvent.getEventType().equals("RegisterResponseEvent")){
				if((((RegisterResponseEvent)mEvent).getResult()).equals("Succeed")){
					JOptionPane.showMessageDialog(null, "×¢²á³É¹¦£¡");
				}
				else{
					JOptionPane.showMessageDialog(null, "·þÎñÆ÷¹ÊÕÏ£¬×¢²áÊ§°Ü£¡");
				}
				gotoLoginPanel();

			}
			if(mEvent.getEventType().equals("AddFriendResponseEvent")){
				if((((RegisterResponseEvent)mEvent).getResult()).equals("Succeed")){
				}
				else{
					JOptionPane.showMessageDialog(null, "·þÎñÆ÷¹ÊÕÏ£¬ºÃÓÑÔö¼ÓÊ§°Ü£¡");
				}
				gotoMainPanel();
			}
			if(mEvent.getEventType().equals("ChatEvent")){
				Panel p = new Panel();
				for(int i = 0; i < chatPanels.size(); i++){
					if((chatPanels.get(i)).getFid() == (new Integer(((ChatEvent)mEvent).getSenderId())).longValue()){
						(chatPanels.get(i)).messageShow(((ChatEvent)mEvent).getContent(), String.valueOf(((ChatEvent)mEvent).getSenderId()));
					    break;
					}
				}
			}
			if(mEvent.getEventType().equals("DeleteFriendResponseEvent")){
				if((((RegisterResponseEvent)mEvent).getResult()).equals("Succeed")){
				}
				else{
					JOptionPane.showMessageDialog(null, "·þÎñÆ÷¹ÊÕÏ£¬ºÃÓÑÉ¾³ýÊ§°Ü£¡");
				}		
				gotoMainPanel();
			}		
			if(mEvent.getEventType().equals("RefreshUserInfoResponseEvent")){
				if((((RefreshUserInfoResponseEvent)mEvent).getResult()).equals("Failed")){
					UserInfo _userinfo = new UserInfo();
					_userinfo.setIp(localIp.toString());
					_userinfo.setPort(String.valueOf(localPort));
					_userinfo.setState(state);
					_userinfo.setUid(Long.parseLong(uid));
					_userinfo.setName(userName);
					_userinfo.setPassword(password);
					refreshUserInfo(_userinfo);
				}
					
			}					
			this.doneWork();
		}
	}

	@Override
	protected boolean isInterested(Event _event) {
		// TODO Auto-generated method stub
		for(int i = 0; i < eventList.length; i++){
			if(_event.getEventType().equalsIgnoreCase(eventList[i]))
				return true;
		}
		return false;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Panel _panel = new loginPanel(this);
		setPanel(_panel);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
	}

//	private boolean modifiedState(long _fid, String _state){
//		Event _event = new ChangeUserStateEvent(_fid, _state);
//		event_manager.enqueue(_event);
//		return true;
//	}
	
	public void register(String _name, String _password, String _question, String _answer){
		RegisterEvent _event = new RegisterEvent();
		_event.setName(_name);
		_event.setPassword(_password);
		_event.setQuestion(_question);
		_event.setAnswer(_answer);
		_event.setIp(serverIp.toString());
		_event.setPort(serverPort);
		event_manager.enqueue(_event);
	}
	
	private void getFriendInfo(long _fid){
		GetFriendsEvent _event = new GetFriendsEvent();
		_event.setUId(_fid);
		event_manager.enqueue(_event);
	}
	
	public void getPassword(String _answer){
		GetPasswordEvent _event = new GetPasswordEvent();
		_event.setAnswer(_answer);
		event_manager.enqueue(_event);
	}
	
	public void login(String _id, String _password, String _state){
		 //gotoMainPanel();
		 CheckLoginInfoEvent _event = new CheckLoginInfoEvent();
		 _event.setName(_id);
		 _event.setPassword(_password);
		event_manager.enqueue(_event);
		tempId = _id;
		tempState = _state;
		tempPassword = _password;
	}
	
	public void chat(String _content, String _fid){
		ChatEvent _event = new ChatEvent();
		_event.setContent(_content);
		_event.setReceiverId(_fid);
		_event.setSenderId(uid);
		event_manager.enqueue(_event);	
	}
	public void addFriend(long _uid1, long _uid2){
		AddFriendEvent _event = new AddFriendEvent();
		_event.setUId1(_uid1);
		_event.setUId2(_uid2);
		event_manager.enqueue(_event);
	}

	private void refreshUserInfo(UserInfo _user_info){
		RefreshUserInfoEvent _event = new RefreshUserInfoEvent();
		_event.setUserInfo(_user_info);
		event_manager.enqueue(_event);
	}	

	public void deleteFriend(long _uid1, long _uid2){
		DeleteFriendEvent _event = new DeleteFriendEvent();
		_event.setUId1(_uid1);
		_event.setUId2(_uid2);
		event_manager.enqueue(_event);
	}		

	public void gotoRegisterPanel(){
		Panel _panel = new registerPanel(this);
		_panel.setBounds(0, 0, 389, 303);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);				
//		frame.setSize(389,303);
//		Panel _panel = new registerPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);		
	}

	public void gotoMainPanel(){
		Panel _panel = new mainFunnyChatPanel(this);
		_panel.setBounds(0, 0, 228, 457);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);			
//		frame.setSize(228,457);
//		Panel _panel = new mainFunnyChatPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);				
	}	

	public void gotoLoginPanel(){
		Panel _panel = new loginPanel(this);
		_panel.setBounds(0, 0, 245, 285);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);
//		frame.setSize(245,285);
//		Panel _panel = new loginPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);				
	}	

	public void gotoPasswordPanel(){
		Panel _panel = new PasswordPanel(this);
		_panel.setBounds(0, 0, 268, 277);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);		
//		frame.setSize(268, 277);
//		Panel _panel = new PasswordPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);				
	}		
	
	@Override
	public String getPluginName() {
		return "FunnyChat Cilent";
	}
	
	public String getUid(){
		return uid;
	}
	
	public void setUid(String _uid){
		uid = _uid;
	}

	public String getUserState(){
		return state;
	}

	public String[] friendsName(){
		String[] name = new String[friendsInfo.size()];
		for(int i = 0; i < friendsInfo.size(); i++){
			name[i] = (friendsInfo.get(i)).getName();
		}
		return name;
	}

	public String[] friendsState(){
		String[] state = new String[friendsInfo.size()];
		for(int i = 0; i < friendsInfo.size(); i++){
			state[i] = (friendsInfo.get(i)).getState();
		}
		return state;
	}

	public String[] friendsID(){
		String[] state = new String[friendsInfo.size()];
		for(int i = 0; i < friendsInfo.size(); i++){
			state[i] = String.valueOf((friendsInfo.get(i)).getUid());
		}
		return state;
	}
	
	public void setUserState(String _state){
		state = _state;
	}	
	/////////////////////TEST///////////////////////////////
//	public static void main(String args[])
//	{
//		Client c = new Client();
//		JFrame frame = new JFrame("MyFrame");
//		c.frame = frame;
//		frame.add(c.getPanel());
//		frame.setSize(205,245);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
}
