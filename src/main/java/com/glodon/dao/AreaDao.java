package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.Area;

public interface AreaDao {
	public int insertArea(Area area);

	public List<Area> queryArea();
	public List<Area> queryAreaById(int cityid);
	
	
	public int updateArea (Map<String, String> params);
	
	public int deleteArea(Map<String, String> params);
	
}
