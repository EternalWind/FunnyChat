package com.funnyChat.event;

public class CheckLoginInfoEvent extends Event {

	private String mName;
	private String mPassword;

	public CheckLoginInfoEvent(String _name, String _password) {
		mName = _name;
		mPassword = _password;
	}

	public String getName() {
		return mName;
	}

	public void setName(String _name) {
		this.mName = _name;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String _password) {
		this.mPassword = _password;
	}

	@Override
	public String getEventType() {
		return "CheckLoginInfoEvent";
	}

	@Override
	protected String onSerialize() {
		return mName + " " + mPassword;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mName = _data[0];
		mPassword = _data[1];
	}
}
