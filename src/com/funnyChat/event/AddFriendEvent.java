package com.funnyChat.event;

import com.funnyChat.network.Connection;

public class AddFriendEvent extends MessageEvent {

	public AddFriendEvent(Connection _target,long _uid1,long _uid2) {
		super(_target);
		data.put("MessageType", "AddFriendEvent");
		data.put("UId1", Long.toString(_uid1));
		data.put("UId2", Long.toString(_uid2));
	}

	@Override
	public String getEventType() {
		return "AddFriendEvent";
	}
}
