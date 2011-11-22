package com.funnyChat.utils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LayoutInfoReader {

	private BufferedReader _reader;

	public Panel[] readLayoutInfo(File _layoutFile) {
		try {
			_reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(_layoutFile)));
			int _panel_count = Integer.parseInt(_reader.readLine());
			Panel[] _panels = new Panel[_panel_count];

			String[] _data;
			int[] _rect = new int[4]; // record x,y,width,height, which are the
			// left
			// bottom point,width and height, respectively
			for (int i = 0; i < _panel_count; ++i) {
				_data = _reader.readLine().split(" ");
				for (int j = 0; j < 4; ++j) {
					_rect[j] = Integer.parseInt(_data[j]);
				}
				_panels[i] = new Panel();
				_panels[i].setBounds(_rect[0], _rect[1], _rect[2], _rect[3]);
				// =============== for test =============================
				if (i == 0)
					_panels[i].setBackground(Color.blue);
				else if (i == 1)
					_panels[i].setBackground(Color.red);
				else
					_panels[i].setBackground(Color.green);
				// ====================================================
				_panels[i].setVisible(true);
			}
			return _panels;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
