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
	public Byte[] getmContent() {
		return mContent;
	}
	public void setmContent(Byte[] mContent) {
		this.mContent = mContent;
	}
	public Boolean getmIsAvaliable() {
		return mIsAvaliable;
	}
	public void setmIsAvaliable(Boolean mIsAvaliable) {
		this.mIsAvaliable = mIsAvaliable;
	}
	public Integer getmID() {
		return mID;
	}
	/**
	 * 限定为包内访问
	 * @param _id
	 */
	void setmID(Integer _id){
		mID = _id;
	}
	

}
