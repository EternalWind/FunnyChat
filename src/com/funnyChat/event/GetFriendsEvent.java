package com.funnyChat.event;


public class GetFriendsEvent extends Event {
	
	private long mUId;
	
	public GetFriendsEvent() {
		mIsLocal = false;
		mUId = 0;
	}

	public long getUId() {
		return mUId;
	}

	public void setUId(long _uid) {
		this.mUId = _uid;
	}

	@Override
	public String getEventType() {
		return "GetFriendsEvent";
	}

	@Override
	protected String onSerialize() {
		return String.valueOf(mUId);
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mUId = Long.parseLong(dataStr);
	}
}
