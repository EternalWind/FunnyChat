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
