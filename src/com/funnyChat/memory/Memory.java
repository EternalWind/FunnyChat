package com.funnyChat.memory;

public class Memory {
	private Integer mID;
	private Byte[]mContent;
	private Boolean mIsAvaliable;
	
	public Memory(){
		
	}
	public Memory(Integer _id){
		mID = _id;
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
	public Integer getID() {
		return mID;
	}
	/**
	 * 限定为包内访问
	 * @param _id
	 */
	void setID(Integer _id){
		mID = _id;
	}
	

}
