package com.funnyChat.event;

public class PingEvent extends Event {
	private Boolean mIsDone;
	
	public PingEvent(int _target) {
		mIsLocal = false;
		mIsDone = false;
		mTarget = _target;
	}

	public void finishPing() {
		mIsDone = true;
	}
	
	public boolean isFinished(){
		return mIsDone;
	}
	
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "PingEvent";
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return mIsDone.toString();
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		mIsDone = Boolean.parseBoolean(_data_str);
	}

}
