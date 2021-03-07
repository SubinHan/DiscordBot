package com.luna.subin.Model;

public class ApexPlayerData {

	private String name;
	private int damageDealt;
	private int kills;
	private int reviveGiven;
	private int respawnGiven;
	private int survivalTime;
	
	public ApexPlayerData(String name, int damage, int kills, int reviveGiven, int respawnGiven, int survivalTime) {
		this.name = name;
		this.damageDealt = damage;
		this.kills = kills;
		this.reviveGiven = reviveGiven;
		this.respawnGiven = respawnGiven;
		this.survivalTime = survivalTime;
	}
	
	public ApexPlayerData(String name, int damage, int kills) {
		this(name, damage, kills, -1, -1, -1);
	}

	public ApexPlayerData(String name, int damage) {
		this(name, damage, -1, -1, -1, -1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamageDealt() {
		return damageDealt;
	}

	public void setDamageDealt(int damageDealt) {
		this.damageDealt = damageDealt;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getReviveGiven() {
		return reviveGiven;
	}

	public void setReviveGiven(int reviveGiven) {
		this.reviveGiven = reviveGiven;
	}

	public int getRespawnGiven() {
		return respawnGiven;
	}

	public void setRespawnGiven(int respawnGiven) {
		this.respawnGiven = respawnGiven;
	}

	public int getSurvivalTime() {
		return survivalTime;
	}

	public void setSurvivalTime(int survivalTime) {
		this.survivalTime = survivalTime;
	}

}
