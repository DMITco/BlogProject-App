package com.dmitco.blogproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

	@SerializedName("password")
	private String password;
	@SerializedName("userAvatar")
	private String userAvatar;
	@SerializedName("name")
	private String name;
	@SerializedName("userName")
	private String userName;
	@SerializedName("family")
	private String family;
	@SerializedName("userId")
	private int userId;
	@SerializedName("email")
	private String email;
	@SerializedName("activeCode")
	private String activeCode;
	@SerializedName("isActice")
	private boolean isActice;
	@SerializedName("registerDate")
	private String registerDate;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUserAvatar(String userAvatar){
		this.userAvatar = userAvatar;
	}

	public String getUserAvatar(){
		return userAvatar;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setFamily(String family){
		this.family = family;
	}

	public String getFamily(){
		return family;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setActiveCode(String activeCode){
		this.activeCode = activeCode;
	}

	public String getActiveCode(){
		return activeCode;
	}

	public void setIsActice(boolean isActice){
		this.isActice = isActice;
	}

	public boolean isIsActice(){
		return isActice;
	}

	public void setRegisterDate(String registerDate){
		this.registerDate = registerDate;
	}

	public String getRegisterDate(){
		return registerDate;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"password = '" + password + '\'' + 
			",userAvatar = '" + userAvatar + '\'' + 
			",name = '" + name + '\'' + 
			",userName = '" + userName + '\'' + 
			",family = '" + family + '\'' + 
			",userId = '" + userId + '\'' + 
			",email = '" + email + '\'' + 
			",activeCode = '" + activeCode + '\'' + 
			",isActice = '" + isActice + '\'' + 
			",registerDate = '" + registerDate + '\'' + 
			"}";
		}
}
