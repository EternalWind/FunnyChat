package com.funnyChat.Thread;

public abstract class FCThread extends Thread {
	private boolean isAlive = true;
	
	protected abstract void onRun();
	
	public void run(){
		while(isAlive){
			onRun();
		}
	}
	
	public void terminate(){
		isAlive = false;
	}
}
