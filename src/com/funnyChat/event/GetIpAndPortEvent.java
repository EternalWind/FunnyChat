package com.funnyChat.event;


public class GetIpAndPortEvent extends Event {

	private long mUId;
	
	public GetIpAndPortEvent(long _user_id) {
		mUId = _user_id;
	}

	public long getUId() {
		return mUId;
	}

	public void setUId(long _uid) {
		this.mUId = _uid;
	}

	@Override
	public String getEventType() {
		return "GetIpAndPortEvent";
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
