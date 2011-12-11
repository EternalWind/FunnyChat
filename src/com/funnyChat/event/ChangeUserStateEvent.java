package com.funnyChat.event;

public class ChangeUserStateEvent extends Event {
	
	private long mUId;
	private String mState;
	
	public ChangeUserStateEvent() {
		mIsLocal = false;
		mUId = 0;
		mState = "";
	}

	public long getUId() {
		return mUId;
	}

	public void setUId(long _uid) {
		this.mUId = _uid;
	}

	public String getState() {
		return mState;
	}

	public void setState(String _state) {
		this.mState = _state;
	}

	@Override
	public String getEventType() {
		return "ChangeUserStateEvent";
	}

	@Override
	protected String onSerialize() {
		return mUId + " " + mState;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mUId = Long.parseLong(_data[0]);
		mState = _data[1];
	}
}
