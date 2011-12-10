package com.funnyChat.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class UserInfoDAO {
	private Statement stmt;

	public UserInfoDAO() {
		stmt = Conn.getInstance().getStmt();
	}

	public Long getCount() {
		String sql = "select count(*) from userinfo ";

		try {
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				return rs.getLong(1);
			else
				return new Long(-1);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Long(-1);
		}
	}
	
	public UserInfo find(long uid) {
		try {
			UserInfo userInfo = new UserInfo();
			String sql = "select * from userinfo where uid = " + uid;
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userInfo.setUid(uid);
				userInfo.setName(rs.getString(2));
				userInfo.setPassword(rs.getString(3));
				userInfo.setState(rs.getString(4));
				userInfo.setIp(rs.getString(5));
				userInfo.setPort(rs.getString(6));
			}
			return userInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public UserInfo find(String name) {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUid(-1);
			String sql = "select * from userinfo where name = '" + name+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userInfo.setUid(rs.getLong(1));
				userInfo.setName(rs.getString(2));
				userInfo.setPassword(rs.getString(3));
				userInfo.setState(rs.getString(4));
				userInfo.setIp(rs.getString(5));
				userInfo.setPort(rs.getString(6));
			}
			return userInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public boolean add(UserInfo userInfo) {
		try {
			String sql = "insert into userinfo(name,password,state,ip,port) values('"
			 +userInfo.getName()+"','"
			 +userInfo.getPassword()+"','"
			 +userInfo.getState()+"','"
			 +userInfo.getIp()+"','"
			 +userInfo.getPort()+"')";
			System.out.println(sql);
			return stmt.execute(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean update(UserInfo userInfo) {
		try {
			String sql = "update userinfo set name = '" +userInfo.getName()+"',"
			+"password = '"+userInfo.getPassword()+"',"
			+"state = '"+userInfo.getState()+"',"
			+"ip = '"+userInfo.getIp()+"',"
			+"port = '"+userInfo.getPort()+"' "
			+"where uid = "+ userInfo.getUid();
			return stmt.execute(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean delete(long uid) {
		try {
			String sql = "delete from userinfo where uid = "+uid;
			return stmt.execute(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static void main(String[]args){
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		System.out.println(userInfoDAO.getCount());
//		System.out.println(userInfoDAO.find(1).toString());
//		
//		
//		UserInfo userInfo = new UserInfo();
//		userInfo.setPassword("1234");
//		userInfo.setName("name7");
//		userInfo.setState("wetrdrfg");
//		userInfo.setIp("sdgdhgdfs");
//		userInfo.setPort("12345");
//		userInfoDAO.add(userInfo);
//		
//		userInfoDAO.find(1);
//		userInfo = userInfoDAO.find(userInfo.getName());
//		System.out.println("\n\n"+userInfo.toString());
//		userInfo.setState("setDescribe");
//		userInfoDAO.update(userInfo);
//		
//		userInfoDAO.find(userInfo.getName());
//		
//		userInfoDAO.delete(userInfo.getUid());
	}
}
