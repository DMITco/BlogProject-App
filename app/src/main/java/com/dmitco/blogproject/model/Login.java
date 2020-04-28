package com.dmitco.blogproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Login implements Serializable {

	@SerializedName("user")
	private User user;
	@SerializedName("version")
	private Version version;
	@SerializedName("token")
	private String token;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setVersion(Version version){
		this.version = version;
	}

	public Version getVersion(){
		return version;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"user = '" + user + '\'' + 
			",version = '" + version + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
