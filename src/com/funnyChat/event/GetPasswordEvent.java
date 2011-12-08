package com.funnyChat.event;

public class GetPasswordEvent extends Event {
	String _answer;
	
	public GetPasswordEvent(String _answer_str){
		_answer = _answer_str;
	}
	
	public void setAnswer(String _answer_str){
		_answer = _answer_str;
	}
	
	public String getAnswer(){
		return _answer;
	}
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "GetPasswordEvent";
	}
	

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return _answer;
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		_answer = _data_str;
	}

}
