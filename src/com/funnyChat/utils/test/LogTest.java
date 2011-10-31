package com.funnyChat.utils.test;

import com.funnyChat.utils.Log;
import com.funnyChat.utils.Log.LogType;

public class LogTest {
	public static void test() {
		Log _log = new Log();
		_log.setLogFile("LogTest.txt");
		_log.addLog("log test: debug type", LogType.DEBUG);
		_log.addLog("log test: error type", LogType.ERROR);
		_log.addLog("log test: warning type", LogType.WARNING);
		_log.saveLog();
	}
}
