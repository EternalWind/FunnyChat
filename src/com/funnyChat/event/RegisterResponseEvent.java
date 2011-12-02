package com.funnyChat.event;

import com.funnyChat.db.UserInfo;
import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class RegisterResponseEvent extends MessageEvent {
	public RegisterResponseEvent(Connection _target, Server _server,
			UserInfo _user_info) {
		super(_target);
		data.put("MessageType", "RegisterResponseEvent");
		if (_server.register(_user_info))
			data.put("Result", "Succeed");
		else
			data.put("Result", "Failed");
	}

	@Override
	public String getEventType() {
		return "RegisterResponseEvent";
	}
}
