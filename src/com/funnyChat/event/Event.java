package com.funnyChat.event;

import java.util.*;

public abstract class Event {
	private String mEventType;
	private Integer[] mMemoryIds;
	private boolean mIsLocal;
	private UUID mId = UUID.randomUUID();
	public boolean equals(Event _event){
		return mId.equals(_event.mId);
	}
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
	public byte[] serialize(){
		
	}
	public boolean unserialize(byte[] _byte_arr){
		return false;
	}
}
