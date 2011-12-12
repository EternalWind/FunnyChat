package com.funnyChat.event;

import com.funnyChat.db.PluginInfo;


public class ReleasePluginEvent extends Event {
	
	private PluginInfo mPluginInfo;
	private byte[] mPlugins;
	
	public ReleasePluginEvent() {
		mPluginInfo = new PluginInfo();
		mPlugins = null;
	}

	public PluginInfo getPluginInfo() {
		return mPluginInfo;
	}

	public void setPluginInfo(PluginInfo _pluginInfo) {
		this.mPluginInfo = _pluginInfo;
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
		return mPluginInfo.toString()+String.valueOf(mPlugins);
	}

	@Override
	protected void onUnserialize(String dataStr) {
		int index = dataStr.indexOf("]");
		mPluginInfo = PluginInfo.parse(dataStr.substring(0, index+1));
		mPlugins = dataStr.substring(index+1).getBytes();
	}
}
