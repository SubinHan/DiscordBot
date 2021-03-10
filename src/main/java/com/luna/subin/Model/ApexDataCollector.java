package com.luna.subin.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApexDataCollector {

	ArrayList<ApexMatchData> matches;

	Map<String, Double> damageRatio;
	Map<String, Double> killsRatio;
	Map<String, Double> damageDeviation;
	Map<String, Double> killsDeviation;
	Map<String, Double> averageDamage;
	Map<String, Double> averageKills;
	Map<String, Double> damageVariation;

	public ApexDataCollector() {
		matches = new ArrayList<ApexMatchData>();
		damageRatio = new HashMap<String, Double>();
		killsRatio = new HashMap<String, Double>();
		damageDeviation = new HashMap<String, Double>();
		killsDeviation = new HashMap<String, Double>();
		averageDamage = new HashMap<String, Double>();
		averageKills = new HashMap<String, Double>();
		damageVariation = new HashMap<String, Double>();
	}

	public void add(ApexMatchData match) {
		this.matches.add(match);
	}

	public void collect() {
		collectDamageRatio();
		collectKillsRatio();
		collectDamageDeviation();
		collectKillsDeviation();
		collectAverageDamage();
		collectAverageKills();
		collectDamageVariation();
	}

	private void collectDamageRatio() {
		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Double> damageRatioMap = matchData.getDamageRatio();
//			System.out.println(damageRatioMap);

			for (String name : damageRatioMap.keySet()) {
				if (!damageRatio.containsKey(name))
					damageRatio.put(name, damageRatioMap.get(name));
				else {
					damageRatio.put(name, damageRatio.get(name) + damageRatioMap.get(name));
				}
			}
		}
//		System.out.println(damageRatio);

		for (String name : damageRatio.keySet()) {
			damageRatio.put(name, damageRatio.get(name) / matches.size());
		}
	}

	private void collectKillsRatio() {
		int numValid = 0;

		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Double> killsRatioMap = matchData.getKillsRatio();
			if (killsRatioMap == null) {
				continue;
			}

//			System.out.println(killsRatioMap);

			for (String name : killsRatioMap.keySet()) {
				if (!killsRatio.containsKey(name))
					killsRatio.put(name, killsRatioMap.get(name));
				else {
					killsRatio.put(name, killsRatio.get(name) + killsRatioMap.get(name));
				}

			}
			numValid++;
		}
//		System.out.println(killsRatio);

		for (String name : killsRatio.keySet()) {
			killsRatio.put(name, killsRatio.get(name) / (numValid));
		}
	}

	private void collectDamageDeviation() {

		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Double> damageDeviationMap = matchData.getDamageDeviation();
//			System.out.println(damageDeviation);

			for (String name : damageDeviationMap.keySet()) {
				if (!damageDeviation.containsKey(name))
					damageDeviation.put(name, damageDeviationMap.get(name));
				else {
					damageDeviation.put(name, damageDeviation.get(name) + damageDeviationMap.get(name));
				}
			}
		}
//		System.out.println(killsRatio);

		for (String name : damageDeviation.keySet()) {
			damageDeviation.put(name, damageDeviation.get(name) / matches.size());
		}
	}

	private void collectKillsDeviation() {
		int numUnvalid = 0;

		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Double> killsDeviationMap = matchData.getKillsDeviation();
//			System.out.println(killsDeviation);
			if (killsDeviationMap == null) {
				numUnvalid++;
				continue;
			}

			for (String name : killsDeviationMap.keySet()) {
				if (!killsDeviation.containsKey(name))
					killsDeviation.put(name, killsDeviationMap.get(name));
				else {
					killsDeviation.put(name, killsDeviation.get(name) + killsDeviationMap.get(name));
				}
			}
		}
//		System.out.println(killsRatio);

		for (String name : killsDeviation.keySet()) {
			killsDeviation.put(name, killsDeviation.get(name) / (matches.size() - numUnvalid));
		}
	}

	private void collectAverageDamage() {
		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Integer> averageDamageMap = matchData.getDamage();
//			System.out.println(averageDamage);

			for (String name : averageDamageMap.keySet()) {
				if (!averageDamage.containsKey(name)) {
					averageDamage.put(name, averageDamageMap.get(name).doubleValue());
				} else {
					averageDamage.put(name, averageDamage.get(name) + averageDamageMap.get(name).doubleValue());
				}
			}
		}
//		System.out.println(killsRatio);

		for (String name : averageDamage.keySet()) {
			averageDamage.put(name, averageDamage.get(name) / matches.size());
		}
	}

	private void collectAverageKills() {
		int numUnvalid = 0;

		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Integer> averageKillsMap = matchData.getKills();
//			System.out.println(averageDamage);
			if (averageKillsMap == null) {
				numUnvalid++;
				continue;
			}

			for (String name : averageKillsMap.keySet()) {
				if (!averageKills.containsKey(name))
					averageKills.put(name, averageKillsMap.get(name).doubleValue());
				else {
					averageKills.put(name, averageKills.get(name) + averageKillsMap.get(name).doubleValue());
				}
			}
		}
//		System.out.println(killsRatio);

		for (String name : averageKills.keySet()) {
			averageKills.put(name, averageKills.get(name) / (matches.size() - numUnvalid));
		}
	}

	private void collectDamageVariation() {

		Iterator it = matches.iterator();
		while (it.hasNext()) {
			ApexMatchData matchData = (ApexMatchData) it.next();
			Map<String, Double> damageVariationMap = matchData.getDamageVariation();
//			System.out.println(damageDeviation);

			for (String name : damageVariationMap.keySet()) {
				if (!damageVariation.containsKey(name))
					damageVariation.put(name, damageVariationMap.get(name));
				else {
					damageVariation.put(name, damageVariation.get(name) + damageVariationMap.get(name));
				}
			}
		}
//		System.out.println(killsRatio);

		for (String name : damageVariation.keySet()) {
			damageVariation.put(name, damageVariation.get(name) / matches.size());
		}
	}

	public Map<String, Double> getDamageRatio() {
		return damageRatio;
	}

	public Map<String, Double> getKillsRatio() {
		return killsRatio;
	}

	public Map<String, Double> getDamageDeviation() {
		return damageDeviation;
	}

	public Map<String, Double> getKillsDeviation() {
		return killsDeviation;
	}

	public Map<String, Double> getAverageDamage() {
		return averageDamage;
	}

	public Map<String, Double> getAverageKills() {
		return averageKills;
	}
	
	public Map<String, Double> getDamageVariation() {
		return damageVariation;
	}

}
