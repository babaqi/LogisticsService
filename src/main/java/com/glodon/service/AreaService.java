package com.glodon.service;



public interface AreaService {

	public String insertArea(String cityid,String areaName);

	public String queryArea();
	public String queryAreaByCityId(String cityid);
	
	
	public String updateArea (String id,String name);
	
	public String deleteArea(String jsonArea);
	




}
