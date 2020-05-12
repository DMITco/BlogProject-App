package com.dmitco.blogproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

	@SerializedName("imageName")
	private String imageName;
	@SerializedName("postText")
	private String postText;
	@SerializedName("userCreateId")
	private int userCreateId;
	@SerializedName("userLastName")
	private String userLastName;
	@SerializedName("userAvatar")
	private String userAvatar;
	@SerializedName("groupTitle")
	private String groupTitle;
	@SerializedName("userFirstName")
	private String userFirstName;
	@SerializedName("postTitle")
	private String postTitle;
	@SerializedName("createDate")
	private String createDate;
	@SerializedName("postId")
	private int postId;

	public void setImageName(String imageName){
		this.imageName = imageName;
	}

	public String getImageName(){
		return imageName;
	}

	public void setPostText(String postText){
		this.postText = postText;
	}

	public String getPostText(){
		return postText;
	}

	public void setUserCreateId(int userCreateId){
		this.userCreateId = userCreateId;
	}

	public int getUserCreateId(){
		return userCreateId;
	}

	public void setUserLastName(String userLastName){
		this.userLastName = userLastName;
	}

	public String getUserLastName(){
		return userLastName;
	}

	public void setUserAvatar(String userAvatar){
		this.userAvatar = userAvatar;
	}

	public String getUserAvatar(){
		return userAvatar;
	}

	public void setGroupTitle(String groupTitle){
		this.groupTitle = groupTitle;
	}

	public String getGroupTitle(){
		return groupTitle;
	}

	public void setUserFirstName(String userFirstName){
		this.userFirstName = userFirstName;
	}

	public String getUserFirstName(){
		return userFirstName;
	}

	public void setPostTitle(String postTitle){
		this.postTitle = postTitle;
	}

	public String getPostTitle(){
		return postTitle;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public int getPostId(){
		return postId;
	}

	@Override
 	public String toString(){
		return 
			"Post{" + 
			"imageName = '" + imageName + '\'' + 
			",postText = '" + postText + '\'' + 
			",userCreateId = '" + userCreateId + '\'' + 
			",userLastName = '" + userLastName + '\'' + 
			",userAvatar = '" + userAvatar + '\'' + 
			",groupTitle = '" + groupTitle + '\'' + 
			",userFirstName = '" + userFirstName + '\'' + 
			",postTitle = '" + postTitle + '\'' + 
			",createDate = '" + createDate + '\'' + 
			",postId = '" + postId + '\'' + 
			"}";
		}
}
