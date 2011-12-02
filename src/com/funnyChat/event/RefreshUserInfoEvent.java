package com.funnyChat.event;

import com.funnyChat.db.UserInfo;

public class RefreshUserInfoEvent extends Event {

	private UserInfo mUserInfo;

	public RefreshUserInfoEvent(UserInfo _user_info) {
		mUserInfo = _user_info;
	}

	public UserInfo getUserInfo() {
		return mUserInfo;
	}

	public void setmUserInfo(UserInfo _user_info) {
		this.mUserInfo = _user_info;
	}

	@Override
	public String getEventType() {
		return "RefreshUserInfoEvent";
	}

	@Override
	protected String onSerialize() {
		return mUserInfo.getUid() + " " + mUserInfo.getName() + " "
				+ mUserInfo.getPassword() + " " + mUserInfo.getState() + " "
				+ mUserInfo.getIp() + " " + mUserInfo.getPort();
	}

	@Override
	protected void onUnserialize(String dataStr) {
		String[] _data = dataStr.split(" ");
		mUserInfo.setUid(Long.parseLong(_data[0]));
		mUserInfo.setName(_data[1]);
		mUserInfo.setPassword(_data[2]);
		mUserInfo.setState(_data[3]);
		mUserInfo.setIp(_data[4]);
		mUserInfo.setPort(_data[5]);
	}
}
