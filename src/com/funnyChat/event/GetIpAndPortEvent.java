package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class GetIpAndPortEvent extends MessageEvent {

	public GetIpAndPortEvent(Connection _target, long _user_id) {
		super(_target);
		data.put("MessageType", "GetIpAndPortEvent");
		data.put("UId",Long.toString(_user_id));
	}

	@Override
	public String getEventType() {
		return "GetIpAndPortEvent";
	}
}
