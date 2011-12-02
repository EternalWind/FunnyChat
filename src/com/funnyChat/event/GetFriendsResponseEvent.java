package com.funnyChat.event;

import java.util.List;

import com.funnyChat.db.UserInfo;
import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class GetFriendsResponseEvent extends MessageEvent {

	public GetFriendsResponseEvent(Connection _target, Server _server,
			long _user_id) {
		super(_target);
		List<UserInfo> _users_info = _server.getFriends(_user_id);
		data.put("MessageType", "GetFriendsResponseEvent");
		int i = 0;
		for (UserInfo _user_info : _users_info) {
			data.put("Friend_" + i++, _user_info.getUid() + ":"
					+ _user_info.getName());
		}
	}

	@Override
	public String getEventType() {
		return "GetFriendsResponseEvent";
	}
}
