package com.funnyChat.core;

import com.funnyChat.Thread.ThreadManager;
import com.funnyChat.event.EventManager;
import com.funnyChat.memory.MemoryManager;
import com.funnyChat.network.NetworkManager;
import com.funnyChat.plugin.PluginManager;

public class Core {

	private MainWindow mMainWnd;

	public void initialize() {
		ThreadManager.initialize();
		MemoryManager.initialize();
		EventManager.initialize();
		NetworkManager.initialize();
		PluginManager.initialize();
	}

	public void deinitialize() {
		ThreadManager.getInstance().deinitialize();
		MemoryManager.getInstance().deinitialize();
		EventManager.getInstance().deinitialize();
		NetworkManager.getInstance().deinitialize();
		PluginManager.getInstance().deinitialize();
	}

	public void run() {
		EventManager.getInstance().start();
		NetworkManager.getInstance().start();
		PluginManager.getInstance().enableAll();
	}

	public boolean registerMainWnd(MainWindow _mainWnd) {
		if (_mainWnd != null) {
			mMainWnd = _mainWnd;
			return true;
		} else
			return false;
	}
	
	public MainWindow getMainWindow() {
		return mMainWnd;
	}
}
