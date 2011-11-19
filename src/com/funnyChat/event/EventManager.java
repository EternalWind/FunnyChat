package com.funnyChat.event;

import java.util.LinkedList;
import java.util.Queue;
import com.funnyChat.network.*;
import com.funnyChat.Thread.*;

import com.funnyChat.plugin.PluginManager;

public class EventManager extends FCThread{
	private Queue<Event> mLEvent;
	private Queue<Event> mNEvent;
	private LinkedList<Event> mEventPrototype;
	private static EventManager mInstance;
	public static void initialize(){
		if(mInstance == null){
			mInstance = new EventManager();
		}
	}
	public synchronized void deinitialize(){
		if(mInstance != null){
			terminate();
			mLEvent.clear();
			mNEvent.clear();
			mEventPrototype.clear();
			mLEvent = null;
			mNEvent = null;
			mInstance = null;
			mEventPrototype = null;
		}
	}
	public static EventManager getInstance(){
		return mInstance;
	}
	private EventManager(){
		mLEvent = new LinkedList<Event>();
		mNEvent = new LinkedList<Event>();
		mEventPrototype = new LinkedList<Event>();
	}
	protected void onRun(){
		try {
			handleLocalEvent();
			handleNetworkEvent();
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void enqueue(Event _event){
		boolean _is_registered = false;
		
		//Check if has registered or not
		for(Event _e : mEventPrototype){
			if(_event.getClass() == _e.getClass()){
				_is_registered = true;
			}
		}
		
		if(_is_registered){
			if(_event.isLocal()){
				mLEvent.add(_event);
			}else{
				mNEvent.add(_event);
			}
		}
	}
	public synchronized void enqueue(byte[] _byte_arr){
		Event _event = getEventInstance(_byte_arr);
		
		if(_event != null){
			enqueue(_event);
		}
	}
	private synchronized void handleNetworkEvent(){
		/**
		 * 将NetworkEvent通过NetworkManager发送
		 */
		NetworkManager _networkManager = NetworkManager.getInstance();
		Event _event = mNEvent.poll();
		if(_event != null){
			_networkManager.send(_event);
		}
	}
	private synchronized void handleLocalEvent(){
		/**
		 * 将LocalEvent发送至 Plugin
		 */
		PluginManager _pluginManager = PluginManager.getInstance();
		Event _event = mLEvent.poll();
		if(_event != null && !_pluginManager.handleEvent(_event)){
			mLEvent.add(_event);
		}
	}

	public boolean register(Event _event){
		for(Event _e : mEventPrototype){
			if(_e.getEventType().equals( _event.getEventType())){
				//Already registered
				return false;
			}
		}
		
		mEventPrototype.add(_event);
		return true;
	}
	
	public Event getEventInstance(byte[] _byte_arr){
		try{
			Event _event = null;

			for(Event _e : mEventPrototype){
				_event = _e.getClass().newInstance();
				if(_event.unserialize(_byte_arr)){
					return _event;
				}
			}
			return null;
		}
		catch(Exception e){
			//Wait for logger...
			return null;
		}
	}
	
}
