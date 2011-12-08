package com.funnyChat.event;

import java.util.HashMap;

public class MessageEvent extends Event{
     HashMap<String,String> data = new HashMap<String, String>();
     /**
      * data���� MessageType ���� ��ʾ��Ϣ����,ÿ���¼�����
      * ÿ���¼��ж��� MessageNumber
      * ���صľ��������MessageNumber,�������ֲ�ͬ������
      *
      * ������:
      * MessageTypeΪgetIpAndPort��ʾ��ȡIP��ַ�Ͷ˿ں�
      * uid�����û�ID
      * ���ص���:
      * MessageTypeΪgetIpAndPortResponse
      * ip
      * port
      * 
      * ������:
      * MessageTypeΪgetPluginList��ʾ��ȡ����б�
      * ���ص���:
      * MessageTypeΪgetPluginListResponse
      * plugin_���(��0��ʼ),value��pid:name
      * 
      * ������:
      * MessageTypeΪrealeasePlugin��ʾ��ȡ����б�
      * plugin��byte����
      * ���ص���:
      * MessageTypeΪrealeasePluginResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪaddFriendPlugin��ʾ��ȡ����б�
      * uid1
      * uid2
      * ���ص���:
      * MessageTypeΪaddFriendResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪgetFriendsPlugin��ʾ��ȡ����б�
      * uid
      * ���ص���:
      * MessageTypeΪgetFriendsResponse
      * friend_���(��0��ʼ),value��uid:name
      * 
      * ������:
      * MessageTypeΪdeleteFriendPlugin��ʾ��ȡ����б�
      * uid1
      * uid2
      * ���ص���:
      * MessageTypeΪdeleteFriendResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪsendUserStateChange��ʾ��ȡ����б�
      * uid1
      * state
      * ���ص���:
      * MessageTypeΪsendUserStateChangeResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪcheckLoginInfo��ʾ��ȡ����б�
      * name
      * password
      * ���ص���:
      * MessageTypeΪcheckLoginInfoResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪrefreshUserInfo��ʾ��ȡ����б�
      * uid
      * name
      * password
      * state
      * ip
      * port
      * ���ص���:
      * MessageTypeΪrefreshUserInfoResponse
      * result: success(ʧ�ܷ���failed)
      * 
      * ������:
      * MessageTypeΪregister��ʾ��ȡ����б�
      * name
      * password
      * state
      * ip
      * port
      * ���ص���:
      * MessageTypeΪregisterResponse
      * result: success(ʧ�ܷ���failed)
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
