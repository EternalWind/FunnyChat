package com.funnyChat.memory;

public class Memory {
	private Byte[]mContent;
	private Boolean mIsAvaliable;
	
	public Memory(){
		
	}
	public Byte[] getContent() {
		return mContent;
	}
	public void setContent(Byte[] mContent) {
		this.mContent = mContent;
	}
	public Boolean getIsAvaliable() {
		return mIsAvaliable;
	}
	public void setIsAvaliable(Boolean mIsAvaliable) {
		this.mIsAvaliable = mIsAvaliable;
	}
}
