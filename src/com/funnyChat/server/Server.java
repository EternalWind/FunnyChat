package com.funnyChat.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.funnyChat.plugin.PluginAdapter;
import com.funnyChat.db.FriendDAO;
import com.funnyChat.db.PluginInfo;
import com.funnyChat.db.PluginInfoDAO;
import com.funnyChat.db.UserInfo;
import com.funnyChat.db.UserInfoDAO;

public class Server extends PluginAdapter{
	@Override
	public void onCreate() {}

	@Override
	protected void onDestroy() {}

	@Override
	protected void onEnable() {}

	@Override
	protected void execute() {
		//启动线程执行传过来的事件
	}
	//
	@Override
	protected void onDisable() {}

    public String getIpAndPort(long _uid){
    	String result = "";
    	try{
    		UserInfo userInfo = users.get(_uid);
    		result = userInfo.getIp()+":"+userInfo.getPort();
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    	return result;
    }
    public List<PluginInfo>getPluginList(){
    	PluginInfoDAO pluginInfo = new PluginInfoDAO();
    	return pluginInfo.find();
    }
    public boolean realeasePlugin(PluginInfo _plugin){
    	PluginInfoDAO pluginInfoDAO = new PluginInfoDAO();
    	return pluginInfoDAO.add(_plugin);
    }
    public boolean addFriend(long uid1, long uid2){
    	return false;
    }
    public List<UserInfo> getFriends(long _uid){
    	FriendDAO friendDAO = new FriendDAO();
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	List<Long> friends = friendDAO.find(_uid);
    	List<UserInfo> friendInfo = new ArrayList<UserInfo>();
    	for(int i=0;i<friends.size();i++)
    		friendInfo.add(userInfoDAO.find(friends.get(i)));
    	return friendInfo;
    }
    public boolean deleteFriend(long _uid1, long _uid2){
    	FriendDAO friendDAO = new FriendDAO();
        return friendDAO.delete(_uid1, _uid2);
    }
    public boolean sendUserStateChange(Long _uid, String state){
    	try{
    		users.get(_uid).setState(state);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return false;
    }
    public boolean checkLoginInfo(String _name, String _password){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	UserInfo userInfo = userInfoDAO.find(_name);
    	if(userInfo != null && userInfo.getPassword().equals(_password))
    		return true;
    	return false;
    }
    public boolean refreshUserInfo(UserInfo userInfo){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	userInfoDAO.update(userInfo);
    	users.put(userInfo.getUid(), userInfo);
    	return true;
    }
    public void checkAndwer(){
    	
    }
    public boolean register(UserInfo userInfo){
    	UserInfoDAO userInfoDAO = new UserInfoDAO();
    	if(userInfoDAO.find(userInfo.getName())!=null){
    		return false;
    	}
    	boolean succ = userInfoDAO.add(userInfo);
    	if(succ){
    		users.put(userInfo.getUid(), userInfo);
    		return true;
    	}
    	return false;
    }
    private HashMap<Long,UserInfo> users = new HashMap<Long, UserInfo>();
}
