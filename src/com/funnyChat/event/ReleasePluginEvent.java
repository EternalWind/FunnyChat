package com.funnyChat.event;


public class ReleasePluginEvent extends Event {

	private byte[] mPlugins;
	
	public ReleasePluginEvent(byte[] _plugins) {
		mPlugins = _plugins;
	}

	public byte[] getPlugins() {
		return mPlugins;
	}

	public void setPlugins(byte[] _plugins) {
		this.mPlugins = _plugins;
	}

	@Override
	public String getEventType() {
		return "ReleasePluginEvent";
	}

	@Override
	protected String onSerialize() {
		return String.valueOf(mPlugins);
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mPlugins = dataStr.getBytes();
	}
}
