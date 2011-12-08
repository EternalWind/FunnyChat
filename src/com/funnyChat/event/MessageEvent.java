package com.funnyChat.event;

import java.util.HashMap;

public class MessageEvent extends Event{
     HashMap<String,String> data = new HashMap<String, String>();
     /**
      * data中有 MessageType 属性 表示消息类型,每个事件都有
      * 每个事件中都有 MessageNumber
      * 返回的就是请求的MessageNumber,方便区分不同的请求
      *
      * 请求中:
      * MessageType为getIpAndPort表示获取IP地址和端口号
      * uid包含用户ID
      * 返回的是:
      * MessageType为getIpAndPortResponse
      * ip
      * port
      * 
      * 请求中:
      * MessageType为getPluginList表示获取插件列表
      * 返回的是:
      * MessageType为getPluginListResponse
      * plugin_序号(从0开始),value是pid:name
      * 
      * 请求中:
      * MessageType为realeasePlugin表示获取插件列表
      * plugin的byte数组
      * 返回的是:
      * MessageType为realeasePluginResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为addFriendPlugin表示获取插件列表
      * uid1
      * uid2
      * 返回的是:
      * MessageType为addFriendResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为getFriendsPlugin表示获取插件列表
      * uid
      * 返回的是:
      * MessageType为getFriendsResponse
      * friend_序号(从0开始),value是uid:name
      * 
      * 请求中:
      * MessageType为deleteFriendPlugin表示获取插件列表
      * uid1
      * uid2
      * 返回的是:
      * MessageType为deleteFriendResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为sendUserStateChange表示获取插件列表
      * uid1
      * state
      * 返回的是:
      * MessageType为sendUserStateChangeResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为checkLoginInfo表示获取插件列表
      * name
      * password
      * 返回的是:
      * MessageType为checkLoginInfoResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为refreshUserInfo表示获取插件列表
      * uid
      * name
      * password
      * state
      * ip
      * port
      * 返回的是:
      * MessageType为refreshUserInfoResponse
      * result: success(失败返回failed)
      * 
      * 请求中:
      * MessageType为register表示获取插件列表
      * name
      * password
      * state
      * ip
      * port
      * 返回的是:
      * MessageType为registerResponse
      * result: success(失败返回failed)
      * 
      */
     public MessageEvent(){
     }
     public MessageEvent(int _target){
    	 mIsLocal = false;
 //		 mTarget = _target;
     }
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "requestEvent";
	}
	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		String str = "";
		for(String key:data.keySet()){
			str+="<"+key+">"+data.get(key)+"</"+key+">";
		}
		return str;
	}
	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		int pos = _data_str.indexOf("<");
		while(pos<_data_str.length()&&pos>=0){
			int end = _data_str.indexOf(">");
			String key = _data_str.substring(pos+1, end);
			pos = end+1;
			end = _data_str.indexOf("</"+key+">");
			String value = _data_str.substring(pos, end);
			pos = end+3+key.length();
			data.put(key, value);
			_data_str = _data_str.substring(pos);
			pos=0;
		}
	}
	
	public static void main(String[]args){
		MessageEvent r = new MessageEvent();
		r.data.put("1111111111", "22222222");
		r.data.put("33333333", "444444444444");
		r.data.put("5555555555", "66666666666");
		System.out.println(r.onSerialize());
		String s = r.onSerialize();
		r.onUnserialize(s);
		System.out.println(r.onSerialize());
		byte[] bytes = r.serialize();
		
		MessageEvent r2 = new MessageEvent();
		r2.unserialize(bytes);
		System.out.println(r2.onSerialize());
	}
}
