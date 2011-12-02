package com.funnyChat.core;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import com.funnyChat.plugin.Plugin;
import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.ConfigurationInfo;
import com.funnyChat.utils.LayoutInfo;

public class MainWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mWindow;
	private JFrame mPluginChooser;
	private JCheckBox[] mPluginSwitches;
	private ConfigurationInfo mConfInfo;
	private LayoutInfo mLayoutInfo;

	public MainWindow() {
		mWindow = new JFrame();
		mWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
	}

	public boolean initWindow(String _window_title,
			ConfigurationInfo _configuration) {
		// load configuration file
		mConfInfo = _configuration;

		// read windows attributes
		int[] _size = mConfInfo.getWindowSize();
		int[] _loc = mConfInfo.getWindowLocation();

		// read layout information to layout main window
		File _default_layout = null;
		if ((_default_layout = mConfInfo.getDefaultLayout()) == null)
			return false;
		if (!_default_layout.isFile())
			return false;
		mLayoutInfo = new LayoutInfo(_default_layout);
		for (Panel p : mLayoutInfo.getPanels())
			mWindow.add(p);

		// read plug-ins information to load plug-ins
		PluginManager _pm = PluginManager.getInstance();
		int _it = 0;
		for (Plugin p : _pm.getPlugins()) {
			if (_it >= mLayoutInfo.getPanelCount())
				mLayoutInfo.addPanel(new Panel());
			p.setPanel(mLayoutInfo.getPanel(_it++));
			p.onCreate();
		}

		mWindow.setTitle(_window_title);
		mWindow.setSize(_size[0], _size[1]);
		mWindow.setLocation(_loc[0], _loc[1]);
		return true;
	}

	public void deinitWindow() throws Throwable {
		mWindow.dispose();
	}

	public void run() {
		mPluginChooser = new JFrame();
		Collection<Plugin> _plugins = PluginManager.getInstance().getPlugins();
		int _panel_count = _plugins.size();
		mPluginChooser.setLayout(new GridLayout(1,_panel_count));
		mPluginSwitches = new JCheckBox[_panel_count];
		int i = 0;
		for (Plugin _plugin : _plugins) {
			mPluginSwitches[i].setDoubleBuffered(true);
			mPluginSwitches[i].setText(_plugin.getName());
			mPluginSwitches[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					updatePluginState();
				}
			});
			mPluginChooser.add(mPluginSwitches[i]);
			i++;
		}
		mPluginChooser.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mWindow.setVisible(true);
			}
		});
		mPluginChooser.setVisible(true);
	}
	
	private void updatePluginState() {
		Collection<Plugin> _plugins = PluginManager.getInstance().getPlugins();
		int i = 0;
		for (Plugin _plugin : _plugins) {
			if(mPluginSwitches[i].isSelected() && !_plugin.isEnabled())
				_plugin.enable();
			else if(!mPluginSwitches[i].isSelected() && _plugin.isEnabled())
				_plugin.disable();
		}
	}
}
