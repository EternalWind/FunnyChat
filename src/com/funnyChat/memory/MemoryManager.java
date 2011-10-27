package com.funnyChat.memory;

import java.util.HashMap;

public class MemoryManager {
	private HashMap<Integer, Memory> mMemorys;
	private static MemoryManager mInstance;
	private int counter = 0;
	
	private MemoryManager(){
		mMemorys = new HashMap<Integer, Memory>();
	}
	
	public static void initialize(){
		if(mInstance == null){
			mInstance = new MemoryManager();
		}
	}
	public void deinitialize(){
		if(mInstance != null){
			removeAll();
			mMemorys = null;
			mInstance = null;
		}
	}
	
	private synchronized Integer generateId(){
		return counter++;
	}
	public static MemoryManager getInstance(){
		return mInstance;
	}
	public Boolean insert(Memory _memory){
		if(mMemorys.containsValue(_memory)){
			return false;
		}
		else{
			mMemorys.put(generateId(), _memory);
			return true;
		}
	}
	public Boolean remove(Integer _id){
		if(mMemorys.remove(_id) == null){
			return false;
		}
		return true;
	}
	public void removeAll(){
		mMemorys.clear();
	}
	public Memory get(Integer _id){
		return mMemorys.get(_id);
	}
	public Boolean set(Integer _id, Memory _memory){
		if(!remove(_id)){
			return false;
		}
		else{
			mMemorys.put(_id, _memory);
			return true;
		}
	}
}
