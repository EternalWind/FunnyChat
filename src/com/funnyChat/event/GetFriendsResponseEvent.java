package com.funnyChat.event;

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
		String _friends = String.valueOf(mUsersInfo.size());
		for (UserInfo _user_info : mUsersInfo) {
			_friends += " " + _user_info.getUid() + " " + _user_info.getName();
		}
		return _friends;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mUsersInfo.clear();
		String[] _data = dataStr.split(" ");
		int _size = Integer.parseInt(_data[0]);
		UserInfo[] _users_info = new UserInfo[2 * _size];
		for (int i = 0; i != _size; i += 2) {
			_users_info[i].setUid(Long.parseLong(_data[i + 1]));
			_users_info[i].setName(_data[i + 2]);
			mUsersInfo.add(_users_info[i]);
		}
	}
}
