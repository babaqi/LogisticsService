package com.glodon.dto;

public class ShowUserDto {
	private int user_id;
	private String user_name;
	private String user_pwd;
	private String user_tel;
	private String user_city_name;
	private String user_area_name;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_city_name() {
		return user_city_name;
	}
	public void setUser_city_name(String user_city_name) {
		this.user_city_name = user_city_name;
	}
	public String getUser_area_name() {
		return user_area_name;
	}
	public void setUser_area_name(String user_area_name) {
		this.user_area_name = user_area_name;
	}
	@Override
	public String toString() {
		return "showUserDto [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_pwd=" + user_pwd + ", user_tel=" + user_tel
				+ ", user_city_name=" + user_city_name + ", user_area_name="
				+ user_area_name + "]";
	}
	
}
