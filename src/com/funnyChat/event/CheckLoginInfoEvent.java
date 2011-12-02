package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class CheckLoginInfoEvent extends MessageEvent {
	public CheckLoginInfoEvent(Connection _target,String _name,String _password) {
		super(_target);
		data.put("MessageType", "CheckLoginInfoEvent");
		data.put("Name", _name);
		data.put("Password", _password);
	}

	@Override
	public String getEventType() {
		return "CheckLoginInfoEvent";
	}
}
