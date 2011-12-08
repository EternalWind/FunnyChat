package com.funnyChat.event;

public class GetPasswordResponseEvent extends Event{
	String _password;
	public GetPasswordResponseEvent(){
		_password = "";
	}
	
	public void setPassword(String _password_str){
		_password = _password_str;
	}
	
	public String getPassword(){
		return _password;
	}
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "GetPasswordResponseEvent";
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return _password;
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		_password = _data_str;
	}

}
