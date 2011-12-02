package com.funnyChat.event;

public class GetPluginListEvent extends Event {

	public GetPluginListEvent() {
	}

	@Override
	public String getEventType() {
		return "GetPluginListEvent";
	}

	@Override
	protected String onSerialize() {
		return "";
	}

	@Override
	protected void onUnserialize(String dataStr) {
	}
}
