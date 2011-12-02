package com.funnyChat.event;

import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class AddFriendResponseEvent extends MessageEvent {

	public AddFriendResponseEvent(Connection _target, Server _server,
			long _uid1, long _uid2) {
		super(_target);
		data.put("MessageType", "AddFriendResponseEvent");
		if (_server.addFriend(_uid1, _uid2))
			data.put("Result", "Succeed");
		else
			data.put("Result", "Failed");
	}

	@Override
	public String getEventType() {
		return "AddFriendResponseEvent";
	}
}
