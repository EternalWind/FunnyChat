import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.funnyChat.core.*;
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
	private String tempName;
	private String tempState;
	private String userName;
	private String tempPassword;
	private String password;
	private MainWindow2 mTestWin;
	private String[] stateC = {"在线","忙碌","离开"};
	private HashMap<Long,Connection>connections = new HashMap<Long,Connection>();
//	private Vector<String> chatUsers;
	public HashMap<Long,Chat>chatPanels = new HashMap<Long,Chat>();
	private List<UserInfo> friendsInfo;
	protected Connection serverCon;
	private Connection _conTemp;
	private String[] eventList = {
			"ConnectedEvent", "GetFriendsResponseEvent", "ChangeUserStateResponseEvent",
			"CheckLoginInfoResponseEvent", "RegisterResponseEvent", "AddFriendResponseEvent",
			"ChatEvent", "DeleteFriendResponseEvent", "RefreshUserInfoResponseEvent",
			"GetPasswordResponseEvent","ConnectionFailedEvent"
	};
	
	public Client(){
//		chatUsers = new Vector<String>();
		friendsInfo = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		uid = "1231312";
		state="aaa";
		LoginPanel3 _panel = new LoginPanel3(this);
		setPanel(_panel);
		localIp = new String("123.123.123.13");
		localPort = 55555;
//		String ip = new String("192.168.1.102");
//		byte[] _addr = ip.getBytes();
		try {
			serverIp = InetAddress.getByName("192.168.1.13");
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
		friendsInfo = new ArrayList<UserInfo>();
		uid = "1231312";
		state="aaa";
		LoginPanel3 _panel = new LoginPanel3(this);
		_panel.setBounds(0, 0, 413, 270);
		setPanel(_panel);
		localIp = new String("123.123.123.123");
		localPort = NetworkManager.getInstance().getPort();
//		String ip = new String("192.168.1.102");
//		byte[] _addr = ip.getBytes();
		try {
			serverIp = InetAddress.getByName("192.168.1.13");
			//serverIp.getByAddress(_addr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverPort = 55555;		
		
		network_manager = NetworkManager.getInstance();
		network_manager.connect(serverIp, serverPort);
		network_manager.getConnections();
		event_manager = EventManager.getInstance();
		
		mTestWin = Core.getInstance().getMainWindow();	
		
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
		
		mTestWin.updatePanel(this.getPluginName(), mPanel, _panel);
		/*mTestWin.validate();
		mTestWin.repaint();
		_panel.validate();
		_panel.repaint();*/
	}

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(this.hasWork()){
//			if(serverCon != null && serverCon.getIP().toString().substring(1).equals(serverIp.toString().substring(1))){
//				getFriendInfo(Long.parseLong(uid));					
//			}
			System.out.println(mEvent.getEventType());
			if(mEvent.getEventType().equals("ConnectionFailedEvent")){
				System.out.println("LYD DIE DIE DIE!");
				
			}
			if(mEvent.getEventType().equals("ConnectedEvent")){
				getFriendInfo(Long.parseLong(uid));
				Connection _con = ((ConnectedEvent)mEvent).getSource();
				if(_con.getIP().equals(serverIp))
					serverCon = _con;
				_conTemp = _con;
//				else{
//					long _id = 0;
//					for(int i = 0; i < friendsInfo.size(); i++){
//						if((friendsInfo.get(i).getIp()).equals(_con.getIP().toString().substring(1))){
//							_id = friendsInfo.get(i).getUid();
//							break;
//						}
//					}
//					if(_id != 0){
//						connections.put(_id, _con);
//					}
//				}
				System.out.println("LYD DIE DIE DIE!!!");
			}
			if(mEvent.getEventType().equals("GetFriendsResponseEvent")){
				System.out.println(((GetFriendsResponseEvent)mEvent).getEventType()+((GetFriendsResponseEvent)mEvent).getUsersInfo());
				friendsInfo = ((GetFriendsResponseEvent)mEvent).getUsersInfo();
				for(int i = 0; i < friendsInfo.size(); i++){
					System.out.println(friendsInfo.get(i).toString());
				}
				//ConnectRefresh();
				gotoMainPanel();
				Connection _con = _conTemp;
				if(_con.getIP().equals(serverIp))
					serverCon = _con;
				else{
					long _id = 0;
					for(int i = 0; i < friendsInfo.size(); i++){
						if((friendsInfo.get(i).getIp()).equals(_con.getIP().toString().substring(1))){
							_id = friendsInfo.get(i).getUid();
							break;
						}
					}
					if(_id != 0){
						connections.put(_id, _con);
					}
				}				
			}
			if(mEvent.getEventType().equals("GetPasswordResponseEvent")){
				System.out.println(((GetPasswordResponseEvent)mEvent).getEventType()+((GetPasswordResponseEvent)mEvent).getPassword());
				if(!(((GetPasswordResponseEvent)mEvent).getPassword()).equals("")){
					JOptionPane.showMessageDialog(null, "你的密码是"+(((GetPasswordResponseEvent)mEvent).getPassword()));
					gotoLoginPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "取回密码失败！");
					gotoLoginPanel();					
				}
			}			
//			if(mEvent.getEventType().equals("ChangeUserStateResponseEvent"))
//			{}
			if(mEvent.getEventType().equals("CheckLoginInfoResponseEvent")){
				System.out.println(((CheckLoginInfoResponseEvent)mEvent).getEventType()+((CheckLoginInfoResponseEvent)mEvent).getResult());
				if((((CheckLoginInfoResponseEvent)mEvent).getResult()).equals("Succeed")){
					userName = tempName;
					state = tempState;
					password = tempPassword;
					uid = ((CheckLoginInfoResponseEvent)mEvent).getUid();
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
					JOptionPane.showMessageDialog(null, "服务器故障，登陆失败！");
					//gotoLoginPanel();					
				}
			}
			if(mEvent.getEventType().equals("RegisterResponseEvent")){
				System.out.println(((RegisterResponseEvent)mEvent).getEventType()+((RegisterResponseEvent)mEvent).getResult());
				if((((RegisterResponseEvent)mEvent).getResult()).equals("Succeed")){
					JOptionPane.showMessageDialog(null, "注册成功！");
				}
				else{
					JOptionPane.showMessageDialog(null, "服务器故障，注册失败！");
				}
				gotoLoginPanel();

			}
			if(mEvent.getEventType().equals("AddFriendResponseEvent")){
				System.out.println(((AddFriendResponseEvent)mEvent).getEventType()+((AddFriendResponseEvent)mEvent).getResult());
				if((((AddFriendResponseEvent)mEvent).getResult()).equals("Succeed")){
					getFriendInfo(Long.parseLong(uid));
					gotoMainPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "服务器故障，好友增加失败！");
				}
				gotoMainPanel();
			}
			if(mEvent.getEventType().equals("ChatEvent")){
				System.out.println(((ChatEvent)mEvent).toString());
				System.out.println(((ChatEvent)mEvent).getEventType()+((ChatEvent)mEvent).getContent());
				Chat p;
				Connection _c = mEvent.getSource();
				String _ip = ((_c.getIP()).toString()).substring(1);
				long _id = 0;
				for(int i= 0; i < friendsInfo.size(); i++){
					System.out.println(_ip+"aaaaaaa"+friendsInfo.get(i).getIp());
					if(	_ip.equals(friendsInfo.get(i).getIp())){
						_id = friendsInfo.get(i).getUid();
						break;
					}
				}
				if(_id != 0){
				p = chatPanels.get(_id);
				if(p != null)
					p.messageShow(((ChatEvent)mEvent).getContent(), String.valueOf(((ChatEvent)mEvent).getSenderId()));
				else{
					JFrame frame = new JFrame("MyFrame");
					Chat chatPanel = new Chat(this, _id);
		            chatPanels.put( _id,chatPanel);
		            chatPanel.messageShow(((ChatEvent)mEvent).getContent(), String.valueOf(((ChatEvent)mEvent).getSenderId()));
					frame.add(chatPanel);
					frame.setSize(300, 300);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				}					
				}
				else{
					System.out.println("找不到聊天对象的ID！");
				}
			}
			if(mEvent.getEventType().equals("DeleteFriendResponseEvent")){
				System.out.println(((DeleteFriendResponseEvent)mEvent).getEventType()+((DeleteFriendResponseEvent)mEvent).getResult());
				if((((DeleteFriendResponseEvent)mEvent).getResult()).equals("Succeed")){
					getFriendInfo(Long.parseLong(uid));
					gotoMainPanel();
				}
				else{
					JOptionPane.showMessageDialog(null, "服务器故障，好友删除失败！");
				}		
				gotoMainPanel();
			}		
			if(mEvent.getEventType().equals("RefreshUserInfoResponseEvent")){
				System.out.println(((RefreshUserInfoResponseEvent)mEvent).getEventType()+((RefreshUserInfoResponseEvent)mEvent).getResult());
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
		JPanel _panel = new LoginPanel3(this);
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
		_event.setIp(serverIp.toString().substring(1));
		_event.setPort(serverPort);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}
	
	private void getFriendInfo(long _fid){
		GetFriendsEvent _event = new GetFriendsEvent();
		_event.setUId(_fid);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}
	
	public void getPassword(String _answer){
		GetPasswordEvent _event = new GetPasswordEvent();
		//_event.setUserName();
		_event.setAnswer(_answer);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}
	
	public void login(String _userName, String _password, String _state){
		 //gotoMainPanel();
		 CheckLoginInfoEvent _event = new CheckLoginInfoEvent();
		 _event.setName(_userName);
		 _event.setTarget(serverCon);
		 _event.setPassword(_password);
		 _event.setPort(String.valueOf(localPort));
		event_manager.enqueue(_event);
		tempName = _userName;
		tempState = _state;
		tempPassword = _password;
	}
	
	public void chat(String _content, String _fid){
		ChatEvent _event = new ChatEvent();
		Connection _c = connections.get(Long.parseLong(_fid));
		if(_c == null){
			JOptionPane.showMessageDialog(null, "未与该用户连接！");
		}
		else{
			_event.setContent(_content);
			_event.setReceiverId(_fid);
			_event.setSenderId(uid);
			_event.setTarget(_c);
			event_manager.enqueue(_event);	
		}
	}
	public void addFriend(long _uid1, long _uid2){
		AddFriendEvent _event = new AddFriendEvent();
		_event.setUId1(_uid1);
		_event.setUId2(_uid2);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}

	private void refreshUserInfo(UserInfo _user_info){
		RefreshUserInfoEvent _event = new RefreshUserInfoEvent();
		_event.setUserInfo(_user_info);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}	

	public void deleteFriend(long _uid1, long _uid2){
		DeleteFriendEvent _event = new DeleteFriendEvent();
		_event.setUId1(_uid1);
		_event.setUId2(_uid2);
		_event.setTarget(serverCon);
		event_manager.enqueue(_event);
	}		

	public void gotoRegisterPanel(){
		JPanel _panel = new RegisterPanel2(this);
		_panel.setBounds(0, 0, 503, 523);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);	
		/*mTestWin.validate();
		mTestWin.repaint();
		_panel.validate();
		_panel.repaint();*/
//		frame.setSize(389,303);
//		Panel _panel = new registerPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);		
	}

	public void gotoMainPanel(){
		JPanel _panel = new MainPanel(this);
		_panel.setBounds(0, 0, 419, 506);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);	
		/*mTestWin.validate();
		mTestWin.repaint();
		_panel.validate();
		_panel.repaint();*/
