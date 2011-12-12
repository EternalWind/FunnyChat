package com.funnyChat.db;

import java.sql.*;

public class Conn {
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=FunnyChat";
	private String user = "sa";// ”√ªß√˚
	private String password = "dd917266**";// √‹¬Î
	private static Conn instance = null;
	private Statement stmt;
	private Connection conn;

	public boolean conn() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static Conn getInstance() {
		if (instance == null) {
			instance = new Conn();
			instance.conn();
		}
		return instance;
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
