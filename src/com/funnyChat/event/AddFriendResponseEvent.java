package com.funnyChat.event;

public class AddFriendResponseEvent extends Event {

	private String mResult;
	
//	public AddFriendResponseEvent(Server _server,
//			long _uid1, long _uid2) {
//		if (_server.addFriend(_uid1, _uid2))
//			mResult = "Succeed";
//		else
//			mResult = "Failed";
//	}
	public AddFriendResponseEvent(){
		mResult = "Failed";
	}

	public void setResult(String mResult) {
		this.mResult = mResult;
	}

	public String getResult() {
		return mResult;
	}

	@Override
	public String getEventType() {
		return "AddFriendResponseEvent";
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
