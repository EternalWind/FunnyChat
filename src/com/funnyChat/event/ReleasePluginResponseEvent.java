package com.funnyChat.event;


public class ReleasePluginResponseEvent extends Event {
	
	public ReleasePluginResponseEvent() {
	}

	@Override
	public String getEventType() {
		return "ReleasePluginResponseEvent";
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		// TODO Auto-generated method stub
		
	}
}
