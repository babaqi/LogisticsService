package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.City;

public interface CityDao {

	public int insertCity(City city);

	public List<City> queryCity();
	
	
	public int updateCity (Map<String, String> params);
	
	public int deleteCity(Map<String, String> params);
}
