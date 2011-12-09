package com.funnyChat.utils;

import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public final class LayoutInfo {

	private BufferedReader mReader = null;
	private FileWriter mWriter = null;
	private File mLayoutFile = null;
	private Map<String, Panel> mLayoutInfo = null;

	public LayoutInfo(File _layout_file) {
		mLayoutFile = _layout_file;
	}

	private void initReader() {
		try {
			mReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(mLayoutFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initWriter() {
		try {
			mWriter = new FileWriter(mLayoutFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readLayoutInfo() {
		if (mLayoutFile == null || !mLayoutFile.isFile()) {
			mLayoutFile = new File("Default Layout.txt");
			// mLayoutInfo = new LinkedList<Panel>();
			// Panel _panel = new Panel();
			// _panel.setBounds(0, 0, 100, 100);
			// mLayoutInfo.add(_panel);
		} else {
			try {
				if (mReader == null)
					initReader();
				int _panel_count = Integer.parseInt(mReader.readLine());
				if (_panel_count <= 0)
					return;

				String[] _data;
				// _plugin_name: record plug-in name
				// _rect: record x,y, which are the left
				// bottom point, respectively
				String _plugin_name = "";
				int[] _rect = new int[2];
				for (int i = 0; i < _panel_count; ++i) {
					_data = mReader.readLine().split(" ");
					_plugin_name = _data[0];
					for (int j = 0; j < 2; ++j) {
						_rect[j] = Integer.parseInt(_data[j + 1]);
					}
					mLayoutInfo.put(_plugin_name, new Panel());
					mLayoutInfo.get(_plugin_name).setLocation(_rect[0],
							_rect[1]);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeLayoutInfo() {
		try {
			initWriter();
			String _data = "";
			_data += mLayoutInfo.size();
			for (Entry<String, Panel> p : mLayoutInfo.entrySet()) {
				_data += "\r\n" + p.getKey() + " " + p.getValue().getX() + " "
						+ p.getValue().getY();
			}
			mWriter.write(_data);
			mWriter.flush();
			mWriter.close();
			mWriter = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPanelCount() {
		if (mLayoutInfo == null)
			readLayoutInfo();
		return mLayoutInfo.size();
	}

	public Panel getPanel(String _plugin_name) {
		if (mLayoutInfo == null)
			readLayoutInfo();
		return mLayoutInfo.get(_plugin_name);
	}

	public Collection<Panel> getPanels() {
		if (mLayoutInfo == null)
			readLayoutInfo();
		return mLayoutInfo.values();
	}

	public void registerPanel(String _plugin_name, Panel _panel) {
		if (mLayoutInfo == null)
			readLayoutInfo();
		mLayoutInfo.put(_plugin_name, _panel);
	}

	public void removePanel(String _plugin_name) {
		if (mLayoutInfo == null)
			readLayoutInfo();
		mLayoutInfo.remove(_plugin_name);
	}
}
