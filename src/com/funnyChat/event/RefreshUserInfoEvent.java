package com.funnyChat.event;

import com.funnyChat.db.UserInfo;
import com.funnyChat.network.Connection;

public class RefreshUserInfoEvent extends MessageEvent {
	
	public RefreshUserInfoEvent(Connection _target, UserInfo _user_info) {
		super(_target);
		data.put("MessageType", "RefreshUserInfoEvent");
		data.put("UId", Long.toString(_user_info.getUid()));
		data.put("Name", _user_info.getName());
		data.put("Password", _user_info.getPassword());
		data.put("State", _user_info.getState());
		data.put("Ip", _user_info.getIp());
		data.put("Port", _user_info.getPort());
	}

	@Override
	public String getEventType() {
		return "RefreshUserInfoEvent";
	}
}
