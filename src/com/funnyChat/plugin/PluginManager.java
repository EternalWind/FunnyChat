package com.funnyChat.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import com.funnyChat.event.*;

public class PluginManager {
	private HashMap<Integer, Plugin> mPlugins;
	private static PluginManager mInstance;
	private Integer mIdCount;
	private String mDir;
	
	private static class PluginFilter implements FileFilter{
		private static String mSuffix = "plu";
		
		public boolean accept(File _path_name){
			return _path_name.getName().endsWith(mSuffix);
		}
		
		public static String getSuffix(){
			return mSuffix;
		}
	}
	
	private PluginManager(String _directory){
		mPlugins = new HashMap<Integer, Plugin>();
		mIdCount = 0;
		mDir = _directory;
	}
	
	private Integer generateId(){
		return mIdCount++;
	}
	
	public void scan(){
		try{
			File _plugin_dir = new File(mDir);
			String _plugin_name;
			
			for(File _plugin_file : _plugin_dir.listFiles(new PluginFilter())){
				_plugin_name = _plugin_file.getName();
				_plugin_name = _plugin_name.substring(0, _plugin_name.length() - 
						PluginFilter.getSuffix().length());
				Plugin _plugin = (Plugin)Class.forName(_plugin_name).newInstance();
				if(!mPlugins.containsValue(_plugin)){
					mPlugins.put(generateId(), _plugin);
				}
			}
		}
		catch(Exception e){
			//Logger...
		}
	}
	
	public static void initialize(String _directory){
		if(mInstance == null){
			mInstance = new PluginManager(_directory);

			mInstance.scan();
		}
	}
	public static void initialize(){
		initialize("Plugin");
	}
	/*  Aborted
	public int getCount(){
		return mIdCount;
	}
	*/
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
	/*   Aborted
	public Boolean insert(Plugin _plugin){
		if(mPlugins.containsValue(_plugin)){
			return false;
		}
		else{
			mPlugins.put(generateId(), _plugin);
			return true;
		}
	}*/
	public Boolean remove(Integer _id){
		Plugin _plugin = mPlugins.remove(_id);
		if(_plugin == null){
			return false;
		}
		_plugin.destroy();
		
		return true;
	}
	public Boolean remove(Plugin _plugin){
		Integer _id = getId(_plugin);
		return remove(_id);
	}
	public Boolean removeAll(){
		for(Plugin _plugin : mPlugins.values()){
			_plugin.destroy();
		}
		mPlugins.clear();
		return true;
	}
	public Integer getId(Plugin _plugin){
		for(Map.Entry<Integer, Plugin> _item : mPlugins.entrySet()){
			if(_item.getValue().equals(_plugin)){
				return _item.getKey();
			}
		}
		
		return -1;
	}
	public Plugin get(Integer _id){
		return mPlugins.get(_id);
	}
	public Collection<Plugin> getPlugins() {
		return mPlugins.values();
	}
	/*   Aborted
	public Boolean set(Integer _id, Plugin _plugin){
		mPlugins.put(_id, _plugin);
		return true;
	}
	*/
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
