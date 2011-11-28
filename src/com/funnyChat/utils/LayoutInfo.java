package com.funnyChat.utils;

import java.awt.Color;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class LayoutInfo {

	private BufferedReader mReader = null;
	private FileWriter mWriter = null;
	private File mLayoutFile = null;
	private LinkedList<Panel> mPanels = null;

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
		try {
			if (mReader == null)
				initReader();
			int _panel_count = Integer.parseInt(mReader.readLine());
			if (_panel_count <= 0)
				return;

			mPanels = new LinkedList<Panel>();
			String[] _data;
			// _rect: record x,y,width,height, which are the left
			// bottom point,width and height, respectively
			int[] _rect = new int[4];
			for (int i = 0; i < _panel_count; ++i) {
				_data = mReader.readLine().split(" ");
				for (int j = 0; j < 4; ++j) {
					_rect[j] = Integer.parseInt(_data[j]);
				}
				mPanels.add(new Panel());
				mPanels.getLast().setBounds(_rect[0], _rect[1], _rect[2],
						_rect[3]);
				// =============== for test =============================
				if (i == 0)
					mPanels.get(i).setBackground(Color.blue);
				else if (i == 1)
					mPanels.get(i).setBackground(Color.red);
				else
					mPanels.get(i).setBackground(Color.green);
				// ====================================================
				mPanels.get(i).setVisible(true);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLayoutInfo() {
		try {
			initWriter();
			String _data = "";
			_data += mPanels.size() + "\r\n";
			for (Panel p : mPanels) {
				_data += "\r\n" + p.getX() + " " + p.getY() + " "
						+ p.getWidth() + " " + p.getHeight();
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
		return mPanels.size();
	}

	public Panel getPanel(int _index) {
		if (mPanels == null)
			readLayoutInfo();
		return mPanels.get(_index);
	}
	
	public LinkedList<Panel> getPanels() {
		return mPanels;
	}
	
	public void addPanel(Panel _panel) {
		mPanels.add(_panel);
	}
	
	public void removePanel(int _index) {
		mPanels.remove(_index);
	}
	
	public void setPanel(int _index, Panel _panel) {
		if(_index >= 0 && _index < mPanels.size())
			mPanels.set(_index, _panel);
	}
}
