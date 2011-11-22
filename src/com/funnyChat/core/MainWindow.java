package com.funnyChat.core;

import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.funnyChat.utils.ConfigurationInfo;
import com.funnyChat.utils.LayoutInfoReader;

public class MainWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mWindow;
	private ConfigurationInfo mConfInfo;

	public MainWindow() {
		mWindow = new JFrame();
		mConfInfo = new ConfigurationInfo();
		mWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
	}

	public void initWindow(String _window_title) {
		try {
			if (!mConfInfo.loadConfFile()) {
				mConfInfo.createConfFile();
				mConfInfo.loadConfFile();
			}
			int[] _size = mConfInfo.getWindowSize();
			int[] _loc = mConfInfo.getWindowLocation();
			LayoutInfoReader _layout_reader = new LayoutInfoReader();
			Panel[] _panels;
			File _default_layout = null;
			if ((_default_layout = mConfInfo.getDefaultLayout()) != null) {
				if ((_panels = _layout_reader.readLayoutInfo(_default_layout)) != null) {
					for (Panel p : _panels)
						mWindow.add(p);
					mWindow.setTitle(_window_title);
					mWindow.setSize(_size[0], _size[1]);
					mWindow.setLocation(_loc[0], _loc[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deinitWindow() throws Throwable {
		mWindow.dispose();
	}

	public void run() {
		mWindow.setVisible(true);
	}
}
