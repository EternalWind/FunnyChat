package com.funnyChat.plugin;

import java.awt.Panel;
import com.funnyChat.Thread.*;
import com.funnyChat.event.Event;

public abstract class Plugin extends FCThread {
	private boolean mIsEnabled;
	private boolean mIsBusy;
	protected Event mEvent;
	protected Panel mPanel;
	public Plugin(){
		mIsEnabled = false;
		mEvent = null;
		mIsBusy = false;
		mPanel = null;
	}
	protected abstract void onEnable();
	protected abstract void onDisable();
	protected abstract void execute();
	protected abstract boolean isInterested(Event _event);
	final protected void onRun(){
		if(mIsEnabled){
			execute();
		}
	}
	final public boolean equals(Object _obj){
		if(_obj.getClass() == this.getClass()){
			return true;
		}
		else{
			return false;
		}
	}
	public void enable(){
		if(mIsEnabled == false){
			mIsEnabled = true;
			if(!this.isAlive())
				this.start();
			onEnable();
		}
	}
	public void disable(){
		if(mIsEnabled == true){
			mIsEnabled = false;
			onDisable();
		}
	}
	public boolean isEnabled(){
		return mIsEnabled;
	}
	public void destroy(){
		terminate();
		onDestroy();
	}
	public Panel getPanel(){
		return mPanel;
	}
	public void setPanel(Panel _panel){
		mPanel = _panel;
	}
	public abstract void onCreate();
	protected abstract void onDestroy();
	//处理事件前应先检查是否有事件要处理
	protected boolean hasWork(){
		return mIsBusy;
	}
	//完成事件的处理后应调用此函数告知插件管理器处理已完成
	protected void doneWork(){
		mIsBusy = false;
	}
	public boolean handleEvent(Event _localEvent){
		if(isInterested(_localEvent) && !_localEvent.equals(mEvent) && mIsEnabled){  //确保本插件对该事件感兴趣且还未处理过
			if(mIsBusy){
				return false;
			}
			else{
				mIsBusy = true;
				mEvent = _localEvent;
				return true;
			}
		}
		else{
			return true;
		}
	}
}
