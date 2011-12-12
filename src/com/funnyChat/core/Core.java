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

	//private MainWindow mMainWnd;
	private Log mLogger = null;
	static private Core mInstance = null;
	static private String mDEAFAULTLOGPATH = "Log.txt";
	private MainWindow2 mTestWin;

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
			//mInstance.mMainWnd = new MainWindow();
			mInstance.mTestWin = new MainWindow2();
			
			EventManager.initialize();

			String _default_plugin = mInstance.mTestWin.getConfigInfo().getDefaultPlugins();
			
			if (_default_plugin  == null)
				PluginManager.initialize();
			else
				PluginManager.initialize(_default_plugin);
			
			mInstance.mTestWin.initWindow("FunnyChat");
			//mInstance.mMainWnd.initWindow("FunnyChat", mInstance.mConfInfo);

			MemoryManager.initialize();
			
			if(mInstance.mTestWin.getConfigInfo().getPort() == ConfigurationInfo.RANDOMPORT) {
				NetworkManager.initialize();
			}
			else {
				NetworkManager.initialize(50, mInstance.mTestWin.getConfigInfo().getPort());
			}
			
		} else {
			mInstance.mLogger.addLog("Duplicative initialization for the Core.",
					LogType.WARNING);
		}
		return true;
	}

	public void deinitialize() {
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
		//mMainWnd.run();
		mTestWin.setVisible(true);
	}

	// Abort
	/*
	 * public boolean registerMainWnd(MainWindow _mainWnd) { if (_mainWnd !=
	 * null) { mMainWnd = _mainWnd; return true; } else return false; }
	 */

	static public MainWindow2 getMainWindow() {
		return mInstance.mTestWin;
	}

	static public Core getInstance() {
		return mInstance;
	}

	static public Log getLogger() {
		return mInstance.mLogger;
	}
}
