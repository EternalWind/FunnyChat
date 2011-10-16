package com.funnyChat.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {
	private List<Memory> mMemorys;
	private static MemoryManager mMemoryManager;
	private int counter = 1;
	
	private MemoryManager(){
		mMemorys = new ArrayList<Memory>();
	}
	public synchronized Integer generateld(){
		return counter++;
	}
	public static MemoryManager getInstance(){
		if(mMemoryManager == null){
			mMemoryManager = new MemoryManager();
		}
		return mMemoryManager;
	}
	public Boolean insert(Memory _memory){
		_memory.setmID(generateld());
		mMemorys.add(_memory);
		return true;
	}
	public Boolean remove(Integer _id){
		for(int i=0;i<mMemorys.size();i++){
			if(mMemorys.get(i).getmID().equals(_id)){
				mMemorys.remove(i);
				return true;
			}
		}
		return false;
	}
	public Memory get(Integer _id){
		for(int i=0;i<mMemorys.size();i++){
			if(mMemorys.get(i).getmID().equals(_id)){
				return mMemorys.get(i);
			}
		}
		return null;
	}
	public Boolean set(Integer _id, Memory _memory){
		for(int i=0;i<mMemorys.size();i++){
			if(mMemorys.get(i).getmID().equals(_id)){
				mMemorys.remove(i);
				_memory.setmID(_id);
				mMemorys.add(_memory);
				return true;
			}
		}
		return false;
	}
}
