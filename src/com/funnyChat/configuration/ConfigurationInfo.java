package com.funnyChat.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConfigurationInfo {
	public static final String SEPERATOR = ";";
	public static String getString(String[] strs) {
		String s ="";
		for(String i:strs) {
			s += i + SEPERATOR;
		}
		return s;
	}
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
	}

	public boolean createConfFile() throws IOException {
		return mConfFile.createNewFile();
	}

	public boolean loadConfFile(File _file) throws IOException {
		if (!_file.isFile()) {
			System.out.println("error: not a file: " + _file.getPath());
			return false;
		}
		if (!_file.exists()) {
			System.out.println("error: configuration file not found: "
					+ _file.getPath());
			return false;
		}
		mConfFile = _file;

		// get information from configuration file
		BufferedReader read = new BufferedReader(new InputStreamReader(
				new FileInputStream(_file)));

		String date = "";

		date = read.readLine();
		String[] wnd_size = date.split(" ");
		mWinWidth = Integer.parseInt(wnd_size[0]);
		mWinHeight = Integer.parseInt(wnd_size[1]);

		date = read.readLine();
		String[] wnd_loc = date.split(" ");
		mWinX = Integer.parseInt(wnd_loc[0]);
		mWinY = Integer.parseInt(wnd_loc[1]);

		date = read.readLine();
		String is_full_screen = date.trim();
		mIsFullScreen = Boolean.parseBoolean(is_full_screen);

		date = read.readLine();
		mLayoutFilePath = date.split(SEPERATOR);
		date = read.readLine();
		mSkinFilePath = date.split(SEPERATOR);
		date = read.readLine();
		mPluginFilePath = date.split(SEPERATOR);

		date = read.readLine();
		mDefaultLayout = date.trim();
		date = read.readLine();
		mDefaultSkin = date.trim();
		date = read.readLine();
		mDefaultPlugins = date.trim().split(SEPERATOR);

		return true;
	}

	public void saveConfFile() throws IOException {
		if (!mConfFile.exists())
			this.createConfFile();
		if (!mConfFile.canWrite())
			mConfFile.setWritable(true, true);

		// set information to configuration file
		FileWriter fw = new FileWriter(mConfFile);
		
		String LFP = getString(mLayoutFilePath);
		String SFP = getString(mSkinFilePath);
		String PFP = getString(mPluginFilePath);
		String DP = getString(mDefaultPlugins);
		
		fw.write(mWinWidth + " " + mWinHeight + "\n" +
				mWinX + " " + mWinY + "\n" +
				mIsFullScreen + "\n" +
				LFP + "\n" +
				SFP + "\n" +
				PFP + "\n" +
				mDefaultLayout + "\n" +
				mDefaultSkin + "\n" +
				DP);
		fw.flush();
		fw.close();
		
		mConfFile.setReadOnly();
		return;
	}

	public int[] getWindowSize() {
		int[] size = { mWinWidth, mWinHeight };
		return size;
	}

	public void setWindowSize(int[] _size) {
		if (_size.length != 2)
			return;
		mWinWidth = _size[0];
		mWinHeight = _size[1];
	}

	public int[] getWindowLocation() {
		int[] location = { mWinX, mWinY };
		return location;
	}

	public void setWindowLocation(int[] _location) {
		if (_location.length != 2)
			return;
		mWinX = _location[0];
		mWinY = _location[1];
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

	public boolean getDefaultLayout(File _layoutFile) {
		if (this.mDefaultLayout != "") {
			_layoutFile = new File(this.mDefaultLayout);
			return true;
		} else
			return false;
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
