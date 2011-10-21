package com.funnyChat.plugin;

import java.util.HashMap;

public class PluginManager {
	private HashMap<Integer, Plugin> mPlugins;
	private static PluginManager mPluginManager;
	private PluginManager(){
		mPlugins = new HashMap<Integer, Plugin>();
	}
	public static void initialzie(){
		if(mPluginManager == null){
			mPluginManager = new PluginManager();
		}
	}
	public void deInitialize(){
		
	}
	public static PluginManager getInstance(){
		return mPluginManager;
	}
	public Boolean insert(Plugin _plugin){
		if(_plugin.getID() == null)
		    mPlugins.put(_plugin.getID(), _plugin);
		else
			mPlugins.put(_plugin.getID(), _plugin);
		return true;
	}
	public Boolean remove(Integer _id){
		for(Integer key : mPlugins.keySet()){
			if(mPlugins.get(key).getID().equals(_id)){
				mPlugins.remove(key);;
				return true;
			}
		}
		return false;
	}
	public Boolean removeAll(){
		mPlugins.clear();
		return true;
	}
	public Plugin get(Integer _id){
		for(Integer key : mPlugins.keySet()){
			if(mPlugins.get(key).getID().equals(_id)){
				return mPlugins.get(key);
			}
		}
		return null;
	}
	public HashMap<Integer, Plugin> getPlugins() {
		return mPlugins;
	}
	public Boolean set(Integer _id, Plugin _plugin){
		mPlugins.put(_id, _plugin);
		return true;
	}
	public void enable(Integer[] _ids){
		for(int i=0;i<_ids.length;i++){
			for(Integer key : mPlugins.keySet()){
				if(mPlugins.get(key).getID().equals(_ids[i])){
					mPlugins.get(key).onEnable();
				}
			}
		}
	}
	public void enableAll(){
		for(Integer key : mPlugins.keySet()){
				mPlugins.get(key).onEnable();
		}
	}
	public void disable(Integer[] _ids){
		for(int i=0;i<_ids.length;i++){
			for(Integer key : mPlugins.keySet()){
				if(mPlugins.get(key).getID().equals(_ids[i])){
					mPlugins.get(key).onDisable();
				}
			}
		}
	}
	public void disableAll(){
		for(Integer key : mPlugins.keySet()){
			mPlugins.get(key).onDisable();
	    }
	}
}
