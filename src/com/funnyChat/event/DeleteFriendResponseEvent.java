package com.funnyChat.event;

public class DeleteFriendResponseEvent extends Event {

	private String mResult;

	public DeleteFriendResponseEvent(){
		mIsLocal = false;
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
