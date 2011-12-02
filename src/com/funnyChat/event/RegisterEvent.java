package com.funnyChat.event;

public class RegisterEvent extends Event {

	private String mName;
	private String mPassword;
	private String mState;
	private String mIp;
	private String mPort;

	public RegisterEvent(String _name, String _password, String _state,
			String _ip, String _port) {
		mName = _name;
		mPassword = _password;
		mState = _state;
		mIp = _ip;
		mPort = _port;
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

	public String getState() {
		return mState;
	}

	public void setState(String _state) {
		this.mState = _state;
	}

	public String getIp() {
		return mIp;
	}

	public void setIp(String _ip) {
		this.mIp = _ip;
	}

	public String getPort() {
		return mPort;
	}

	public void setPort(String _port) {
		this.mPort = _port;
	}

	@Override
	public String getEventType() {
		return "RegisterEvent";
	}

	@Override
	protected String onSerialize() {
		return mName + " " + mPassword + " " + mState + " " + mIp + " " + mPort;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mName = _data[0];
		mPassword = _data[1];
		mState = _data[2];
		mIp = _data[3];
		mPort = _data[4];
	}
}
