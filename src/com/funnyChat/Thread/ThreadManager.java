package com.funnyChat.Thread;

import java.util.*;

public class ThreadManager {
	private HashMap<Integer, FCThread> mThreads;
	private static ThreadManager mInstance;
	private int mIdCount;
	private int mMaxCount;
	
	private ThreadManager(int _max_count){
		mMaxCount = _max_count;
		mIdCount = 0;
		mThreads = new HashMap<Integer, FCThread>();
	}
	
	public static boolean initialize(int _max_count){
		if(mInstance == null){
			mInstance = new ThreadManager(_max_count);
			return true;
		}
		return false;
	}
	
	public static boolean initialize(){
		return initialize(10);
	}
	
	public static ThreadManager getInstance(){
		return mInstance;
	}
	
	public boolean deinitialize(){
		if(mInstance != null){
			removeAll();
			mThreads = null;
			mInstance = null;
			return true;
		}
		return false;
	}
	
	protected int generateId(){
		return mIdCount++;
	}
	
	public int add(FCThread _thread){
		if(mThreads.size() < mMaxCount){
			int _id = generateId();
			mThreads.put(_id, _thread);
			_thread.start();
			return _id;
		}
		else{
			return -1;
		}
	}
	
	public boolean remove(int _id){
		FCThread _thread;
		
		if((_thread = mThreads.remove(_id)) == null){
			return false;
		}
		else{
			_thread.terminate();   //Terminate the thread before remove it.
			
			return true;
		}
	}
	
	public void removeAll(){
		for(FCThread _thread : mThreads.values())
			_thread.terminate();       //Terminate all the threads before remove them.
		
		mThreads.clear();
	}
	
	public Thread get(int _id){
		return mThreads.get(_id);
	}
	
	public int getMaxCount(){
		return mMaxCount;
	}
	
	public void setMaxCount(int _max_count){
		mMaxCount = _max_count;
	}
}
