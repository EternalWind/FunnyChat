package com.funnyChat.event.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.funnyChat.event.Event;
import com.funnyChat.event.EventManager;

public class EventManagerTest {
    public static EventManager eventManager;
	@Test
	public void testInitialize() {
		EventManager.initialize();
	}

	@Test
	public void testGetInstance() {
		eventManager = EventManager.getInstance();
	}

	@Test
	public void testQueue() {
		Event _event = new EventImpl();
		_event.setIsLocal(false);
		eventManager.queue(_event);
	}

	@Test
	public void testDequeue() {
		Event _event = eventManager.dequeue();
		if(_event == null)
			System.out.println("null");
		else
			System.out.println(_event.isLocal());
	}

}
