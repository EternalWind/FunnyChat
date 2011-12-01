package com.funnyChat.db;

public class UserInfo {
    private long uid;
    private String name;
    private String password;
    private String state;
    private String ip;
    private String port;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", name=" + name + ", password="
				+ password + ", state=" + state + ", ip=" + ip + ", port="
				+ port + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
