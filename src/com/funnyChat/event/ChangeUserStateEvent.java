package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class ChangeUserStateEvent extends MessageEvent {
	
	public ChangeUserStateEvent(Connection _target, long _user_id, String _state) {
		super(_target);
		data.put("MessageType", "ChangeUserStateEvent");
		data.put("UId", Long.toString(_user_id));
		data.put("State", _state);
	}

	@Override
	public String getEventType() {
		return "ChangeUserStateEvent";
	}
}
