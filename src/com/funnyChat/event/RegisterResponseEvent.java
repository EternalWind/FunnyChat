package com.funnyChat.event;

import com.funnyChat.db.UserInfo;
import com.funnyChat.server.Server;

public class RegisterResponseEvent extends Event {

	private String mResult;

//	public RegisterResponseEvent(Server _server, UserInfo _user_info) {
//		if (_server.register(_user_info))
//			mResult = "Succeed";
//		else
//			mResult = "Failed";
//	}
	public RegisterResponseEvent() {
		mResult = "Failed";
	}

	public String getResult() {
		return mResult;
	}
	public void setResult(String _result) {
		mResult = _result;
	}

	@Override
	public String getEventType() {
		return "RegisterResponseEvent";
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
