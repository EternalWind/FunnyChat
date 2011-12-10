package com.funnyChat.event;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatEvent extends Event{
	private String date;//发送时间
	private String senderInfo;//name(uid)
	private String content;//发送内容
	private List<byte[]> pictures;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSenderInfo() {
		return senderInfo;
	}
	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<byte[]> getPictures() {
		return pictures;
	}
	public void setPictures(List<byte[]> pictures) {
		this.pictures = pictures;
	}
	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return "ChatEvent";
	}
	@Override
	protected String onSerialize() {
		// TODO Auto-generated method stub
		HashMap<String,String> data = new HashMap<String, String>();
		data.put("date", date);
		data.put("senderInfo", senderInfo);
		data.put("content", content);
		data.put("picCount", String.valueOf(pictures.size()));
		for(int i=0;i<pictures.size();i++){
			data.put("pic_"+i, String.valueOf(pictures.get(i)));
		}
		String str = "";
		for(String key:data.keySet()){
			str+="<"+key+">"+data.get(key)+"</"+key+">";
		}
		return str;
	}
	@Override
	protected void onUnserialize(String _data_str) {
		// TODO Auto-generated method stub
		HashMap<String,String> data = new HashMap<String, String>();
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
		date = data.get("date");
		senderInfo = data.get("senderInfo");
		content = data.get("content");
		int count = Integer.parseInt(data.get("picCount"));
		pictures = new ArrayList<byte[]>();
		for(int i=0;i<count;i++){
			pictures.add((data.get("pic_"+i).getBytes()));
		}
	}
	public byte[] getBytes(){
		byte[] content = onSerialize().getBytes();
		if(content.length>100000000)
			return null;
		int contentLen = content.length;
		byte[] bytes = new byte[contentLen+4];
		bytes[0] = (byte)(contentLen/1000000);
		contentLen = contentLen%1000000;
		bytes[1] = (byte)(contentLen/10000);
		contentLen = contentLen%10000;
		bytes[2] = (byte)(contentLen/100);
		contentLen = contentLen%100;               
		bytes[3] = (byte)(contentLen);
		for(int i=0;i<content.length;i++){
			bytes[i+4] = content[i];
		}
		return bytes;
	}
	
	private static DataInputStream dataInputStream;
	public static List<ChatEvent> readRecords(String path){
		List<ChatEvent> records = new ArrayList<ChatEvent>(); 
		try {
			dataInputStream = new DataInputStream(new FileInputStream(new File(
					path)));
			ChatEvent recordEvent = null;
			while ((recordEvent = readRecord()) != null) {
				records.add(recordEvent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}
	private static ChatEvent readRecord(){
		ChatEvent record = new ChatEvent();
		byte[] length= new byte[4];
		try {
			int actLen = dataInputStream.read(length, 0, length.length);
			if(actLen<4)
				return null;
			int lengthInt = length[0]*1000000+length[1]*10000+length[2]*100+length[3];
			byte[] bytes = new byte[lengthInt];
			actLen = dataInputStream.read(bytes, 0, bytes.length);
			if(actLen<lengthInt)
				return null;
			record.onUnserialize(String.valueOf(bytes));
			return record;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
