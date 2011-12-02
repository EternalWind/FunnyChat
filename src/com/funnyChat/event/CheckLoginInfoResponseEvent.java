package com.funnyChat.event;

import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class CheckLoginInfoResponseEvent extends MessageEvent {

	public CheckLoginInfoResponseEvent(Connection _target, Server _server,
			String _name, String _password) {
		super(_target);
		data.put("MessageType", "CheckLoginInfoResponseEvent");
		if (_server.checkLoginInfo(_name, _password))
			data.put("Result", "Succeed");
		else
			data.put("Result", "Failed");
	}

	@Override
	public String getEventType() {
		return "CheckLoginInfoResponseEvent";
	}
}
