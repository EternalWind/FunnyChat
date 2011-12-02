package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class RegisterEvent extends MessageEvent {
	public RegisterEvent(Connection _target, String _name, String _password,
			String _state, String _ip, String _port) {
		super(_target);
		data.put("MessageType", "RegisterEvent");
		data.put("Name", _name);
		data.put("Password", _password);
		data.put("State", _state);
		data.put("Ip", _ip);
		data.put("Port", _port);
	}

	@Override
	public String getEventType() {
		return "RegisterEvent";
	}
}
