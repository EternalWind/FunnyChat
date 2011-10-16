package com.funnyChat.network;

import java.nio.channels.*;
import java.net.*;

public class Connection {
	private int mType;
	private SocketChannel mSocketCh;
	private int mLastActiveTime;
	
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
	
	public int getLastActiveTime(){
		return mLastActiveTime;
	}
	
	public void setLastActiveTime(int _time){
		mLastActiveTime = _time;
	}
	
	public int getType(){
		return mType;
	}
}
