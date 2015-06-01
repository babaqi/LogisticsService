package com.glodon.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glodon.bean.AreaBean;
import com.glodon.bean.CityBean;
import com.glodon.model.Area;
import com.glodon.model.City;
import com.glodon.model.Context;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/area")
public class AreaController {
	private Gson gson = new Gson();
	@Autowired
	private AreaService addressService;
	@Autowired
	private CityService cityService;

//	/**
//	 * 
//	 * 
//	 */
//	@RequestMapping(value = "/insertAddress", method = RequestMethod.GET)
//	@ResponseBody
//	public String insertAddress(String jsonAddress) {
//		return addressService.insertArea(jsonAddress);
//	}
	/**
	 * 
	 * web提交地区
	 */
	@RequestMapping(value = "/WebSubmitArea", method = RequestMethod.GET)
	@ResponseBody
	public String WebSubmitArea(HttpServletRequest request,ModelMap modelMapHttp) {
		
		String result = addressService.insertArea(request.getParameter("cityidK").toString(),request.getParameter("areaNameK").toString());
		return "submitArea";
	}

	/**
	 *
	 */
	@RequestMapping(value = "/queryAddress", method = RequestMethod.GET)
	@ResponseBody
	public String queryAddress() {
		return addressService.queryArea();
	}
	/**
	 *
	 */
	@RequestMapping(value = "/MobileQueryAreaByCityId", method = RequestMethod.GET)
	@ResponseBody
	public String MobileQueryAreaByCityId(String JsonArea) {
		Type targetType = new TypeToken<HashMap<String, Area>>() {
		}.getType();
		Map<String, Area> map = gson.fromJson(JsonArea, targetType);
		return addressService.queryAreaByCityId(String.valueOf(map.get("area").getArea_cityid()));
	}

	/**
	 * Web查询
	 */
	@RequestMapping(value = "/WebQueryArea", method = RequestMethod.GET)
	public String WebQueryAddress(HttpServletRequest request, ModelMap modelMap) {
		String cityResult = cityService.queryCity();
		Type cityTargetType = new TypeToken<CityBean>() {
		}.getType();
		CityBean cityBean = gson.fromJson(cityResult, cityTargetType);
		List<City> cityList = cityBean.getCity();
		modelMap.put("cityList", cityList);
		
		
		String result = addressService.queryArea();
		Type targetType = new TypeToken<AreaBean>() {
		}.getType();
		AreaBean areaBean = gson.fromJson(result, targetType);
		List<Area> areaList = areaBean.getArea();
		modelMap.put("areaList", areaList);
		return "submitArea";
	}
	
	
	/**
	 * Web查询
	 */
	@RequestMapping(value = "/WebQueryAreaById", method = RequestMethod.GET)
	@ResponseBody
	public String WebQueryAreaById(HttpServletRequest request, ModelMap modelMap) {
		
		String result = addressService.queryAreaByCityId(request.getParameter("cityidK").toString());
		Type targetType = new TypeToken<AreaBean>() {
		}.getType();
		AreaBean areaBean = gson.fromJson(result, targetType);
		List<Area> areaList = areaBean.getArea();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("areaList", areaList);
		return gson.toJson(map);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/updateAddress", method = RequestMethod.GET)
	@ResponseBody
	public String updateAddress(String jsonAddress) {
		
		return null;
	}

	/**
	 * web更新
	 */
	@RequestMapping(value = "/WebUpdateAddress", method = RequestMethod.GET)
	@ResponseBody
	public String WebUpdateAddress(HttpServletRequest request , ModelMap modelMap) {

		String result = addressService.updateArea(request.getParameter("areaIdk").toString(),request.getParameter("areaNamek").toString());

		return "submitArea";
	}

	/**
	 *
	 */
	@RequestMapping(value = "/deleteArea", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAddress(HttpServletRequest request , ModelMap modelMap) {
		return addressService.deleteArea(request.getParameter("areaIdk").toString());
	}
}
