import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.funnyChat.plugin.PluginAdapter;
import com.funnyChat.event.*;
import com.funnyChat.core.*;
import com.funnyChat.network.*;

public class TestPlugin extends PluginAdapter {
	private TestPluginPanel tpp;
	private Connection c = null;
	
	@Override
	public void onCreate() {
		EventManager.getInstance().register(new ChatEvent());
	}
	
	@Override
	protected void onEnable() {
		tpp = new TestPluginPanel();
		try {
			NetworkManager.getInstance().connect(InetAddress.
					getByName("192.168.1.101"), 55555);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Core.getInstance().getMainWindow().registerPanel(this.getPluginName(), tpp);
		tpp.setSize(500, 500);
		tpp.validate();
		tpp.setTp(this);
	}
	
	@Override
	protected void onDisable() {
		Core.getInstance().getMainWindow().updatePanel(this.getPluginName(), 
				tpp, null);
		tpp = null;
		if(c != null) {
			int _id = NetworkManager.getInstance().getId(c);
			NetworkManager.getInstance().disconnect(_id);
			c = null;
		}
	}
	
	@Override
	protected boolean isInterested(Event _event) {
		if(_event.getEventType().equals("ChatEvent") ||
				_event.getEventType().equals("ConnectedEvent") ||
				_event.getEventType().equals("ConnectionFailedEvent")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getPluginName() {
		return "Test Plugin";
	}
	
	@Override
	protected void execute() {
		if(this.hasWork()) {
			if(mEvent.getEventType().equals("ChatEvent")) {
				ChatEvent _event = (ChatEvent)mEvent;
				tpp.updateText(_event.getContent());
			}
			else if(mEvent.getEventType().equals("ConnectedEvent")) {
				tpp.updateText("Connected");
			}
			else if(mEvent.getEventType().equals("ConnectionFailedEvent")) {
				tpp.updateText("Disconnected");
			}
			this.doneWork();
		}
	}
	
	public void clicked() {
		ChatEvent _ce = new ChatEvent();
		_ce.setIsLocal(false);
		_ce.setContent("hello");
		_ce.setDate("11");
		_ce.setPictures(null);
		_ce.setReceiverId("123");
		_ce.setSenderId("123");
		_ce.setTarget(c);
		EventManager.getInstance().enqueue(_ce);
	}
}
