package com.funnyChat.event;


public class ReleasePluginResponseEvent extends Event {
	private String mResult;
	public String getResult() {
		return mResult;
	}

	public void setResult(String _Result) {
		this.mResult = _Result;
	}

	public ReleasePluginResponseEvent() {
	}

	@Override
	public String getEventType() {
		return "ReleasePluginResponseEvent";
	}

	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		return mResult;
	}

	@Override
	protected void onUnserialize(String dataStr) {
		// TODO Auto-generated method stub
		mResult = dataStr;
	}
}
