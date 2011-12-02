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

	static private MainWindow mMainWnd;
	private ConfigurationInfo mConfInfo;
	static private Log mLogger = null;
	static private Core mInstance = null;
	static private String mDEAFAULTLOGPATH = "Log.txt";

	public boolean initialize() {
		return initialize(Core.mDEAFAULTLOGPATH);
	}
	
	public boolean initialize(String _log_path) {
		if (mInstance == null) {
			// ThreadManager.initialize();
			mInstance = new Core();
			mLogger = new Log();
			if (_log_path == null) {
				_log_path = mDEAFAULTLOGPATH;
			}
			mConfInfo = new ConfigurationInfo();
			mMainWnd = new MainWindow();

			try {
				if (!mConfInfo.loadConfFile()) {
					mConfInfo.createConfFile();
					mConfInfo.loadConfFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String _default_plugin = null;
			if ((_default_plugin = mConfInfo.getDefaultPlugins()) == null)
				PluginManager.initialize();
			else
				PluginManager.initialize(_default_plugin);
			mMainWnd.initWindow("FunnyChat", mConfInfo);

			MemoryManager.initialize();
			EventManager.initialize();
			NetworkManager.initialize();
			
		} else {
			mLogger.addLog("Duplicative initialization for the Core.",
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
	}

	public void run() {
		EventManager.getInstance().start();
		NetworkManager.getInstance().start();
		PluginManager.getInstance().enableAll();
		mMainWnd.run();
	}

	// Abort
	/*
	 * public boolean registerMainWnd(MainWindow _mainWnd) { if (_mainWnd !=
	 * null) { mMainWnd = _mainWnd; return true; } else return false; }
	 */

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
