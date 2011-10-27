package com.funnyChat.memory.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.funnyChat.memory.Memory;
import com.funnyChat.memory.MemoryManager;

public class MemoryManagerTest {

	public static MemoryManager memoryManager;
	@Test
	public void testInitialize() {
		MemoryManager.initialize();
	}

	@Test
	public void testGetInstance() {
		memoryManager =  MemoryManager.getInstance();
	}

	@Test
	public void testInsert() {
		Memory _memory = new Memory();
		memoryManager.insert(_memory);
		
		Memory _memory2 = new Memory();
		memoryManager.insert(_memory2);
	}

	@Test
	public void testRemove() {
		memoryManager.remove(1);
	}

	@Test
	public void testGet() {
		Memory _memory = memoryManager.get(2);
		System.out.println(_memory.getIsAvaliable());
	}

	@Test
	public void testSet() {
		Memory _memory3 = new Memory();
		memoryManager.set(3,_memory3);
	}

}
