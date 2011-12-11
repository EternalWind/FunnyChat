package com.funnyChat.db;

import java.util.Date;

public class PluginInfo {
	private long pid;
	private String name;
	private long uid;
	private String describe;
	private Date uptime;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "PluginInfo [pid=" + pid + ", name=" + name + ", uid=" + uid
				+ ", describe=" + describe + ", uptime=" + uptime + ", path="
				+ path + "]";
	}
	@SuppressWarnings("deprecation")
	public static PluginInfo parse(String str) {
		// TODO Auto-generated method stub
		PluginInfo pluginInfo = new PluginInfo();
		int start = str.indexOf("pid=");
		int end = str.indexOf(",");
		pluginInfo.setPid(Long.parseLong(str.substring(start+4, end)));
		str = str.substring(end+1);
		
		start = str.indexOf("name=");
		end = str.indexOf(",");
		pluginInfo.setName(str.substring(start+5, end));
		str = str.substring(end+1);
		
		start = str.indexOf("uid=");
		end = str.indexOf(",");
		pluginInfo.setUid(Long.parseLong(str.substring(start+4, end)));
		str = str.substring(end+1);
		
		start = str.indexOf("describe=");
		end = str.indexOf(",");
		pluginInfo.setDescribe(str.substring(start+9, end));
		str = str.substring(end+1);
		
		start = str.indexOf("uptime=");
		end = str.indexOf(",");
		pluginInfo.setUptime(new Date(Date.parse(str.substring(start+7, end))));
		str = str.substring(end+1);
		
		start = str.indexOf("path=");
		end = str.indexOf("]");
		pluginInfo.setPath(str.substring(start+5, end));
		return pluginInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private String path;
}
