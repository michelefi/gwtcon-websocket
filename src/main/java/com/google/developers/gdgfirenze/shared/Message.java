package com.google.developers.gdgfirenze.shared;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

	private String data;
	private String username;
	private Date time;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


}
