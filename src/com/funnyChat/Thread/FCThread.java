package com.funnyChat.Thread;

import com.funnyChat.core.*;
import com.funnyChat.utils.Log.LogType;

public abstract class FCThread extends Thread {
	private boolean mIsAlive = true;
	protected long mSleepTime = 500;
	
	protected abstract void onRun();
	
	final public void run(){
		while(mIsAlive){
			onRun();
			try {
				Thread.sleep(mSleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Core.getLogger().addLog("Thread " + this.getId() + " has been interrupted.", LogType.WARNING);
				mIsAlive = false;
			}
		}
	}
	
	final public void terminate(){
		mIsAlive = false;
	}
	
	public long getSleepTime() {
		return mSleepTime;
	}
	
	public void setSleepTime(long _sleep_time) {
		mSleepTime = _sleep_time;
	}
}
