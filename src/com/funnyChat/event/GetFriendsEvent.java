package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class GetFriendsEvent extends MessageEvent {
	
	public GetFriendsEvent(Connection _target, long _user_id) {
		super(_target);
		data.put("MessageType", "GetFriendsEvent");
		data.put("UId", Long.toString(_user_id));
	}

	@Override
	public String getEventType() {
		return "GetFriendsEvent";
	}
}
