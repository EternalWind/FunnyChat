package com.funnyChat.Thread;

public abstract class FCThread extends Thread {
	private boolean isAlive = true;
	
	protected abstract void onRun();
	
	final public void run(){
		while(isAlive){
			onRun();
		}
	}
	
	final public void terminate(){
		isAlive = false;
	}
}
