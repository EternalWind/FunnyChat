package com.funnyChat.event;

import com.funnyChat.db.UserInfo;
import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class RefreshUserInfoResponseEvent extends MessageEvent {

	public RefreshUserInfoResponseEvent(Connection _target, Server _server,
			UserInfo _user_info) {
		super(_target);
		data.put("MessageType", "RefreshUserInfoResponseEvent");
		if (_server.refreshUserInfo(_user_info))
			data.put("Result", "Succeed");
		else
			data.put("Result", "Failed");
	}

	@Override
	public String getEventType() {
		return "RefreshUserInfoResponseEvent";
	}
}
