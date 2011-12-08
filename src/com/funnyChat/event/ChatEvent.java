package com.funnyChat.event;

public class ChatEvent extends Event {
	private Integer mFrom;
	private boolean mIsReceived;
	private Integer mTo;
	
	public boolean getIsReceived() {
		return mIsReceived;
	}
	
	public Integer getSenderId() {
		return mFrom;
	}
	
	public Integer getReceiverId() {
		return mTo;
	}
	
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "ChatEvent";
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return "data";
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub

	}

}
