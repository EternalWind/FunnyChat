package com.funnyChat.event;

import java.util.List;

import com.funnyChat.db.PluginInfo;
import com.funnyChat.server.Server;

public class GetPluginListResponseEvent extends Event {

	private List<PluginInfo> mPluginsInfo;

	public GetPluginListResponseEvent(Server _server) {
		mPluginsInfo = _server.getPluginList();
	}

	public List<PluginInfo> getPluginsInfo() {
		return mPluginsInfo;
	}

	public void setPluginsInfo(Server _server) {
		this.mPluginsInfo = _server.getPluginList();
	}

	@Override
	public String getEventType() {
		return "GetPluginListResponseEvent";
	}

	@Override
	protected String onSerialize() {
		String _plugins = String.valueOf(mPluginsInfo.size());
		for (PluginInfo _plugin_info : mPluginsInfo) {
			_plugins += " " + _plugin_info.getPid() + " "
					+ _plugin_info.getName();
		}
		return _plugins;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mPluginsInfo.clear();
		String[] _data = dataStr.split(" ");
		int _size = Integer.parseInt(_data[0]);
		PluginInfo[] _plugins_info = new PluginInfo[2 * _size];
		for (int i = 0; i != _size; i += 2) {
			_plugins_info[i].setPid(Long.parseLong(_data[i + 1]));
			_plugins_info[i].setName(_data[i + 2]);
			mPluginsInfo.add(_plugins_info[i]);
		}
	}
}
