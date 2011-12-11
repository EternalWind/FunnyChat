package com.funnyChat.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendDAO {
	private Statement stmt;

	public FriendDAO() {
		stmt = Conn.getInstance().getStmt();
	}

	public ArrayList<Long> find(long uid) {
		ArrayList<Long> friendsList = new ArrayList<Long>();
		try {
			String sql = "select * from friend where uid1 = " + uid
					+ " or uid2 = " + uid;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long l1 = rs.getLong(2);
				Long l2 = rs.getLong(3);
				if (l1.equals(uid))
					friendsList.add(l2);
				else
					friendsList.add(l1);
				System.out.println("l1:"+l1+" l2:"+l2);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return friendsList;
	}
	public boolean add(long uid1, long uid2) {
		try {
			String sql = "insert into friend(uid1,uid2) values("+ uid1
					+ " , " + uid2 +")";
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean delete(long fid) {
		try {
			String sql = "delete from friend where fid = "+fid;
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Long uid1, Long uid2) {
		try {
			String sql = "delete from friend where uid1 = "+uid1 +" and uid2 = "+ uid2;
			stmt.execute(sql);
			sql = "delete from friend where uid1 = "+uid2 +" and uid2 = "+ uid1;
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args){
		FriendDAO friendDAO = new FriendDAO();
		friendDAO.add(1, 6);
		friendDAO.find(1);
		friendDAO.delete(new Long(1), new Long(5));
		friendDAO.find(1);
	}
}
