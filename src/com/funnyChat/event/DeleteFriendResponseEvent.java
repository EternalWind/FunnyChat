package com.funnyChat.event;

import com.funnyChat.server.Server;

public class DeleteFriendResponseEvent extends Event {

	private String mResult;

	public DeleteFriendResponseEvent(){
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
		return "DeleteFriendResponseEvent";
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
