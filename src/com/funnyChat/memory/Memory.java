package com.funnyChat.memory;

public class Memory {
	private byte[] mContent;
	private Boolean mIsAvaliable;
	private Integer mSize;       //A virtual size.
	
	public Memory(Integer _size){
		mIsAvaliable = true;
		mSize = _size;
		mContent = new byte[mSize];
	}
	public Integer getSize() {
		return mSize;
	}
	public boolean setSize(Integer _size) {
		if(mContent.length >= _size) {
			mSize = _size;
			return true;
		}
		return false;
	}
	public void regenerate(Integer _size) {
		if(mIsAvaliable){
			mContent = new byte[_size];
			mSize = _size;
		}
	}
	public byte[] getContent() {
		if(!mIsAvaliable){
			return mContent;
		}
		return null;
	}
	public Boolean getIsAvaliable() {
		return mIsAvaliable;
	}
	public void release() {
		mIsAvaliable = true;
	}
	public void use() {
		mIsAvaliable = false;
	}
}
