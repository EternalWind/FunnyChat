package com.funnyChat.plugin;

import java.awt.Panel;

import com.funnyChat.event.Event;

public abstract class Plugin {
	private Integer mID;
	Plugin(Integer _id){
		mID = _id;
	} 
	public abstract void onCreate(Panel _ref);
	public abstract void onDestroy();
	public abstract void onEnable();
	public abstract void onDisable();
	public abstract void handleEvent(Event localEvent);
	public Integer getID() {
		return mID;
	}
}
