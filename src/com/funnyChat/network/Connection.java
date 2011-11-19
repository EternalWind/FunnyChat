package com.funnyChat.network;

import java.nio.channels.*;
import java.net.*;

public class Connection {
	private int mType;
	private SocketChannel mSocketCh;
	private long mLastActiveTime;
	
	public Connection(SocketChannel _socket_channel){
		mType = 0;
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
	
	public int getType(){
		return mType;
	}
}
