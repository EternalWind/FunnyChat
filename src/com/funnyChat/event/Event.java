package com.funnyChat.event;

public abstract class Event {
	private String mEventType;
	private Integer[] mMemoryIds;
	public String getmEventType() {
		return mEventType;
	}
	public void setmEventType(String mEventType) {
		this.mEventType = mEventType;
	}
	public Integer[] getmMemoryIds() {
		return mMemoryIds;
	}
	public void setmMemoryIds(Integer[] mMemoryIds) {
		this.mMemoryIds = mMemoryIds;
	}
	public abstract Boolean isLocal();
}