//		frame.setSize(228,457);
//		Panel _panel = new mainFunnyChatPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);				
	}	

	public void gotoLoginPanel(){
		JPanel _panel = new LoginPanel3(this);
		_panel.setBounds(0, 0, 413, 270);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);
		/*mTestWin.validate();
		mTestWin.repaint();
		_panel.validate();
		_panel.repaint();*/
//		frame.setSize(245,285);
//		Panel _panel = new loginPanel(this);
//		frame.remove(mPanel);
//		setPanel(_panel);
//		frame.add(mPanel);
//		frame.setVisible(true);				
	}	

	public void gotoPasswordPanel(String _name){
		PasswordPanel2 _panel = new PasswordPanel2(this);
		_panel.setBounds(0, 0, 268, 277);
		mTestWin.updatePanel(this.getName(), mPanel, _panel);
		setPanel(_panel);	
		/*mTestWin.validate();
		mTestWin.repaint();
		_panel.validate();
		_panel.repaint();*/
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
		UserInfo _user_info = new UserInfo();
		_user_info.setIp(localIp.toString());
		_user_info.setPort(String.valueOf(localPort));
		_user_info.setState(state);
		_user_info.setUid(Long.parseLong(uid));
		_user_info.setName(userName);
		_user_info.setPassword(password);
		this.refreshUserInfo(_user_info);
	}	
	
	public String[] getStateC(){
		return stateC;
	}
	
	private void ConnectRefresh(){
		InetAddress _ip; 
		for(int i = 0; i < friendsInfo.size(); i++){
			try {
				if(!(friendsInfo.get(i).getState()).equals("离线") && connections.get((friendsInfo.get(i))) == null){
					_ip = InetAddress.getByName((friendsInfo.get(i)).getIp());
					System.out.println(_ip.toString()+"UUUUUUU"+Integer.parseInt(((friendsInfo.get(i)).getPort())));
				    network_manager.connect(_ip, Integer.parseInt(((friendsInfo.get(i)).getPort())));
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
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
