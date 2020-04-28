package com.dmitco.blogproject.model;


import com.google.gson.annotations.SerializedName;

//@DoNotRename
public class ModelUserNamePassword {
    @SerializedName("userName")
    String userName;
    @SerializedName("password")
    String password;

    public ModelUserNamePassword(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ModelUserNamePassword{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
