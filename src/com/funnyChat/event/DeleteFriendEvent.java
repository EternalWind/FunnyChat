package com.funnyChat.event;

public class DeleteFriendEvent extends Event {
	
	private long mUId1;
	private long mUId2;
	
	public DeleteFriendEvent() {
		mUId1 = 0;
		mUId2 = 0;
	}

	public long getUId1() {
		return mUId1;
	}

	public void setUId1(long _uid) {
		this.mUId1 = _uid;
	}

	public long getUId2() {
		return mUId2;
	}

	public void setUId2(long _uid) {
		this.mUId2 = _uid;
	}

	@Override
	public String getEventType() {
		return "DeleteFriendEvent";
	}

	@Override
	protected String onSerialize() {
		return mUId1 + " " + mUId2;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _uids = dataStr.split(" ");
		mUId1 = Long.parseLong(_uids[0]);
		mUId2 = Long.parseLong(_uids[1]);
	}
}
