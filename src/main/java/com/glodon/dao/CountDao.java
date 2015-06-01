package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.Context;
import com.glodon.model.Shop;
import com.glodon.model.User;


public interface CountDao {
	public int countOrder(String context_timer);
	public List<Context> queryOrder(Map<String, String> map);
	public List<User> queryUser(Map<String, String> map);
	public List<Shop> queryShop(Map<String, String> map);
}
