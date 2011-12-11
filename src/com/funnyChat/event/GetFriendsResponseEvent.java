package com.funnyChat.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.funnyChat.db.UserInfo;

public class GetFriendsResponseEvent extends Event {

	private List<UserInfo> mUsersInfo;



	public void setUsersInfo(List<UserInfo> mUsersInfo) {
		this.mUsersInfo = mUsersInfo;
	}

//	public GetFriendsResponseEvent(Server _server, long _user_id) {
//		mUsersInfo = _server.getFriends(_user_id);
//	}
	public GetFriendsResponseEvent() {
		mUsersInfo = null;
	}

	public List<UserInfo> getUsersInfo() {
		return mUsersInfo;
	}

	@Override
	public String getEventType() {
		return "GetFriendsResponseEvent";
	}

	@Override
	protected String onSerialize() {
//		String _friends = String.valueOf(mUsersInfo.size());
//		for (UserInfo _user_info : mUsersInfo) {
//			_friends += " " + _user_info.getUid() + " " + _user_info.getName();
//		}
//		return _friends;
		String str = "";
		for(int i=0;i<mUsersInfo.size();i++){
			str+="<userInfo_"+i+">"+mUsersInfo.get(i).toString()+"</userInfo_"+i+">";
		}
		
		return str;
	}

	@Override
	protected void onUnserialize(String _data_str) {
//		mUsersInfo.clear();
//		String[] _data = dataStr.split(" ");
//		int _size = Integer.parseInt(_data[0]);
//		UserInfo[] _users_info = new UserInfo[2 * _size];
//		for (int i = 0; i != _size; i += 2) {
//			_users_info[i].setUid(Long.parseLong(_data[i + 1]));
//			_users_info[i].setName(_data[i + 2]);
//			mUsersInfo.add(_users_info[i]);
//		}
		HashMap<String,String> data = new HashMap<String, String>();
		int pos = _data_str.indexOf("<");
		while(pos<_data_str.length()&&pos>=0){
			int end = _data_str.indexOf(">");
			String key = _data_str.substring(pos+1, end);
			pos = end+1;
			end = _data_str.indexOf("</"+key+">");
			String value = _data_str.substring(pos, end);
			pos = end+3+key.length();
			data.put(key, value);
			_data_str = _data_str.substring(pos);
			pos=0;
		}
		mUsersInfo = new ArrayList<UserInfo>();
		for(String key: data.keySet()){
			mUsersInfo.add(UserInfo.parse(data.get(key)));
		}
		
	}
}
