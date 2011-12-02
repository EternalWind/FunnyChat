package com.funnyChat.event;

import com.funnyChat.server.Server;

public class GetIpAndPortResponseEvent extends Event {

	private String mIp;
	private String mPort;
	
	public GetIpAndPortResponseEvent(Server _server,long _user_id) {
		String[] _ip_port = _server.getIpAndPort(_user_id).split(":");
		mIp = _ip_port[0].trim();
		mPort = _ip_port[1].trim();
	}

	@Override
	public String getEventType() {
		return "GetIpAndPortResponseEvent";
	}

	@Override
	protected String onSerialize() {
		return mIp + " " + mPort;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mIp = _data[0];
		mPort = _data[1];
	}
}
