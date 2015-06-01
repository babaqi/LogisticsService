package com.glodon.service;

import com.glodon.model.Shop;



public interface ShopService {

	public String insertShop(Shop shop);

	public String queryShop(String shopName,String ShopPwd);
	public String queryAllShop();
	
	
	public String updateShop (String jsonShop);
	
	public String deleteShop(String shopId);



}
