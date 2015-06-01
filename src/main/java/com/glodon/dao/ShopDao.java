package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.Shop;

public interface ShopDao {
	public int insertShop(Shop Shop);

	public Shop queryShop(String shopname);
	public List<Shop> queryAllShop();
	
	
	public String updateShop (Map<String, Integer> params);
	
	public int deleteshop(Map<String, String> params);
}
