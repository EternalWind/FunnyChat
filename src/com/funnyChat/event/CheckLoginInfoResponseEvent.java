package com.funnyChat.event;

public class CheckLoginInfoResponseEvent extends Event {

	private String mResult;

//	public CheckLoginInfoResponseEvent(Server _server, String _name,
//			String _password) {
//		if (_server.checkLoginInfo(_name, _password))
//			mResult = "Succeed";
//		else
//			mResult = "Failed";
//	}
	public CheckLoginInfoResponseEvent(){
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
