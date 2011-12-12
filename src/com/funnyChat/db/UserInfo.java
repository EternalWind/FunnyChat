package com.funnyChat.db;

import java.util.Date;

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
@SuppressWarnings("deprecation")
public static UserInfo parse(String str) {
// TODO Auto-generated method stub
UserInfo userInfo = new UserInfo();
int start = str.indexOf("uid=");
int end = str.indexOf(",");
userInfo.setUid(Long.parseLong(str.substring(start+4, end)));
str = str.substring(end+1);

start = str.indexOf("name=");
end = str.indexOf(",");
userInfo.setName(str.substring(start+5, end));
str = str.substring(end+1);

start = str.indexOf("password=");
end = str.indexOf(",");
userInfo.setPassword(str.substring(start+9, end));
str = str.substring(end+1);

start = str.indexOf("state=");
end = str.indexOf(",");
userInfo.setState(str.substring(start+6, end));
str = str.substring(end+1);

start = str.indexOf("ip=");
end = str.indexOf(",");
userInfo.setIp(str.substring(start+3, end));
str = str.substring(end+1);

start = str.indexOf("port=");
end = str.indexOf("]");
userInfo.setPort(str.substring(start+5, end));
return userInfo;
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
