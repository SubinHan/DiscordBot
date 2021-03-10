package com.luna.subin.Bot;

import java.util.Map;

import com.luna.subin.Model.ApexDataCollector;
import com.luna.subin.Model.ApexMatchData;
import com.luna.subin.Model.ApexPlayerData;

import junit.framework.TestCase;

public class ApexDataCollectorTest extends TestCase {

	public void test() {
		ApexDataCollector collector = new ApexDataCollector();
		
		ApexPlayerData subin = new ApexPlayerData("수빈", 1000, 5);
		ApexPlayerData yeong = new ApexPlayerData("영욱", 750, 3);
		ApexPlayerData chang = new ApexPlayerData("창원", 500, 4);
		
		ApexMatchData match1 = new ApexMatchData();
		match1.add(subin);
		match1.add(yeong);
		match1.add(chang);
		
		ApexPlayerData subin2 = new ApexPlayerData("수빈", 500, 2);
		ApexPlayerData yeong2 = new ApexPlayerData("영욱", 300, 3);
		ApexPlayerData chang2 = new ApexPlayerData("창원", 450, 2);
		ApexMatchData match2 = new ApexMatchData();
		match2.add(subin2);
		match2.add(yeong2);
		match2.add(chang2);
		
		ApexPlayerData subin3 = new ApexPlayerData("수빈", 130, 1);
		ApexPlayerData yeong3 = new ApexPlayerData("영욱", 763, 2);
		ApexPlayerData chang3 = new ApexPlayerData("창원", 550, 4);
		ApexMatchData match3 = new ApexMatchData();
		match3.add(subin3);
		match3.add(yeong3);
		match3.add(chang3);
		
		collector.add(match1);
		collector.add(match2);
		collector.add(match3);
		
		collector.collect();

		System.out.println(collector.getDamageRatio());
		System.out.println(collector.getKillsRatio());
		System.out.println(collector.getDamageDeviation());
		System.out.println(collector.getKillsDeviation());
		System.out.println(collector.getAverageDamage());
		System.out.println(collector.getAverageKills());
		System.out.println(collector.getDamageVariation());
		
		
		String embedMessage = "";

		Map<String, Double> averageDamageMap = collector.getAverageDamage();
		for (String name : averageDamageMap.keySet()) {
			embedMessage = embedMessage.concat(name + "은 평균 " + String.format("%.1f", averageDamageMap.get(name)) + "의 데미지를 넣었습니다!\n");
		}
		System.out.println(embedMessage);
	}
}
