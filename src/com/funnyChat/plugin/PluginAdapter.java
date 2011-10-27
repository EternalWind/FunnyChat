package com.funnyChat.plugin;

import java.awt.Panel;

import com.funnyChat.event.Event;

public class PluginAdapter extends Plugin{

	public PluginAdapter(Panel _ref){
		super(_ref);
	}
	@Override
	protected void onCreate(Panel _ref) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRun() {
		// TODO Add your processing logic here
	}

	@Override
	protected boolean isInterested(Event _event){
		//Always interested
		return true;
	}
	
}
