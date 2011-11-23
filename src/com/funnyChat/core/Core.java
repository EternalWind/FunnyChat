package com.funnyChat.core;

//import com.funnyChat.Thread.ThreadManager;
import com.funnyChat.event.EventManager;
import com.funnyChat.memory.MemoryManager;
import com.funnyChat.network.NetworkManager;
import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.*;
import com.funnyChat.utils.Log.LogType;

public class Core {

	static private MainWindow mMainWnd = null;
	static private Log mLogger = null;
	static private Core mInstance = null;
	static private String mDEAFAULTLOGPATH = "Log.txt";

	private Core() {}
	
	static public void initialize(String _log_path) {
		if(mInstance == null){
			//ThreadManager.initialize();
			mInstance = new Core();
			mLogger = new Log();
			if(_log_path == null) {
				_log_path = mDEAFAULTLOGPATH;
			}
			mLogger.setLogFile(_log_path);
			if(mMainWnd == null){
				mMainWnd = new MainWindow();
			}
			MemoryManager.initialize();
			EventManager.initialize();
			NetworkManager.initialize();
			PluginManager.initialize();
		}
		else {
			mLogger.addLog("Duplicative initialization for the Core.", LogType.WARNING);
		}
	}

	public void deinitialize() {
		//ThreadManager.getInstance().deinitialize();
		mMainWnd = null;
		MemoryManager.getInstance().deinitialize();
		EventManager.getInstance().deinitialize();
		NetworkManager.getInstance().deinitialize();
		PluginManager.getInstance().deinitialize();
		mLogger.saveLog();
		mInstance = null;
		mLogger = null;
	}

	public void run() {
		EventManager.getInstance().start();
		NetworkManager.getInstance().start();
		PluginManager.getInstance().enableAll();
	}

	static public boolean registerMainWnd(MainWindow _mainWnd) {
		if (_mainWnd != null) {
			mMainWnd = _mainWnd;
			return true;
		} 
		else {
			mLogger.addLog("Try to register a null pointer as the main window.", LogType.DEBUG);
			return false;
		}
	}
	
	static public MainWindow getMainWindow() {
		return mMainWnd;
	}
	
	static public Core getInstance() {
		return mInstance;
	}
	
	static public Log getLogger() {
		return mLogger;
	}
}
