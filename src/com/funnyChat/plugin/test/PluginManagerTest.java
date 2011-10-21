package com.funnyChat.plugin.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.funnyChat.plugin.PluginAdapter;
import com.funnyChat.plugin.PluginManager;

public class PluginManagerTest {

	public static PluginManager pluginManager;
	@Test
	public void testInitialzie() {
		PluginManager.initialzie();
	}

	@Test
	public void testGetInstance() {
		pluginManager = PluginManager.getInstance();
	}

	@Test
	public void testInsert() {
		PluginAdapter _plugin = new PluginAdapter(1);
		pluginManager.insert(_plugin);
		
		PluginAdapter _plugin2 = new PluginAdapter(2);
		pluginManager.insert(_plugin2);
		
	}

	@Test
	public void testRemove() {
		pluginManager.remove(2);
		pluginManager.remove(3);
	}

	@Test
	public void testGet() {
		System.out.println(pluginManager.get(1).getID());
	}

	@Test
	public void testSet() {
		PluginAdapter _plugin2 = new PluginAdapter(4);
		pluginManager.insert(_plugin2);
	}

	@Test
	public void testEnable() {
		Integer[] enable={1};
		pluginManager.enable(enable);
	}

	@Test
	public void testEnableAll() {
		pluginManager.enableAll();
	}

	@Test
	public void testDisable() {
		Integer[] enable={1};
		pluginManager.disable(enable);
	}

	@Test
	public void testDisableAll() {
		pluginManager.disableAll();
	}

}
