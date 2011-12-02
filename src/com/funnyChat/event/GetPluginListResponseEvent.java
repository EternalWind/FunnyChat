package com.funnyChat.event;

import java.util.List;

import com.funnyChat.db.PluginInfo;
import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class GetPluginListResponseEvent extends MessageEvent {
	
	public GetPluginListResponseEvent(Connection _target, Server _server) {
		super(_target);
		List<PluginInfo> _plugins_info = _server.getPluginList();
		data.put("MessageType", "GetPluginListResponseEvent");
		int i = 0;
		for (PluginInfo _plugin_info : _plugins_info) {
			data.put("Plugin_" + i++, _plugin_info.getPid() + ":"
					+ _plugin_info.getName());
		}
	}

	@Override
	public String getEventType() {
		return "GetPluginListResponseEvent";
	}
}
