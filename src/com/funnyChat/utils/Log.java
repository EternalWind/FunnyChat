package com.funnyChat.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class Log {

	/*
	 * public static String CONFIGURATION_LOG = "log.configuration"; public
	 * static String EVENT_LOG = "log.event"; public static String MEMORY_LOG =
	 * "log.memory"; public static String NETWORK_LOG = "log.network"; public
	 * static String PLUGIN_LOG = "log.plugin"; public static String THREAD_LOG
	 * = "log.thread";
	 */

	public enum LogType {
		ERROR, DEBUG, WARNING
	}

	private Logger mLogger;
	private FileHandler mFileHandler;

	public Log() {
		mLogger = Logger.getLogger("FunnyChat");
		mLogger.setLevel(Level.ALL);
	}

	public boolean setLogFile(String _logFile) {
		try {
			mFileHandler = new FileHandler(_logFile, true);
			mFileHandler.setFormatter(new SimpleFormatter());
			mLogger.addHandler(mFileHandler);
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean addLog(String _logInfo, LogType _logType) {
		if (mFileHandler == null)
			return false;
		switch (_logType) {
		case DEBUG:
			mLogger.log(Level.INFO, "Debug Info:" + _logInfo);
			break;
		case WARNING:
			mLogger.log(Level.WARNING, "Warning Info:" + _logInfo);
			break;
		case ERROR:
			mLogger.log(Level.SEVERE, "Error Info:" + _logInfo);
			break;
		}
		return true;
	}

	public boolean saveLog() {
		if (mFileHandler == null)
			return false;
		mFileHandler.flush();
		return true;
	}

}
