package com.funnyChat.event;

import com.funnyChat.server.Server;

public class CheckLoginInfoResponseEvent extends Event {

	private String mResult;

	public CheckLoginInfoResponseEvent(Server _server, String _name,
			String _password) {
		if (_server.checkLoginInfo(_name, _password))
			mResult = "Succeed";
		else
			mResult = "Failed";
	}

	public String getResult() {
		return mResult;
	}

	@Override
	public String getEventType() {
		return "CheckLoginInfoResponseEvent";
	}

	@Override
	protected String onSerialize() {
		return mResult;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mResult = dataStr;
	}
}
