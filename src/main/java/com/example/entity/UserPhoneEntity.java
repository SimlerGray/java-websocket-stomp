package com.example.entity;

/**
 * Created by apple on 17/7/13.
 */
public class UserPhoneEntity {
	private String user_Uuid;
	private String user_Tel;
	private String phone_Pwd;

	public String getPhone_Pwd() {
		return phone_Pwd;
	}

	public void setPhone_Pwd(String phone_Pwd) {
		this.phone_Pwd = phone_Pwd;
	}

	public String getUser_Uuid() {
		return user_Uuid;
	}

	public void setUser_Uuid(String user_Uuid) {
		this.user_Uuid = user_Uuid;
	}

	public String getUser_Tel() {
		return user_Tel;
	}

	public void setUser_Tel(String user_Tel) {
		this.user_Tel = user_Tel;
	}
}
