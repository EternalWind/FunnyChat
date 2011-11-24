package com.funnyChat.event;

public class ConnectedEvent extends Event {
	private Boolean mIsInitiative;
	
	public ConnectedEvent(boolean _is_initiative) {
		mIsInitiative = _is_initiative;
		mIsLocal = true;
	}
	
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "ConnectedEvent";
	}
	
	public boolean isInitiative() {
		return mIsInitiative;
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
	}

}
