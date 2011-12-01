package com.funnyChat.plugin;

import com.funnyChat.event.Event;

public class PluginAdapter extends Plugin{
	@Override
	public void onCreate() {}

	@Override
	protected void onDestroy() {}

	@Override
	protected void onEnable() {}

	@Override
	protected void execute() {}
	
	@Override
	protected void onDisable() {}

	@Override
	protected boolean isInterested(Event _event){
		//Always interested
		return true;
	}
	
}
