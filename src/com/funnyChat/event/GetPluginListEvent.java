package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class GetPluginListEvent extends MessageEvent {

	public GetPluginListEvent(Connection _target) {
		super(_target);
		data.put("MessageType", "GetPluginListEvent");
	}

	@Override
	public String getEventType() {
		return "GetPluginListEvent";
	}
}
