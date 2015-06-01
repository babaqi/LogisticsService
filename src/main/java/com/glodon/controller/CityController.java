package com.glodon.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glodon.bean.CityBean;
import com.glodon.model.City;
import com.glodon.service.CityService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/city")
public class CityController {
	private Gson gson = new Gson();
	@Autowired
	private CityService cityService;

	/**
	 * 
	 * 
	 */
	@RequestMapping(value = "/insertCity", method = RequestMethod.GET)
	@ResponseBody
	public String insertCity(String jsonCity) {
		City city = new City();
		return cityService.insertCity(city);
	}
	/**
	 * 
	 * WebInsertCity
	 */
	@RequestMapping(value = "/WebInsertCity", method = RequestMethod.GET)
	@ResponseBody
	public String WebInsertCity(HttpServletRequest request,ModelMap modelMap) {
		City city = new City();
		city.setCity_name(request.getParameter("cityNameK").toString());
		cityService.insertCity(city);
		return "submitCity";
	}

	/**
	 *
	 */
	@RequestMapping(value = "/queryCity", method = RequestMethod.GET)
	@ResponseBody
	public String queryCity(String jsonCity) {
		return cityService.queryCity();
	}
	
	/**
	 *
	 */
	@RequestMapping(value = "/WebQueryCity", method = RequestMethod.GET)
	public String WebQueryCity(HttpServletRequest request,ModelMap modelMap) {
		String result = cityService.queryCity();
		Type targetType = new TypeToken<CityBean>() {
		}.getType();
		CityBean cityBean = gson.fromJson(result, targetType);
		List<City> cityList = cityBean.getCity();
		modelMap.put("cityList", cityList);
		return "submitCity";
		
		
	}
	
	/**
	 *
	 */
	@RequestMapping(value = "/updateCity", method = RequestMethod.GET)
	@ResponseBody
	public String updateCity(String id,String name) {
		return cityService.updateCity(id,name);
	}
	/**
	 *
	 */
	@RequestMapping(value = "/WebUpdateCity", method = RequestMethod.GET)
	@ResponseBody
	public String WebUpdateCity(HttpServletRequest request,ModelMap modelMap) {
		
		String result = cityService.updateCity(request.getParameter("cityIdk").toString(),request.getParameter("cityNamek").toString());
		
		return "submitCity";
	}
	/**
	 *
	 */
	@RequestMapping(value = "/deleteCity", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCity(HttpServletRequest request,ModelMap modelMap) {
		return cityService.deleteCity(request.getParameter("cityIdk").toString());
	}
}
