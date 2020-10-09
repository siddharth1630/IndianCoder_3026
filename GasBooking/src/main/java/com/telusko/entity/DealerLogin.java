package com.telusko.entity;

import javax.validation.constraints.NotEmpty;


public class DealerLogin {
	
	@NotEmpty(message = "*required")
	private String panCard;
	@NotEmpty(message = "*required")
	private String password;
	
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
