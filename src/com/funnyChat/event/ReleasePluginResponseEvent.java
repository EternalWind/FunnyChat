package com.funnyChat.event;

import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class ReleasePluginResponseEvent extends MessageEvent {
	
	public ReleasePluginResponseEvent(Connection _target, Server _server) {
		super(_target);
		data.put("MessageType", "ReleasePluginResponseEvent");
		data.put("Result", "AAAAAAAAAAAAAAAAA");
	}

	@Override
	public String getEventType() {
		return "ReleasePluginResponseEvent";
	}
}
