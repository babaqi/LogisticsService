package com.glodon.dao;


import java.util.List;
import java.util.Map;

import com.glodon.model.Context;

public interface ContextDao {

	public int insert(Context context);
	
	public List<Context> query(Map<String, Integer> params);
	
	public List<Context> movequery(Map<String, Integer> params);
	public List<Context> queryOrderbydata(Map<String, String> params);
	
	public int webMoveOrder(Map<String, Object> params);
	
	public int update(Map<String, Object> params);
	
	public String robOrderInfo(int context_id); 
}
