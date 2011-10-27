package com.funnyChat;

import javax.swing.JFrame;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow() {
	}

	public void initWindow() {
		this.frameInit();
		this.setSize(400, 300);
		this.setTitle("FunnyChat");
	}

	public void deinitWindow() throws Throwable {
		this.finalize();
	}

	public void run() {
		this.setVisible(true);
	}

}
