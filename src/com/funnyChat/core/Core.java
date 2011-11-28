package com.funnyChat.core;

import java.io.IOException;

import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.ConfigurationInfo;

public class Core {

	private MainWindow mMainWnd;
	private ConfigurationInfo mConfInfo;

	public boolean initialize() {
		mConfInfo = new ConfigurationInfo();
		mMainWnd = new MainWindow();
		
		try {
			if(!mConfInfo.loadConfFile()) {
				mConfInfo.createConfFile();
				mConfInfo.loadConfFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String _default_plugin = null;
		if ((_default_plugin = mConfInfo.getDefaultPlugins()) == null)
			return false;
		PluginManager.initialize(_default_plugin);
		mMainWnd.initWindow("FunnyChat", mConfInfo);
		return true;
	}

	public void deinitialize() {
		try {
			mMainWnd.deinitWindow();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PluginManager.getInstance().deinitialize();
	}

	public void run() {
		PluginManager.getInstance().enableAll();
		mMainWnd.run();
	}

	// Abort
	/*public boolean registerMainWnd(MainWindow _mainWnd) {
		if (_mainWnd != null) {
			mMainWnd = _mainWnd;
			return true;
		} else
			return false;
	}*/

	public MainWindow getMainWindow() {
		return mMainWnd;
	}
}
