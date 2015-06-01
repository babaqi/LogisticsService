package com.glodon.dto;

public class ShowShopDto {
	private int shop_id;
	private String shop_name;
	private String shop_address;
	private String shop_tel;
	private String shop_pwd;
	private String shop_city_name;
	private String shop_area_name;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getShop_tel() {
		return shop_tel;
	}
	public void setShop_tel(String shop_tel) {
		this.shop_tel = shop_tel;
	}
	public String getShop_pwd() {
		return shop_pwd;
	}
	public void setShop_pwd(String shop_pwd) {
		this.shop_pwd = shop_pwd;
	}
	public String getShop_city_name() {
		return shop_city_name;
	}
	public void setShop_city_name(String shop_city_name) {
		this.shop_city_name = shop_city_name;
	}
	public String getShop_area_name() {
		return shop_area_name;
	}
	public void setShop_area_name(String shop_area_name) {
		this.shop_area_name = shop_area_name;
	}
	@Override
	public String toString() {
		return "ShowShopDto [shop_id=" + shop_id + ", shop_name=" + shop_name
				+ ", shop_address=" + shop_address + ", shop_tel=" + shop_tel
				+ ", shop_pwd=" + shop_pwd + ", shop_city_name="
				+ shop_city_name + ", shop_area_name=" + shop_area_name + "]";
	}
	
	
	
}
