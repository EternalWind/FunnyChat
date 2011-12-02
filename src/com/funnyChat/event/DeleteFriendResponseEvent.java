package com.funnyChat.event;

import com.funnyChat.server.Server;

public class DeleteFriendResponseEvent extends Event {

	private String mResult;

	public DeleteFriendResponseEvent(Server _server, long _uid1, long _uid2) {
		if (_server.deleteFriend(_uid1, _uid2))
			mResult = "Succeed";
		else
			mResult = "Failed";
	}

	public String getResult() {
		return mResult;
	}

	@Override
	public String getEventType() {
		return "DeleteFriendResponseEvent";
	}

	@Override
	protected String onSerialize() {
		return mResult;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		mResult = dataStr;
	}
}
