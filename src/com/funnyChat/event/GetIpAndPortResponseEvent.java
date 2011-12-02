package com.funnyChat.event;

import com.funnyChat.network.Connection;
import com.funnyChat.server.Server;

public class GetIpAndPortResponseEvent extends MessageEvent {

	public GetIpAndPortResponseEvent(Connection _target,Server _server,long _user_id) {
		super(_target);
		data.put("MessageType", "GetIpAndPortResponseEvent");
		String[] _ip_port = _server.getIpAndPort(_user_id).split(":");
		data.put("Ip", _ip_port[0].trim());
		data.put("Port", _ip_port[1].trim());
	}
}
