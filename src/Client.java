

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.funnyChat.db.UserInfo;
import com.funnyChat.event.Event;
import com.funnyChat.plugin.*;
import com.funnyChat.network.*;
import com.funnyChat.event.*;
 

public class Client extends Plugin{
	private InetAddress serverIp;
	private int serverPort;
	private NetworkManager network_manager;
	private EventManager event_manager;
	private long uid;
	private String[] eventList = {
			"ConnectedEvent", "GetFriendsEventResponse", "ChangeUserStateResponseEvent",
			"CheckLoginInfoResponseEvent", "RegisterEvent", "AddFriendResponseEvent",
			"ChatEvent", "DeleteFriendResponseEvent", "RefreshUserInfoResponseEvent"
	};
	@Override
	protected void onEnable() {
		// TODO Auto-generated method stub
		network_manager = NetworkManager.getInstance();
		network_manager.connect(serverIp, serverPort);
		event_manager = EventManager.getInstance();
	}

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(this.hasWork()){
			if(mEvent.getEventType().equals("ConnectedEvent"))
			{}
			if(mEvent.getEventType().equals("GetFriendsEventResponse"))
			{}
			if(mEvent.getEventType().equals("ChangeUserStateResponseEvent"))
			{}
			if(mEvent.getEventType().equals("CheckLoginInfoResponseEvent"))
			{}
			if(mEvent.getEventType().equals("RegisterEvent"))
			{}
			if(mEvent.getEventType().equals("AddFriendResponseEvent"))
			{}
			if(mEvent.getEventType().equals("ChatEvent"))
			{}
			if(mEvent.getEventType().equals("DeleteFriendResponseEvent"))
			{}		
			if(mEvent.getEventType().equals("RefreshUserInfoResponseEvent"))
			{}					
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
		File _config = new File("Client Config.txt");
		if(!_config.exists() || !_config.isFile()) {
			byte[] _ip = new byte[4];
			_ip[0] = (byte)192;
			_ip[1] = (byte)168;
			_ip[2] = (byte)1;
			_ip[3] = (byte)16;
			try {
				serverIp = InetAddress.getByAddress(_ip);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serverPort = 5643;
			
			try {
				FileWriter _file_writer = new FileWriter(_config);
				
				String _temp = serverIp.getHostAddress();
				_temp += "\r\n";
				_temp += serverPort;
				_file_writer.write(_temp);
				
				_file_writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				BufferedReader _file_reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(_config)));
				
				try {
					String _data = _file_reader.readLine();
					serverIp = InetAddress.getByName(_data);
					
					_data = _file_reader.readLine();
					serverPort = new Integer(_data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	}

	private boolean modifiedState(long _fid, String _state){
		Event _event = new ChangeUserStateEvent(_fid, _state);
		event_manager.enqueue(_event);
		return true;
	}
	
	private boolean register(String _name, String _password, String _state, String _ip, String _port){
		Event _event = new RegisterEvent(_name, _password, _state, _ip, _port);
		event_manager.enqueue(_event);
		return true;
	}
	
	private boolean getFriendInfo(long fid){
		Event _event = new GetFriendsEvent(fid);
		event_manager.enqueue(_event);
		return true;
	}
	
	private boolean getPassword(String _answer){
		Event _event = new GetPasswordEvent(_answer);
		event_manager.enqueue(_event);
		return true;
	}
	
	private boolean login(String _name, String _password){
		Event _event = new CheckLoginInfoEvent(_name, _password);
		event_manager.enqueue(_event);
		return true;
	}
	
	
	private boolean addFriend(long _uid1, long _uid2){
		Event _event = new AddFriendEvent(_uid1, _uid2);
		event_manager.enqueue(_event);
		return true;
	}

	private boolean refreshUserInfo(UserInfo _user_info){
		Event _event = new RefreshUserInfoEvent(_user_info);
		event_manager.enqueue(_event);
		return true;
	}	

	private boolean deleteFriend(long _uid1, long _uid2){
		Event _event = new DeleteFriendEvent(_uid1, _uid2);
		event_manager.enqueue(_event);
		return true;
	}		
	
	@Override
	public String getPluginName() {
		return "FunnyChat Cilent";
	}
}
