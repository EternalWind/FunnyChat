package com.funnyChat.event;

import com.funnyChat.db.UserInfo;

public class RefreshUserInfoEvent extends Event {

	private UserInfo mUserInfo;

	public RefreshUserInfoEvent() {
		mUserInfo = new UserInfo();
	}

	public UserInfo getUserInfo() {
		return mUserInfo;
	}

	public void setUserInfo(UserInfo _user_info) {
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
		String str = _data[0];
		System.out.println(str);
		long l = Long.parseLong(str);
		mUserInfo.setUid(l);
		mUserInfo.setName(_data[1]);
		mUserInfo.setPassword(_data[2]);
		mUserInfo.setState(_data[3]);
		mUserInfo.setIp(_data[4]);
		mUserInfo.setPort(_data[5]);
	}
	public static void main(String[]args){
		System.out.println(Long.parseLong("20"));
	}
}
