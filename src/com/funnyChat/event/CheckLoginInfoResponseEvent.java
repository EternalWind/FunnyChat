package com.funnyChat.event;

public class CheckLoginInfoResponseEvent extends Event {

	private String mResult;
	private String uid;

    public String getUid() {
		return uid;
	}

	public void setUid(String _uid) {
		this.uid = _uid;
	}

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
		return mResult + " " + uid;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		int space = dataStr.indexOf(" ");
		mResult = dataStr.substring(0, space);
		uid = dataStr.substring(space+1);
	}
}