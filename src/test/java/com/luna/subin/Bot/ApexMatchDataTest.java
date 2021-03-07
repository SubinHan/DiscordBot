package com.luna.subin.Bot;

import java.util.Map;

import org.junit.Test;

import com.luna.subin.Model.ApexMatchData;
import com.luna.subin.Model.ApexPlayerData;

public class ApexMatchDataTest {
	@Test
	public void test() {
		ApexPlayerData subin = new ApexPlayerData("수빈", 1000, 5);
		ApexPlayerData yeong = new ApexPlayerData("영욱", 750, 3);
		ApexPlayerData chang = new ApexPlayerData("창원", 500, 4);
		
		ApexMatchData data = new ApexMatchData();
		data.add(subin);
		data.add(yeong);
		data.add(chang);
		
		System.out.println(data.getDamageRatio());
		System.out.println(data.getKillsRatio());
		System.out.println(data.getDamageDeviation());
		System.out.println(data.getKillsDeviation());
		System.out.println(data.getDamage());
		System.out.println(data.getKills());
	}
}
