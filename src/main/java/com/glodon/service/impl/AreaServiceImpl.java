package com.glodon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.AreaBean;
import com.glodon.bean.BaseBean;
import com.glodon.bean.CityBean;
import com.glodon.bean.ContextBean;
import com.glodon.bean.UserBean;
import com.glodon.common.Constants;
import com.glodon.dao.AreaDao;
import com.glodon.dao.ShopDao;
import com.glodon.dao.UserDao;
import com.glodon.model.Area;
import com.glodon.model.City;
import com.glodon.model.User;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.glodon.service.ShopService;
import com.glodon.service.UserService;
import com.glodon.util.EmptyUtil;
import com.google.gson.Gson;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	private Gson gson = new Gson();

	@Override
	public String insertArea(String cityid,String areaName) {
		// TODO Auto-generated method stub
		Area area = new Area();
		area.setArea_name(areaName);
		area.setArea_cityid(Integer.parseInt(cityid));
		int id = areaDao.insertArea(area);
		AreaBean areaBean = new AreaBean();
		if (id>0) {
			areaBean.setRetcode(Constants.SUCCESS_CODE);
			areaBean.setRetmsg("查询成功");
		} else {
			areaBean.setRetcode(Constants.ERROR_CODE);
			areaBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(areaBean);
		return result;
	}

	@Override
	public String queryArea() {
		// TODO Auto-generated method stub
		List<Area> list = new ArrayList<Area>();
		list = areaDao.queryArea();
		AreaBean addressBean = new AreaBean();
		if (EmptyUtil.isNotEmpty(list)) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("查询成功");
			addressBean.setArea(list);
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(addressBean);
		return result;

	}
	@Override
	public String queryAreaByCityId(String cityid) {
		// TODO Auto-generated method stub
		List<Area> list = new ArrayList<Area>();
		list = areaDao.queryAreaById(Integer.parseInt(cityid));
		AreaBean addressBean = new AreaBean();
		if (EmptyUtil.isNotEmpty(list)) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("查询成功");
			addressBean.setArea(list);
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("查询失败");
		}
		
		String result = gson.toJson(addressBean);
		return result;
		
	}

	@Override
	public String updateArea(String id,String name) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		
		int ids = areaDao.updateArea(map);

		AreaBean areaBean = new AreaBean();
		if (ids > 0) {
			areaBean.setRetcode(Constants.SUCCESS_CODE);
			areaBean.setRetmsg("更新成功");
		} else {
			areaBean.setRetcode(Constants.ERROR_CODE);
			areaBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(areaBean);
		return result;
	}

	@Override
	public String deleteArea(String id) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		int line = areaDao.deleteArea(map);

		AreaBean areaBean = new AreaBean();
		if (line > 0) {
			areaBean.setRetcode(Constants.SUCCESS_CODE);
			areaBean.setRetmsg("更新成功");
		} else {
			areaBean.setRetcode(Constants.ERROR_CODE);
			areaBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(areaBean);
		return result;
	}

}
