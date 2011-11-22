package com.funnyChat.network;

import java.io.IOException;
import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import com.funnyChat.event.*;
import com.funnyChat.Thread.*;

public class NetworkManager extends FCThread{
	private static class ConnectionChecker extends FCThread{
		private int mTimeout;
		private HashMap<Integer, Connection> mConnections;
		private int mPingInterval;
		
		public ConnectionChecker(int _timeout, int _ping_interval, 
				HashMap<Integer, Connection> _connections){
			mTimeout = _timeout;
			mPingInterval = _ping_interval;
			mConnections = _connections;
		}
		
		public void setTimeout(int _timeout){
			mTimeout = _timeout;
		}
		
		public int getTimeout(){
			return mTimeout;
		}
		
		public void setPingInterval(int _ping_interval){
			mPingInterval = _ping_interval;
		}
		
		public int getPingInterval(){
			return mPingInterval;
		}
		
		protected void onRun(){
			Connection _connection;
			
			for(Integer i : mConnections.keySet()){
				_connection = mConnections.get(i);
				
				if(System.currentTimeMillis() - _connection.getLastActiveTime()
						> mPingInterval){					
					EventManager.getInstance().enqueue(new PingEvent(i));
				}
				else if(System.currentTimeMillis() - 
						mConnections.get(i).getLastActiveTime() > mTimeout){
					mConnections.remove(i);
				}
			}
		}
	}
	
	private int mIdCount;
	private static NetworkManager mInstance;
	private HashMap<Integer, Connection> mConnections;
	private ServerSocketChannel mServerSocketChannel;
	private Selector mSelector;
	private int mMaxCount;
	private ConnectionChecker mChecker;
	
	private NetworkManager(int _max_count, int _port){
		try{
			mIdCount = 0;
			mConnections = new HashMap<Integer, Connection>();
			mSelector = Selector.open();
			mServerSocketChannel = ServerSocketChannel.open();
			mServerSocketChannel.configureBlocking(false);
			mServerSocketChannel.socket().bind(new InetSocketAddress(_port));
			mServerSocketChannel.register(mSelector, SelectionKey.OP_ACCEPT);
			mMaxCount = _max_count;
			mChecker = new ConnectionChecker(11000, 5000, mConnections);
		}
		catch(IOException e){
			//Wait for logger...
		}
	}
	
	public static boolean initialize(int _max_count, int _port){
		if(mInstance == null){
			mInstance = new NetworkManager(_max_count, _port);
			//Register the PingEvent.
			EventManager.getInstance().register(new PingEvent(-1));
			//Launch the checker.
			mInstance.mChecker.start();
			return true;
		}
		return false;
	}
	
	public static boolean initialize(){
		return initialize(50, 55555);
	}
	
	public boolean deinitialize(){
		try{
			if(mInstance != null){
				terminate();
				removeAll();
				mConnections = null;
				mServerSocketChannel.socket().close();
				mServerSocketChannel.close();
				mInstance = null;
				return true;
			}
			return false;
		}
		catch(IOException e){
			//Wait for logger...
			return false;
		}
	}
	
	public static NetworkManager getInstance(){
		return mInstance;
	}
	
	public int getTimeout(){
		return mChecker.getTimeout();
	}
	
	public void setTimeout(int _timeout){
		mChecker.setTimeout(_timeout);
	}
	
	public int getPingInterval(){
		return mChecker.getPingInterval();
	}
	
	public void setPingInterval(int _interval){
		mChecker.setPingInterval(_interval);
	}
	
	public int getMaxCount(){
		return mMaxCount;
	}
	
	public void setMaxCount(int _max_count){
		mMaxCount = _max_count;
	}
	
	protected Integer generateId(){
		return mIdCount++;
	}
	
	public void removeAll(){
		mConnections.clear();
	}
	
	public boolean disconnect(Integer _id){
		try{
			Connection _connection = mConnections.get(_id);
			if(_connection != null){
				mConnections.remove(_id);
				_connection.getSocketChannel().close();
				
				return true;
			}
			else{
				return false;
			}
		}
		catch(IOException e){
			//Wait for logger...
			return false;
		}
	}
	
	public Integer connect(InetAddress _ip, int _port){
		try{
			SocketAddress _address = new InetSocketAddress(_ip, _port);
			SocketChannel _socket_channel = SocketChannel.open();
			_socket_channel.configureBlocking(true);
			_socket_channel.connect(_address);
			if(_socket_channel.isConnected()){
				_socket_channel.configureBlocking(false);
				Integer _id = generateId();
				_socket_channel.register(mSelector, SelectionKey.OP_READ).attach(_id);
				Connection _connection = new Connection(_socket_channel);
				//
				//Send hello message.
				//
				mConnections.put(_id, _connection);
				_connection.setLastActiveTime(System.currentTimeMillis());

				return _id;
			}
			else{
				return -1;
			}
		}
		catch(IOException e){
			//Wait for logger...
			return -1;
		}
	}
	
	public void send(Event _event){
		try{
			Connection _connection = mConnections.get(_event.getTarget());
			if(_connection != null){
				byte[] _data = _event.serialize();

				ByteBuffer _buffer = ByteBuffer.allocate(Integer.SIZE / 8 + _data.length * Byte.SIZE / 8);

				_buffer.putInt(_data.length);
				_buffer.put(_data);
				_buffer.clear();

				_connection.getSocketChannel().write(_buffer);
			}
		}
		catch(IOException e){
			//Logger...
		}
	}
	
	protected void onRun(){
		try{
			mSelector.select();
			Set<SelectionKey> _selectedKeys = mSelector.selectedKeys();
			Iterator<SelectionKey> _iter = _selectedKeys.iterator();
			SocketChannel _sc;
			EventManager _eventManager = EventManager.getInstance();
			
			while(_iter.hasNext()){
				SelectionKey _key = _iter.next();
				ByteBuffer _buffer = ByteBuffer.allocate(Integer.SIZE / 8);
				
				if((_key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
					ServerSocketChannel _ssc = (ServerSocketChannel)_key.channel();
					_sc = _ssc.accept();
					Connection _connection = new Connection(_sc);
					_connection.setLastActiveTime(System.currentTimeMillis());
					Integer _id = generateId();
					
					_sc.configureBlocking(false);
					_sc.register(mSelector, SelectionKey.OP_READ).attach(_id);
					mConnections.put(_id, _connection);
					
					_iter.remove();
				}
				else if((_key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
					Connection _connection = mConnections.get(_key.attachment());
					if(_connection != null){
						_connection.setLastActiveTime(System.currentTimeMillis());
						_sc = (SocketChannel)_key.channel();

						_sc.read(_buffer);
						_buffer.clear();

						int _size = _buffer.getInt();
						_buffer = ByteBuffer.allocate(_size);

						_sc.read(_buffer);
						_buffer.clear();
						
						Integer _id = (Integer)_key.attachment();
						mConnections.get(_id).setLastActiveTime(System.currentTimeMillis());

						_eventManager.enqueue(_buffer.array(), _id);
					}
				}
			}
		}
		catch(IOException e){
			//Logger...
		}
	}
	
	public Collection<Connection> getConnections(){
		return mConnections.values();
	}
	
	public Integer getId(Connection _connection){
		for(Map.Entry<Integer, Connection> _item : mConnections.entrySet()){
			if(_item.getValue() == _connection){
				return _item.getKey();
			}
		}
		
		return -1;
	}
}
