package com.funnyChat.event;

public class ChatEvent extends Event {
	private long mFrom;
	private boolean mIsReceived;
	private long mTo;
	
	public ChatEvent() {
		mFrom = mTo = 0;
		mIsReceived = false;
	}
	
	public void setSenderId(long _sender_id) {
		mFrom = _sender_id;
	}
	
	public void setReceiverId(long _receiver_id) {
		mTo = _receiver_id;
	}
	
	public void setIsReceived(boolean _is_received) {
		mIsReceived = _is_received;
	}
	
	public boolean getIsReceived() {
		return mIsReceived;
	}
	
	public long getSenderId() {
		return mFrom;
	}
	
	public long getReceiverId() {
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
