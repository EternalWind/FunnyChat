package com.funnyChat.core;

import java.io.IOException;

import com.funnyChat.event.EventManager;
import com.funnyChat.memory.MemoryManager;
import com.funnyChat.network.NetworkManager;
import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.ConfigurationInfo;
import com.funnyChat.utils.Log;
import com.funnyChat.utils.Log.LogType;

public class Core {

	private MainWindow mMainWnd;
	private ConfigurationInfo mConfInfo;
	private Log mLogger = null;
	static private Core mInstance = null;
	static private String mDEAFAULTLOGPATH = "Log.txt";

	private Core(){}
	
	static public boolean initialize() {
		return initialize(Core.mDEAFAULTLOGPATH);
	}
	
	static public boolean initialize(String _log_path) {
		if (mInstance == null) {
			// ThreadManager.initialize();
			mInstance = new Core();
			mInstance.mLogger = new Log();
			if (_log_path == null) {
				_log_path = mDEAFAULTLOGPATH;
			}
			mInstance.mConfInfo = new ConfigurationInfo();
			mInstance.mMainWnd = new MainWindow();
			
			EventManager.initialize();

			try {
				if (!mInstance.mConfInfo.loadConfFile()) {
					mInstance.mConfInfo.createConfFile();
					//mConfInfo.loadConfFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String _default_plugin = null;
			if ((_default_plugin = mInstance.mConfInfo.getDefaultPlugins()) == null)
				PluginManager.initialize();
			else
				PluginManager.initialize(_default_plugin);
			mInstance.mMainWnd.initWindow("FunnyChat", mInstance.mConfInfo);

			MemoryManager.initialize();
			NetworkManager.initialize();
			
		} else {
			mInstance.mLogger.addLog("Duplicative initialization for the Core.",
					LogType.WARNING);
		}
		return true;
	}

	public void deinitialize() {
		try {
			mConfInfo.saveConfFile();
			mMainWnd.deinitWindow();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PluginManager.getInstance().deinitialize();
		MemoryManager.getInstance().deinitialize();
		EventManager.getInstance().deinitialize();
		NetworkManager.getInstance().deinitialize();
		mLogger.saveLog();
		mInstance = null;
		mLogger = null;
		
		System.exit(0);
	}

	public void run() {
		EventManager.getInstance().start();
		NetworkManager.getInstance().start();
		//PluginManager.getInstance().enableAll();
		mMainWnd.run();
	}

	// Abort
	/*
	 * public boolean registerMainWnd(MainWindow _mainWnd) { if (_mainWnd !=
	 * null) { mMainWnd = _mainWnd; return true; } else return false; }
	 */

	static public MainWindow getMainWindow() {
		return mInstance.mMainWnd;
	}

	static public Core getInstance() {
		return mInstance;
	}

	static public Log getLogger() {
		return mInstance.mLogger;
	}
}
