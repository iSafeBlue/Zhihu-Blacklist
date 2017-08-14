package org.blue.blacklist.bean;

import java.util.Vector;

public class User {
	
	private int id;
	
	private String zhihuname;
	
	private String nickname;
	
	private String description;
	
	private int blackcount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZhihuname() {
		return zhihuname;
	}

	public void setZhihuname(String zhihuname) {
		this.zhihuname = zhihuname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBlackcount() {
		return blackcount;
	}

	public void setBlackcount(int blackcount) {
		this.blackcount = blackcount;
	}

	public User() {
	}

	public User(int id, String zhihuname, String nickname, String description, int blackcount) {
		this.id = id;
		this.zhihuname = zhihuname;
		this.nickname = nickname;
		this.description = description;
		this.blackcount = blackcount;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", zhihuname=" + zhihuname + ", nickname=" + nickname + ", description=" + description
				+ ", blackcount=" + blackcount + "]";
	}

	

}
