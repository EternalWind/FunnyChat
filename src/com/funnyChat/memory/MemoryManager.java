package com.funnyChat.memory;

import java.util.HashMap;
import java.util.Map.Entry;

public class MemoryManager {
	private HashMap<Integer, Memory> mMemories;
	private static MemoryManager mInstance;
	private int mIdCount;
	private int mMaxCount;
	private int mCount;
	
	private MemoryManager(int _capacity){
		mMemories = new HashMap<Integer, Memory>();
		mMaxCount = _capacity;
		mIdCount = 0;
		mCount = 0;
	}
	public static void initialize(){
		initialize(50);
	}
	public static void initialize(int _capacity){
		if(mInstance == null){
			mInstance = new MemoryManager(_capacity);
		}
	}
	public void deinitialize(){
		if(mInstance != null){
			removeAll();
			mMemories = null;
			mInstance = null;
		}
	}
	
	private synchronized Integer generateId(){
		return mIdCount++;
	}
	public static MemoryManager getInstance(){
		return mInstance;
	}
	/*public Boolean insert(Memory _memory){
		if(mMemorys.containsValue(_memory)){
			return false;
		}
		else{
			mMemorys.put(generateId(), _memory);
			return true;
		}
	}*/
	public Integer allocate(Integer _size){
		//Check if there's a available buffer already existed.
		for(Entry<Integer, Memory> _entry : mMemories.entrySet()){
			if(_entry.getValue().getIsAvaliable() && _entry.getValue().setSize(_size)){
				return _entry.getKey();
			}
		}
		
		//Not exist? Check if we can have more slots
		if(mCount < mMaxCount){
			Integer _id = generateId();
			mCount++;
			mMemories.put(_id, new Memory(_size));
			return _id;
		}
		
		//All failed
		return -1;
	}
	public Boolean remove(Integer _id){
		if(mMemories.remove(_id) == null){
			return false;
		}
		return true;
	}
	public void removeAll(){
		mMemories.clear();
	}
	public Memory get(Integer _id){
		return mMemories.get(_id);
	}
	/*public Boolean set(Integer _id, Memory _memory){
		if(!remove(_id)){
			return false;
		}
		else{
			mMemories.put(_id, _memory);
			return true;
		}
	}*/
}
