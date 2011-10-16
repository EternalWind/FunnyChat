package com.funnyChat.event;

import java.util.LinkedList;
import java.util.Queue;

import com.funnyChat.plugin.PluginManager;

public class EventManager extends Thread{
	private Queue<LocalEvent> mLEvent;
	private Queue<NetworkEvent> mNEvent;
	private static EventManager mEventManager;
	private static EventManager getInstance(){
		if(mEventManager == null){
			mEventManager = new EventManager();
			mEventManager.start();
		}
		return mEventManager;
	}
	private EventManager(){
		mLEvent = new LinkedList<LocalEvent>();
		mNEvent = new LinkedList<NetworkEvent>();
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
			mLEvent.add((LocalEvent)_event);
		}else{
			mNEvent.add((NetworkEvent)_event);
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
			PluginManager pluginManager = PluginManager.getInstance();
			for(int j=0;j<pluginManager.getmPlugins().size();j++)
				pluginManager.getmPlugins().get(j).handleEvent(mLEvent.poll());
		}
	}

}
