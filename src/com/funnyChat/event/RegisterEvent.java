package com.funnyChat.event;

public class RegisterEvent extends Event {

	private String mName;
	private String mPassword;
	private String mQuestion;
	private String mAnswer;
	private String mIp;
	private Integer mPort;

	public RegisterEvent() {
		mName = "";
		mPassword = "";
		mQuestion = "";
		mAnswer = "";
		mIp = "";
		mPort = 0;
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

	public Integer getPort() {
		return mPort;
	}

	public void setPort(Integer _port) {
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
		mPort = new Integer(_data[5]);
	}
}
