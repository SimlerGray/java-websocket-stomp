package com.example.entity;

/**
 * Created by apple on 17/7/20.
 */
public class CommonEntity {
	private AbstractEntity abstractE;
	private String user_Uuid;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUser_Uuid() {
		return user_Uuid;
	}

	public void setUser_Uuid(String user_Uuid) {
		this.user_Uuid = user_Uuid;
	}

	public AbstractEntity getAbstractE() {
		return abstractE;
	}

	public void setAbstractE(AbstractEntity abstractE) {
		this.abstractE = abstractE;
	}
}
