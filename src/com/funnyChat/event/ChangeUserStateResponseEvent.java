package com.funnyChat.event;

import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class ChangeUserStateResponseEvent extends MessageEvent {

	public ChangeUserStateResponseEvent(Connection _target, Server _server,
			long _user_id, String _state) {
		super(_target);
		data.put("MessageType", "ChangeUserStateResponseEvent");
		if (_server.sendUserStateChange(_user_id, _state))
			data.put("Result", "Succeed");
		else
			data.put("Result", "Failed");
	}

	@Override
	public String getEventType() {
		return "ChangeUserStateResponseEvent";
	}
}
