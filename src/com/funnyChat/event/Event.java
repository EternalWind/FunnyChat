package com.funnyChat.event;

public abstract class Event {
	private String mEventType;
	private Integer[] mMemoryIds;
	private boolean mIsLocal;
	public String getEventType() {
		return mEventType;
	}
	public void setEventType(String _eventType) {
		this.mEventType = _eventType;
	}
	public Integer[] getMemoryIds() {
		return mMemoryIds;
	}
	public void setMemoryIds(Integer[] _memoryIds) {
		this.mMemoryIds = _memoryIds;
	}
	public Boolean isLocal(){
		return mIsLocal;
	}
	public void setIsLocal(boolean _isLocal){
		this.mIsLocal = _isLocal;
	}
	public Integer serialize(byte[] _byte_arr){
		return 1;
	}
	public boolean unserialize(byte[] _byte_arr){
		return false;
	}
}
