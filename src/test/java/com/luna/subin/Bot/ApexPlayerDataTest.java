package com.luna.subin.Bot;

import com.luna.subin.Model.ApexPlayerData;

import junit.framework.TestCase;

public class ApexPlayerDataTest extends TestCase {
	
	public void test() {
		ApexPlayerData subin = new ApexPlayerData("수빈", 1000, 5);
		ApexPlayerData yeong = new ApexPlayerData("영욱", 750, 3);
		ApexPlayerData chang = new ApexPlayerData("창원", 500, 4);
		
		assertTrue(subin.getName().equals("수빈"));
		assertTrue(yeong.getName().equals("영욱"));
		assertTrue(chang.getName().equals("창원"));
		
		assertTrue(subin.getDamageDealt() == 1000);
		assertTrue(subin.getKills() == 5);
		assertTrue(subin.getReviveGiven() == -1);
		assertTrue(subin.getRespawnGiven() == -1);
	}
}
