package com.funnyChat.event;

public class RegisterEvent extends Event {

	private String mName;
	private String mPassword;
	private String mQuestion;
	private String mAnswer;
	private String mIp;
	private String mPort;

	public RegisterEvent(String _name, String _password, String _question, String _answer,
			String _ip, String _port) {
		mName = _name;
		mPassword = _password;
		mQuestion = _question;
		mAnswer = _answer;
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

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String _question) {
		this.mQuestion = _question;
	}	
	
	public String getAnswer() {
		return mAnswer;
	}

	public void setAnswer(String _answer) {
		this.mAnswer = _answer;
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
		return mName + " " + mPassword + " " + mQuestion + " " + mAnswer + " " + mIp + " " + mPort;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mName = _data[0];
		mPassword = _data[1];
		mQuestion = _data[2];
		mAnswer = _data[3];
		mIp = _data[4];
		mPort = _data[5];
	}
}
