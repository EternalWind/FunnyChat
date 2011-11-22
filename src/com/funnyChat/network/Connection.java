package com.funnyChat.network;

import java.nio.channels.*;
import java.net.*;

public class Connection {
	//private int mType;        Aborted
	private SocketChannel mSocketCh;
	private long mLastActiveTime;
	
	public Connection(SocketChannel _socket_channel){
		mSocketCh = _socket_channel;
		mLastActiveTime = 0;
	}
	
	public InetAddress getIP(){
		return mSocketCh.socket().getInetAddress();
	}
	
	public int getPort(){
		return mSocketCh.socket().getPort();
	}
	
	public SocketChannel getSocketChannel(){
		return mSocketCh;
	}
	
	public long getLastActiveTime(){
		return mLastActiveTime;
	}
	
	public void setLastActiveTime(long _time){
		mLastActiveTime = _time;
	}
	/* Aborted
	public int getType(){
		return mType;
	}
	*/
}
