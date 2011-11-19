package com.funnyChat.utils.test;

import java.io.IOException;

import com.funnyChat.utils.ConfigurationInfo;
import com.funnyChat.utils.Log;
import com.funnyChat.utils.Log.LogType;

public class ConfigurationInfoTest {
	public static void test() throws IOException {
		Log _log = new Log();
		_log.setLogFile("ConfigurationInfoTest.txt");
		
		String _debug_info = "ConfigurationInfo Test:\n";
		ConfigurationInfo _cfg = new ConfigurationInfo();
		int[] _wnd_size = new int[2];
		int[] _wnd_loc = new int[2];
		boolean _is_full_screen = false;
		String[] _layout = new String[5];
		String[] _plugin = new String[5];
		String[] _skin = new String[5];

		if (!_cfg.loadConfFile()) {
			_cfg.createConfFile();
			_cfg.loadConfFile();
		} else {
			_debug_info += "before modify:\r\n";
			_wnd_size = _cfg.getWindowSize();
			_wnd_loc = _cfg.getWindowLocation();
			_is_full_screen = _cfg.isFullScreen();
			_layout = _cfg.getLayoutFilePath();
			_plugin = _cfg.getPluginFilePath();
			_skin = _cfg.getSkinFilePath();
			_debug_info += "window size: " + _wnd_size[0] + " " + _wnd_size[1]
					+ "\r\nwindow location: " + _wnd_loc[0] + " " + _wnd_loc[1]
					+ "\r\nis full screen: " + _is_full_screen
					+ "\r\nlayout file path:\r\n";
			for (String i : _layout) {
				_debug_info += i + "\r\n";
			}
			_debug_info += "plugin file path:\r\n";
			for (String i : _plugin) {
				_debug_info += i + "\r\n";
			}
			_debug_info += "skin file path:\r\n";
			for (String i : _skin) {
				_debug_info += i + "\r\n";
			}
			_debug_info += "after modify:\r\n";
		}
		_wnd_size[0] = 111;
		_wnd_loc[0] = 222;
		if (_is_full_screen)
			_is_full_screen = false;
		else
			_is_full_screen = true;
		_layout[0] = "test layout...\r\n";
		_plugin[0] = "test plugin...\r\n";
		_skin[0] = "test skin...\r\n";

		_debug_info += "window size: " + _wnd_size[0] + " " + _wnd_size[1]
				+ "\r\nwindow location: " + _wnd_loc[0] + " " + _wnd_loc[1]
				+ "\r\nis full screen: " + _is_full_screen
				+ "\r\nlayout file path:\r\n";
		for (String i : _layout) {
			_debug_info += i + "\r\n";
		}
		_debug_info += "plugin file path:\r\n";
		for (String i : _plugin) {
			_debug_info += i + "\r\n";
		}
		_debug_info += "skin file path:\r\n";
		for (String i : _skin) {
			_debug_info += i + "\r\n";
		}
		
		_debug_info += "test finish.No bug found.";
		_log.addLog(_debug_info, LogType.DEBUG);
	}

}
