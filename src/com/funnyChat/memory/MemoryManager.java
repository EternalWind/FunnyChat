package com.funnyChat.memory;

import java.util.HashMap;

public class MemoryManager {
	private HashMap<Integer, Memory> mMemorys;
	private static MemoryManager mMemoryManager;
	private int counter = 1;
	
	private MemoryManager(){
		mMemorys = new HashMap<Integer, Memory>();
	}
	
	public static void initialize(){
		if(mMemoryManager == null){
			mMemoryManager = new MemoryManager();
		}
	}
	public void deInitialize(){

	}
	
	public synchronized Integer generateld(){
		return counter++;
	}
	public static MemoryManager getInstance(){
		return mMemoryManager;
	}
	public Boolean insert(Memory _memory){
		_memory.setID(generateld());
		mMemorys.put(_memory.getID(), _memory);
		return true;
	}
	public Boolean remove(Integer _id){
		for(Integer key : mMemorys.keySet()){
			if(mMemorys.get(key).getID().equals(_id)){
				mMemorys.remove(key);
				return true;
			}
		}
		return false;
	}
	public Memory get(Integer _id){
		for(Integer key : mMemorys.keySet()){
			if(mMemorys.get(key).getID().equals(_id)){
				return mMemorys.get(key);
			}
		}
		return null;
	}
	public Boolean set(Integer _id, Memory _memory){
		for(Integer key : mMemorys.keySet()){
			if(mMemorys.get(key).getID().equals(_id)){
				_memory.setID(_id);
				mMemorys.put(_memory.getID(), _memory);
				return true;
			}
		}
		return false;
	}
}
