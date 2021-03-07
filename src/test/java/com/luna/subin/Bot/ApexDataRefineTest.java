package com.luna.subin.Bot;

import org.junit.Test;

import com.luna.subin.Model.ApexDataCollector;
import com.luna.subin.Model.ApexMatchData;
import com.luna.subin.Model.ApexPlayerData;

public class ApexDataRefineTest {

	@Test
	public void test() {
		
		ApexDataCollector collector = new ApexDataCollector();
		String rawData = "!영욱:534,2 수빈:132,4 창원:342,2";
		
		System.out.println(rawData.startsWith("!"));
		if(rawData.startsWith("!")) {
			String substringData = rawData.substring(1);
			System.out.println(substringData);
			String[] refinedData;
			refinedData = substringData.split(" ");
			ApexMatchData matchData = new ApexMatchData();
			for (int i = 0; i < refinedData.length; i++) {
				String[] temp = refinedData[i].split(":");

				String name = temp[0];
				ApexPlayerData apexPlayerData;
				if(temp[1].contains(",")) {
					String[] playerData = temp[1].split(",");
					apexPlayerData = new ApexPlayerData(name, Integer.parseInt(playerData[0]), Integer.parseInt(playerData[1]));
				}
				else {
					apexPlayerData = new ApexPlayerData(name, Integer.parseInt(temp[1]));
				}
				matchData.add(apexPlayerData);
			}
			collector.add(matchData);
		}
		rawData = "!영욱:139 수빈:124 창원:98";
		
		System.out.println(rawData.startsWith("!"));
		if(rawData.startsWith("!")) {
			String substringData = rawData.substring(1);
			System.out.println(substringData);
			String[] refinedData;
			refinedData = substringData.split(" ");
			ApexMatchData matchData = new ApexMatchData();
			for (int i = 0; i < refinedData.length; i++) {
				String[] temp = refinedData[i].split(":");

				String name = temp[0];
				ApexPlayerData apexPlayerData;
				if(temp[1].contains(",")) {
					String[] playerData = temp[1].split(",");
					apexPlayerData = new ApexPlayerData(name, Integer.parseInt(playerData[0]), Integer.parseInt(playerData[1]));
				}
				else {
					apexPlayerData = new ApexPlayerData(name, Integer.parseInt(temp[1]));
				}
				matchData.add(apexPlayerData);
			}
			collector.add(matchData);
		}
		
		collector.collect();
		System.out.println(collector.getAverageDamage());
		System.out.println(collector.getDamageDeviation());
		System.out.println(collector.getKillsDeviation());
		System.out.println(collector.getDamageRatio());
		System.out.println(collector.getAverageKills());
		
	}
}
