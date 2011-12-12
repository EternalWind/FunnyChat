package com.funnyChat.db;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class PluginInfoDAO {
	private Statement stmt;

	public PluginInfoDAO() {
		stmt = Conn.getInstance().getStmt();
	}

	public ArrayList<PluginInfo> find() {
		try {
			ArrayList<PluginInfo> pluginInfos = new ArrayList<PluginInfo>();
			String sql = "select * from plugininfo";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PluginInfo pluginInfo = new PluginInfo();
				pluginInfo.setPid(rs.getLong(1));
				pluginInfo.setName(rs.getString(2));
				pluginInfo.setUid(rs.getLong(3));
				pluginInfo.setDescribe(rs.getString(4));
				pluginInfo.setUptime(rs.getDate(5));
				pluginInfo.setPath(rs.getString(6));
				pluginInfos.add(pluginInfo);
				System.out.println(pluginInfo.toString());
			}
			return pluginInfos;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public PluginInfo find(long _pid) {
		try {
			PluginInfo pluginInfo = new PluginInfo();
			String sql = "select * from plugininfo where pid = " + _pid;
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pluginInfo.setPid(_pid);
				pluginInfo.setName(rs.getString(2));
				pluginInfo.setUid(rs.getLong(3));
				pluginInfo.setDescribe(rs.getString(4));
				pluginInfo.setUptime(rs.getDate(5));
				pluginInfo.setPath(rs.getString(6));
			}
			if(pluginInfo.getName() == null)
				return null;
			return pluginInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public PluginInfo find(String _name) {
		try {
			PluginInfo pluginInfo = new PluginInfo();
			String sql = "select * from plugininfo where name = '" + _name +"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pluginInfo.setPid(rs.getLong(1));
				pluginInfo.setName(rs.getString(2));
				pluginInfo.setUid(rs.getLong(3));
				pluginInfo.setDescribe(rs.getString(4));
				pluginInfo.setUptime(rs.getDate(5));
				pluginInfo.setPath(rs.getString(6));
			}
			if(pluginInfo.getName() == null)
				return null;
			return pluginInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public boolean add(PluginInfo pluginInfo) {
		try {
			String sql = "insert into plugininfo(name,uid,describe,uptime,path) values('"
			 +pluginInfo.getName()+"',"
			 +pluginInfo.getUid()+",'"
			 +pluginInfo.getDescribe()+"','"
			 +pluginInfo.getUptime().toGMTString()+"','"
			 +pluginInfo.getPath()+"')";
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean update(PluginInfo _pluginInfo) {
		try {
			String sql = "update plugininfo set name = '" +_pluginInfo.getName()+"',"
			+"uid = "+_pluginInfo.getUid()+","
			+"describe = '"+_pluginInfo.getDescribe()+"',"
			+"uptime = '"+_pluginInfo.getUptime().toGMTString()+"',"
			+"path = '"+_pluginInfo.getPath()+"' "
			+"where pid = "+ _pluginInfo.getPid();
			System.out.println(_pluginInfo.getPid());
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean delete(long _pid) {
		try {
			String sql = "delete from plugininfo where pid = "+_pid;
			stmt.execute(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[]args){
		PluginInfoDAO pluginInfoDAO = new PluginInfoDAO();
		pluginInfoDAO.find();
		
		PluginInfo pluginInfo = new PluginInfo();
		pluginInfo.setUptime(new Date());
		pluginInfo.setName("name5");
		pluginInfo.setDescribe("wetrdrfg");
		pluginInfo.setPath("sdgdhgdfs");
		pluginInfo.setUid(1);
		pluginInfoDAO.add(pluginInfo);
		
		pluginInfoDAO.find();
		pluginInfo = pluginInfoDAO.find(pluginInfo.getName());
		System.out.println("\n\n"+pluginInfo.toString());
		pluginInfo.setDescribe("setDescribe");
		pluginInfoDAO.update(pluginInfo);
		
		pluginInfoDAO.find();
		
		pluginInfoDAO.delete(pluginInfo.getPid());
	}
}
