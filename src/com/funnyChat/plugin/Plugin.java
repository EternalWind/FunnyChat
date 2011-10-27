package com.funnyChat.plugin;

import java.awt.Panel;
import com.funnyChat.Thread.*;
import com.funnyChat.event.Event;

public abstract class Plugin extends FCThread {
	private boolean mIsEnabled;
	private boolean mIsBusy;
	protected Event mEvent;
	public Plugin(Panel _ref){
		mIsEnabled = true;
		mEvent = null;
		mIsBusy = false;
		onCreate(_ref);
	}
	protected abstract void onEnable();
	protected abstract void onDisable();
	protected abstract boolean isInterested(Event _event);
	public void enable(){
		if(mIsEnabled == false){
			mIsEnabled = true;
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
		onDestroy();
	}
	protected abstract void onCreate(Panel _ref);
	protected abstract void onDestroy();
	//�����¼�ǰӦ�ȼ���Ƿ����¼�Ҫ����
	protected boolean hasWork(){
		return mIsBusy;
	}
	//����¼��Ĵ����Ӧ���ô˺�����֪������������������
	protected void doneWork(){
		mIsBusy = false;
	}
	public boolean handleEvent(Event _localEvent){
		if(isInterested(_localEvent) && !_localEvent.equals(mEvent)){  //ȷ��������Ը��¼�����Ȥ�һ�δ�����
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
