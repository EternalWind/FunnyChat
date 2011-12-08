package com.funnyChat.event;

import com.funnyChat.server.Server;

public class ChangeUserStateResponseEvent extends Event {

	private String mResult;
	
//	public ChangeUserStateResponseEvent(Server _server,
//			long _user_id, String _state) {
//		if (_server.sendUserStateChange(_user_id, _state))
//			mResult = "Succeed";
//		else
//			mResult = "Failed";
//	}
	public ChangeUserStateResponseEvent(){
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
		return "ChangeUserStateResponseEvent";
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
