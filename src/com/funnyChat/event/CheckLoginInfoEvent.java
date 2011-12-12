package com.funnyChat.event;

public class CheckLoginInfoEvent extends Event {

	private String mName;
	private String mPassword;
	private String mPort;

	public CheckLoginInfoEvent() {
		mName = "";
		mPassword = "";
		mPort = "";
	}

	public String getPort() {
		return mPort;
	}

	public void setPort(String port) {
		this.mPort = port;
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
		return mName + " " + mPassword+ " "+ mPort;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mName = _data[0];
		mPassword = _data[1];
		mPort = _data[2];
	}
}