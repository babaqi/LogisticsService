package com.glodon.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.AreaBean;
import com.glodon.bean.CityBean;
import com.glodon.common.Constants;
import com.glodon.dao.CityDao;
import com.glodon.model.City;
import com.glodon.service.CityService;
import com.glodon.util.EmptyUtil;
import com.google.gson.Gson;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	private Gson gson = new Gson();

	@Override
	public String insertCity(City city) {
		// TODO Auto-generated method stub
		int id = cityDao.insertCity(city);
		return "";
	}

	@Override
	public String queryCity() {
		// TODO Auto-generated method stub
		List<City> list = new ArrayList<City>();
		list = cityDao.queryCity();
		CityBean cityBean = new CityBean();
		if (EmptyUtil.isNotEmpty(list)) {
			cityBean.setRetcode(Constants.SUCCESS_CODE);
			cityBean.setRetmsg("查询成功");
			cityBean.setCity(list);
		} else {
			cityBean.setRetcode(Constants.ERROR_CODE);
			cityBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(cityBean);
		return result;
	}

	@Override
	public String updateCity(String id,String name) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		
		int ids = cityDao.updateCity(map);

		CityBean cityBean = new CityBean();
		if (ids > 0) {
			cityBean.setRetcode(Constants.SUCCESS_CODE);
			cityBean.setRetmsg("更新成功");
		} else {
			cityBean.setRetcode(Constants.ERROR_CODE);
			cityBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(cityBean);
		return result;
	}
		

	@Override
	public String deleteCity(String id) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		int line = cityDao.deleteCity(map);
		CityBean cityBean = new CityBean();
		if (line > 0) {
			cityBean.setRetcode(Constants.SUCCESS_CODE);
			cityBean.setRetmsg("删除成功");
		} else {
			cityBean.setRetcode(Constants.ERROR_CODE);
			cityBean.setRetmsg("删除失败");
		}
		String result = gson.toJson(cityBean);
		return result;
	}



}
