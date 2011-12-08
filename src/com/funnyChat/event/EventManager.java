package com.funnyChat.event;

import java.util.LinkedList;
import java.util.Queue;
import com.funnyChat.network.*;
import com.funnyChat.Thread.*;
import com.funnyChat.core.*;

import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.Log.LogType;

public class EventManager extends FCThread{
	private Queue<Event> mLEvent;
	private Queue<Event> mNEvent;
	private LinkedList<Event> mEventPrototype;
	private static EventManager mInstance;
	public static void initialize(){
		if(mInstance == null){
			mInstance = new EventManager();
		}
		else{
			Core.getLogger().addLog("Duplicative initialization for the EventManager.", LogType.WARNING);
		}
	}
	public void deinitialize(){
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
	synchronized protected void onRun(){
		try {
			handleLocalEvent();
			handleNetworkEvent();
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Core.getLogger().addLog("The EventManager has been inerrupted.", LogType.DEBUG);
			e.printStackTrace();
		}
	}
	synchronized public void enqueue(Event _event){
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
	synchronized public void enqueue(byte[] _byte_arr, Connection _source){
		Event _event = getEventInstance(_byte_arr);
		//Deals with the PingEvent.
		if(_event.getEventType().equals("PingEvent")){
			PingEvent _ping_event = (PingEvent)_event;
			if(!_ping_event.isFinished()){
				//Need to send it back.
				_ping_event.setIsLocal(false);
				_ping_event.setTarget(_source);
				_ping_event.finishPing();
			}
			else{
				//Done ping. Just kill it.
				_event = null;
			}
		}
		else{
			_event.setSource(_source);
			_event.setIsLocal(true);
		}
		
		if(_event != null){
			enqueue(_event);
		}
	}
	/* Aborted
	public void dequeue();
	*/
	synchronized private void handleNetworkEvent(){
		/**
		 * 将NetworkEvent通过NetworkManager发送
		 */
		NetworkManager _networkManager = NetworkManager.getInstance();
		Event _event = mNEvent.poll();
		if(_event != null){
			_networkManager.send(_event);
		}
	}
	synchronized private void handleLocalEvent(){
		/**
		 * 将LocalEvent发送至 Plugin
		 */
		PluginManager _pluginManager = PluginManager.getInstance();
		Event _event = mLEvent.poll();
		if(_event != null && !_pluginManager.handleEvent(_event)){
			mLEvent.add(_event);
		}
	}

	synchronized public boolean register(Event _event){
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
			Core.getLogger().addLog("Failed to instantiate the an event.", LogType.ERROR);
			return null;
		}
	}
	
}
