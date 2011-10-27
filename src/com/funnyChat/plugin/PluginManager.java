package com.funnyChat.plugin;

import java.util.HashMap;
import com.funnyChat.event.*;

public class PluginManager {
	private HashMap<Integer, Plugin> mPlugins;
	private static PluginManager mInstance;
	private Integer mIdCount;
	private PluginManager(){
		mPlugins = new HashMap<Integer, Plugin>();
		mIdCount = 0;
	}
	
	private Integer generateId(){
		return mIdCount++;
	}
	
	public static void initialize(){
		if(mInstance == null){
			mInstance = new PluginManager();
		}
	}
	public void deinitialize(){
		if(mInstance != null){
			removeAll();
			mPlugins = null;
			mInstance = null;
		}
	}
	public static PluginManager getInstance(){
		return mInstance;
	}
	public Boolean insert(Plugin _plugin){
		if(mPlugins.containsValue(_plugin)){
			return false;
		}
		else{
			mPlugins.put(generateId(), _plugin);
			return true;
		}
	}
	public Boolean remove(Integer _id){
		if(mPlugins.remove(_id) == null){
			return false;
		}
		return true;
	}
	public Boolean removeAll(){
		mPlugins.clear();
		return true;
	}
	public Plugin get(Integer _id){
		return mPlugins.get(_id);
	}
	public HashMap<Integer, Plugin> getPlugins() {
		return mPlugins;
	}
	public Boolean set(Integer _id, Plugin _plugin){
		mPlugins.put(_id, _plugin);
		return true;
	}
	public void enable(Integer[] _ids){
		Plugin _plugin;
		for(int i=0;i<_ids.length;i++){
			_plugin = mPlugins.get(_ids[i]);
			
			if(_plugin != null){
				_plugin.enable();
			}
		}
	}
	public void enableAll(){
		for(Plugin _plugin : mPlugins.values()){
				_plugin.enable();
		}
	}
	public void disable(Integer[] _ids){
		Plugin _plugin;
		for(int i=0;i<_ids.length;i++){
			_plugin = mPlugins.get(_ids[i]);
			
			if(_plugin != null){
				_plugin.disable();
			}
		}
	}
	public void disableAll(){
		for(Plugin _plugin : mPlugins.values()){
			_plugin.disable();
		}
	}
	public boolean handleEvent(Event _event){
		boolean _result = true;
		for(Plugin _plugin : mPlugins.values()){
			if(_plugin.isEnabled()){
				if(!_plugin.handleEvent(_event)){
					_result = false;
				}
			}
		}
		
		return _result;
	}
}
