package com.funnyChat.plugin;

import javax.swing.JDialog;

import com.funnyChat.core.Core;
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
	
	@Override
	public String getPluginName() {
		return "Unnamed Plugin";
	}
	
	@Override
	public JDialog getConfigPanel() {
		return new DefaultPluginConfig(Core.getMainWindow(), true);
	}
}
