package com.funnyChat.plugin;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {
	private List<Plugin> mPlugins;
	private static PluginManager mPluginManager;
	private PluginManager(){
		mPlugins = new ArrayList<Plugin>();
	}
	public static PluginManager getInstance(){
		if(mPluginManager == null){
			mPluginManager = new PluginManager();
		}
		return mPluginManager;
	}
	public Boolean insert(Plugin _plugin){
		mPlugins.add(_plugin);
		return true;
	}
	public Boolean remove(Integer _id){
		for(int i=0;i<mPlugins.size();i++){
			if(mPlugins.get(i).getID().equals(_id)){
				mPlugins.remove(i);
				return true;
			}
		}
		return false;
	}
	public Boolean removeAll(){
		return mPlugins.removeAll(new ArrayList());
	}
	public Plugin get(Integer _id){
		for(int i=0;i<mPlugins.size();i++){
			if(mPlugins.get(i).getID().equals(_id)){
				return mPlugins.get(i);
			}
		}
		return null;
	}
	public List<Plugin> getmPlugins() {
		return mPlugins;
	}
	public Boolean set(Integer _id, Plugin _plugin){
		remove(_id);
		insert(_plugin);
		return true;
	}
	public void enable(Integer[] _ids){
		for(int i=0;i<_ids.length;i++){
			for(int j=0;j<mPlugins.size();j++){
				if(mPlugins.get(j).getID().equals(_ids[i]))
					mPlugins.get(j).onDisable();
			}
		}
	}
	public void enableAll(){
		for(int j=0;j<mPlugins.size();j++){
			mPlugins.get(j).onEnable();
		}
	}
	public void disable(Integer[] _ids){
		for(int i=0;i<_ids.length;i++){
			for(int j=0;j<mPlugins.size();j++){
				if(mPlugins.get(j).getID().equals(_ids[i]))
					mPlugins.get(j).onDisable();
			}
		}
	}
	public void disableAll(){
		for(int j=0;j<mPlugins.size();j++){
			mPlugins.get(j).onDisable();
		}
	}
}
