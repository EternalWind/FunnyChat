package com.funnyChat.event;

public class ChatEvent extends Event {
	private long mFrom;
	private boolean mIsReceived;
	private long mTo;
	private String data;
	
	public boolean setData(String _data){
		data = _data;
		return true;
	}

	public String getData(){
		return data;
	}
	
	public boolean getIsReceived() {
		return mIsReceived;
	}
	
	public long getSenderId() {
		return mFrom;
	}
	
	public boolean setSenderId(long _sId) {
		mTo = _sId;
		return true;
	}

	public boolean setReceiverId(long _rId) {
		mTo = _rId;
		return true;
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
		return data;
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		data = _data_str;
	}

}
