package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class ReleasePluginEvent extends MessageEvent {

	public ReleasePluginEvent(Connection _target, byte[] _plugins) {
		super(_target);
		data.put("MessageType", "ReleasePluginEvent");
		data.put("Plugins", String.valueOf(_plugins));
	}

	@Override
	public String getEventType() {
		return "ReleasePluginEvent";
	}
}
