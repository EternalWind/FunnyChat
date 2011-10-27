package com.funnyChat.event;

import java.util.LinkedList;
import java.util.Queue;

import com.funnyChat.plugin.PluginManager;

public class EventManager extends Thread{
	private Queue<Event> mLEvent;
	private Queue<Event> mNEvent;
	private static EventManager mEventManager;
	public static void initialize(){
		if(mEventManager == null){
			mEventManager = new EventManager();
			mEventManager.start();
		}
	}
	public void deinitialize(){
		
	}
	public static EventManager getInstance(){
		return mEventManager;
	}
	private EventManager(){
		mLEvent = new LinkedList<Event>();
		mNEvent = new LinkedList<Event>();
	}
	public void run(){
		while(true){
			try {
				handleLocalEvent();
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public  void queue(Event _event){
		if(_event.isLocal()){
			mLEvent.add(_event);
		}else{
			mNEvent.add(_event);
		}
	}
	/**
	 * 本方法只返回NetworkEvent
	 * @return
	 */
	public Event dequeue(){
		return mNEvent.poll();
	}
	public synchronized void handleLocalEvent(){
		for(int i=0;i<mLEvent.size();i++){
			/**
			 * 将LocalEvent发送至 Plugin
			 */
			PluginManager.initialzie();
			PluginManager pluginManager = PluginManager.getInstance();
			for(int j=0;j<pluginManager.getPlugins().size();j++)
				pluginManager.getPlugins().get(j).handleEvent(mLEvent.poll());
		}
	}

}
