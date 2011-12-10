package com.funnyChat.event;

public class ConnectionFailedEvent extends Event {
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "ConnectionFailedEvent";
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
