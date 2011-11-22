package com.funnyChat.memory;

public class Memory {
	private Byte[] mContent;
	private Boolean mIsAvailable;
	private Integer mSize;       //A virtual size.
	
	public Memory(Integer _size){
		mIsAvailable = true;
		mSize = _size;
		mContent = new Byte[mSize];
	}
	public Integer getSize() {
		return mSize;
	}
	public boolean allocate(Integer _size) {
		if(mIsAvailable && mContent.length >= _size) {
			mSize = _size;
			mIsAvailable = false;
			return true;
		}
		return false;
	}
	public Memory regenerate(Integer _size) {
		if(mIsAvailable){
			mContent = new Byte[_size];
			mSize = _size;
		}
		
		return this;
	}
	public Byte[] getContent() {
		if(!mIsAvailable){
			return mContent;
		}
		return null;
	}
	public Boolean getIsAvailable() {
		return mIsAvailable;
	}
	public void release() {
		if(!mIsAvailable){
			mIsAvailable = true;
		}
	}
}
