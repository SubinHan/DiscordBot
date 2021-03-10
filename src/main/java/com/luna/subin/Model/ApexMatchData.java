package com.luna.subin.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApexMatchData {
	ArrayList<ApexPlayerData> players;

	public ApexMatchData() {
		players = new ArrayList<ApexPlayerData>();
	}

	public void add(ApexPlayerData data) {
		players.add(data);
	}

	public Map getDamageRatio() {
		Map map = new HashMap<String, Double>();

		Iterator it;
		it = players.iterator();
		int totalDamage = 0;
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			totalDamage += player.getDamageDealt();
		}

		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			double damageRatio;
			if(totalDamage == 0)
				damageRatio = 0;
			else
				damageRatio = (double) player.getDamageDealt() / totalDamage;
			map.put(player.getName(), damageRatio);
		}

		return map;
	}

	public Map getKillsRatio() {
		Map map = new HashMap<String, Double>();

		Iterator it;
		it = players.iterator();
		int totalKills = 0;
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			if (player.getKills() < 0) {
				return null;
			}
			totalKills += player.getKills();
		}

		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();

			double killsRatio;
			if (totalKills == 0) {
				return null;
			} else {
				killsRatio = (double) player.getKills() / totalKills;
			}
			map.put(player.getName(), killsRatio);
		}

		return map;
	}

	public Map getDamageDeviation() {
		Map map = new HashMap<String, Double>();

		Iterator it;
		it = players.iterator();
		int totalDamage = 0;
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			totalDamage += player.getDamageDealt();
		}

		double averageDamage = (double) totalDamage / players.size();

		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			double damageDeviation = player.getDamageDealt() - averageDamage;
			map.put(player.getName(), damageDeviation);
		}

		return map;
	}

	public Map getKillsDeviation() {
		Map map = new HashMap<String, Double>();

		Iterator it;
		it = players.iterator();
		int totalKills = 0;
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			if (player.getKills() < 0) {
				return null;
			}
			totalKills += player.getKills();
		}

		double averageKills = (double) totalKills / players.size();

		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			double killsDeviation = player.getKills() - averageKills;
			map.put(player.getName(), killsDeviation);
		}

		return map;
	}

	public Map getDamage() {
		Map map = new HashMap<String, Integer>();

		Iterator it;
		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			map.put(player.getName(), player.getDamageDealt());
		}

		return map;
	}

	public Map getKills() {
		Map map = new HashMap<String, Integer>();

		Iterator it;
		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			if (player.getKills() < 0) {
				return null;
			}
			map.put(player.getName(), player.getKills());
		}

		return map;
	}
	
	public Map getDamageVariation() {
		Map map = new HashMap<String, Double>();

		Iterator it;
		it = players.iterator();
		int totalDamage = 0;
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			totalDamage += player.getDamageDealt();
		}

		double averageDamage = (double) totalDamage / players.size();

		it = players.iterator();
		while (it.hasNext()) {
			ApexPlayerData player = (ApexPlayerData) it.next();
			double damageVariation = Math.pow(player.getDamageDealt() - averageDamage, 2);
			map.put(player.getName(), damageVariation);
		}

		return map;
	}

}
