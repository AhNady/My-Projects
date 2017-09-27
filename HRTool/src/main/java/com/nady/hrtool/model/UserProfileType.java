package com.nady.hrtool.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	EMPLOYEE("EMPLOYEE"),
	HRADMIN("HRADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
