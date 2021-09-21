package com.sample.microservices.common.model;

import lombok.Data;

@Data
public class UserInfoStore {
	
	private String userName;

	public void copyData(UserInfoStore sourceData) {
		this.setUserName(sourceData.getUserName());
	}
	
	public void clear() {
		this.setUserName(null);
	}

	@Override
	public String toString() {
		return "UserInfoStore [userName=" + userName + "]";
	}
	
	
}
