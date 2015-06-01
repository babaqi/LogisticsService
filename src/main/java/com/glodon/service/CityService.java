package com.glodon.service;

import com.glodon.model.City;



public interface CityService {

	public String insertCity(City city);

	public String queryCity();
	
	
	public String updateCity (String id,String name);
	
	public String deleteCity(String id);
	
	
	




}
