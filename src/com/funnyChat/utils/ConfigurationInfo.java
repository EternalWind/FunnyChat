package com.funnyChat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.funnyChat.utils.Log.LogType;

public final class ConfigurationInfo {
	
	public static final String SEPERATOR = ";";
	private static String getString(String[] strs) {
		String s ="";
		for(String i:strs) {
			s += i + SEPERATOR;
		}
		return s;
	}
	private Log log;
	
	private File mConfFile;
	private int mWinWidth;
	private int mWinHeight;
	private int mWinX;
	private int mWinY;
	private boolean mIsFullScreen;
	private String[] mLayoutFilePath;
	private String[] mSkinFilePath;
	private String[] mPluginFilePath;
	private String mDefaultLayout;
	private String mDefaultSkin;
	private String[] mDefaultPlugins;
	
	public ConfigurationInfo() {
		log = new Log();
		log.setLogFile("Config_Log.txt");
		mConfFile = new File("Config.txt");
	}

	public boolean createConfFile() throws IOException {		
		return mConfFile.createNewFile();
	}

	public boolean loadConfFile() throws IOException {
		if (!mConfFile.isFile()) {
			log.addLog("error: not a file: " + mConfFile.getPath(),LogType.ERROR);
			return false;
		}
		if (!mConfFile.exists()) {
			log.addLog("error: configuration file not found: "
					+ mConfFile.getPath(),LogType.ERROR);
			return false;
		}

		// get information from configuration file
		BufferedReader _read = new BufferedReader(new InputStreamReader(
				new FileInputStream(mConfFile)));

		String _data = "";

		_data = _read.readLine();
		String[] _wnd_size = _data.split(" ");
		mWinWidth = Integer.parseInt(_wnd_size[0]);
		mWinHeight = Integer.parseInt(_wnd_size[1]);

		_data = _read.readLine();
		String[] wnd_loc = _data.split(" ");
		mWinX = Integer.parseInt(wnd_loc[0]);
		mWinY = Integer.parseInt(wnd_loc[1]);

		_data = _read.readLine();
		String _is_full_screen = _data.trim();
		mIsFullScreen = Boolean.parseBoolean(_is_full_screen);

		_data = _read.readLine();
		mLayoutFilePath = _data.split(SEPERATOR);
		_data = _read.readLine();
		mSkinFilePath = _data.split(SEPERATOR);
		_data = _read.readLine();
		mPluginFilePath = _data.split(SEPERATOR);

		_data = _read.readLine();
		mDefaultLayout = _data.trim();
		_data = _read.readLine();
		mDefaultSkin = _data.trim();
		_data = _read.readLine();
		mDefaultPlugins = _data.trim().split(SEPERATOR);

		return true;
	}

	public void saveConfFile() throws IOException {
		if (!mConfFile.exists())
			this.createConfFile();
		if (!mConfFile.canWrite())
			mConfFile.setWritable(true, true);

		// set information to configuration file
		FileWriter _fw = new FileWriter(mConfFile);
		
		String LFP = getString(mLayoutFilePath);
		String SFP = getString(mSkinFilePath);
		String PFP = getString(mPluginFilePath);
		String DP = getString(mDefaultPlugins);
		
		_fw.write(mWinWidth + " " + mWinHeight + "\r\n" +
				mWinX + " " + mWinY + "\r\n" +
				mIsFullScreen + "\r\n" +
				LFP + "\r\n" +
				SFP + "\r\n" +
				PFP + "\r\n" +
				mDefaultLayout + "\r\n" +
				mDefaultSkin + "\r\n" +
				DP);
		_fw.flush();
		_fw.close();
		
		mConfFile.setReadOnly();
		return;
	}

	public int[] getWindowSize() {
		int[] _size = { mWinWidth, mWinHeight };
		return _size;
	}

	public void setWindowSize(int _width, int _height) {
		mWinWidth = _width;
		mWinHeight = _height;
	}

	public int[] getWindowLocation() {
		int[] _location = { mWinX, mWinY };
		return _location;
	}

	public void setWindowLocation(int _x, int _y) {
		mWinX = _x;
		mWinY = _y;
	}

	public boolean isFullScreen() {
		return mIsFullScreen;
	}

	public void setIsFullScreen(boolean _isFullScreen) {
		this.mIsFullScreen = _isFullScreen;
	}

	public String[] getLayoutFilePath() {
		return mLayoutFilePath;
	}

	public void setLayoutFilePath(String[] _layoutFilePath) {
		this.mLayoutFilePath = _layoutFilePath;
	}

	public String[] getSkinFilePath() {
		return mSkinFilePath;
	}

	public void setSkinFilePath(String[] _skinFilePath) {
		this.mSkinFilePath = _skinFilePath;
	}

	public String[] getPluginFilePath() {
		return mPluginFilePath;
	}

	public void setPluginFilePath(String[] _pluginFilePath) {
		this.mPluginFilePath = _pluginFilePath;
	}

	public File getDefaultLayout() {
		if (this.mDefaultLayout != "") {
			File _layoutFile = new File(this.mDefaultLayout);
			return _layoutFile;
		} else
			return null;
	}

	public void setDefaultLayout(String _layoutFilePath) {
		this.mDefaultLayout = _layoutFilePath;
	}

	public boolean getDefaultSkin(File _skinFile) {
		if (this.mDefaultSkin != "") {
			_skinFile = new File(this.mDefaultSkin);
			return true;
		} else
			return false;
	}

	public void setDefaultSkin(String _skinFilePath) {
		this.mDefaultSkin = _skinFilePath;
	}

	public boolean getDefaultPlugins(File[] _pluginFiles) {
		if (mDefaultPlugins.length != 0) {
			for (int i = 0; i < mDefaultPlugins.length; ++i)
				_pluginFiles[i] = new File(mDefaultPlugins[i]);
			return true;
		} else
			return false;
	}

	public void setDefaultPlugins(String[] _pluginFilesPath) {
		this.mDefaultPlugins = _pluginFilesPath;
	}
}
