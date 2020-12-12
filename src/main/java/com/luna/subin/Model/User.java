package com.luna.subin.Model;

import java.io.Serializable;

public class User {
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private long role;
	private String nickname;
	
	public User(long id) {
		this.id = id;
	}
}
