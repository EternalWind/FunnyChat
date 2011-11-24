package com.funnyChat.event;

import java.util.*;
import com.funnyChat.network.*;

public abstract class Event {
	//private String mEventType;           Aborted
	//private Integer[] mMemoryIds;        Aborted
	protected boolean mIsLocal;
	protected Connection mSource = null;
	protected Connection mTarget = null;          //The target host id. Only used for network event.
	protected UUID mId = UUID.randomUUID();    //For local usage.
	public boolean equals(Event _event){
		return mId.equals(_event.mId);
	}
	public abstract String getEventType();
	/*  Aborted
	 * public String getEventType() {
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
	}*/
	public boolean isLocal(){
		return mIsLocal;
	}
	public Connection getTarget(){
		return mTarget;
	}
	public void setTarget(Connection _target){
		mTarget = _target;
	}
	public void setIsLocal(boolean _isLocal){
		this.mIsLocal = _isLocal;
	}
	public byte[] serialize(){
		String _data_str = getEventType();
		_data_str += "\n";
		_data_str += onSerialize();
		
		return _data_str.getBytes();
	}
	public boolean unserialize(byte[] _byte_arr){
		String _data_str = new String(_byte_arr);
		String _type = getEventType();
		
		//Check the type.
		if(_data_str.indexOf(_type) == 0){
			//Type matched!
			onUnserialize(_data_str.substring(_type.length(), _data_str.length()));
			
			return true;
		}
		
		//Type not matched!
		return false;
	}
	public Connection getSource(){
		return mSource;
	}
	public void setSource(Connection _source){
		mSource = _source;
	}
	protected abstract String onSerialize();
	protected abstract void onUnserialize(String _data_str);
}
