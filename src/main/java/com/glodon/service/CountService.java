package com.glodon.service;

public interface CountService {
	public String countOrder(String time);
	
	public String queryOrder(String areaid ,String data);
	public String queryUser(String areaid);
	public String queryShop(String areaid);

}
