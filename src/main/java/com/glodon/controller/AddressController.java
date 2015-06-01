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

import com.glodon.bean.AddressBean;
import com.glodon.bean.AreaBean;
import com.glodon.bean.CityBean;
import com.glodon.model.Address;
import com.glodon.model.Area;
import com.glodon.model.City;
import com.glodon.service.AddressService;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/address")
public class AddressController {
	private Gson gson = new Gson();
	@Autowired
	private AddressService addressService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;

	/**
	 * 
	 * web提交地区
	 */
	@RequestMapping(value = "/WebSubmitAddress", method = RequestMethod.GET)
	public String WebSubmitArea(HttpServletRequest request,
			ModelMap modelMapHttp) {

		String result = addressService.insertAddress(
				request.getParameter("areaidK").toString(), request
						.getParameter("citynameK").toString()
						+ request.getParameter("areanameK").toString()
						+ request.getParameter("addressNameK").toString());
		return "submitArea";
	}

	/**
	 * Web查询
	 */
	@RequestMapping(value = "/WebQueryAddress", method = RequestMethod.GET)
	public String WebQueryAddress(HttpServletRequest request, ModelMap modelMap) {
		String addressResult = addressService.queryAddress("1");
		Type addressTargetType = new TypeToken<AddressBean>() {
		}.getType();
		AddressBean addressBean = gson.fromJson(addressResult,
				addressTargetType);
		List<Address> addressList = addressBean.getAddress();
		modelMap.put("addressList", addressList);

		String cityResult = cityService.queryCity();
		Type cityTargetType = new TypeToken<CityBean>() {
		}.getType();
		CityBean cityBean = gson.fromJson(cityResult, cityTargetType);
		List<City> cityList = cityBean.getCity();
		modelMap.put("cityList", cityList);
		
		
		String cityid;
		if (request.getParameter("cityidK") == null) {
			cityid = "1";
		} else {
			cityid = request.getParameter("cityidK").toString();
		}
		String areaResult = areaService.queryAreaByCityId(cityid);
		Type areaTargetType = new TypeToken<AreaBean>() {
		}.getType();
		AreaBean areaBean = gson.fromJson(areaResult, areaTargetType);
		List<Area> areaList = areaBean.getArea();
		modelMap.put("areaList", areaList);

		return "submitAddress";
	}
	/**
	 * Web根据areaid查询addres
	 */
	@RequestMapping(value = "/WebQueryAddressByareaId", method = RequestMethod.GET)
	@ResponseBody
	public String WebQueryAddressByareaId(HttpServletRequest request, ModelMap modelMap) {
		
		String addressResult = addressService.queryAddress(request.getParameter("areaidK"));
		Type addressTargetType = new TypeToken<AddressBean>() {
		}.getType();
		AddressBean addressBean = gson.fromJson(addressResult,
				addressTargetType);
		List<Address> addressList = addressBean.getAddress();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addressList", addressList);
		return gson.toJson(map);
	}

	@RequestMapping("abcd")
	@ResponseBody
	public String getAreas(HttpServletRequest request) {
		String cityid;
		if (request.getParameter("cityidK") == null) {
			cityid = "1";
		} else {
			cityid = request.getParameter("cityidK").toString();
		}
		String areaResult = areaService.queryAreaByCityId(cityid);
		Type areaTargetType = new TypeToken<AreaBean>() {
		}.getType();
		AreaBean areaBean = gson.fromJson(areaResult, areaTargetType);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Area> areaList = areaBean.getArea();
		map.put("areaList", areaList);

		return gson.toJson(map);
	}
	
	@RequestMapping("queryAddress")
	@ResponseBody
	public String getaddress(HttpServletRequest request) {
		String addressResult = addressService.queryAddress(request.getParameter("areaidK"));
		Type addressTargetType = new TypeToken<AddressBean>() {
		}.getType();
		AddressBean addressBean = gson.fromJson(addressResult,
				addressTargetType);
		List<Address> addressList = addressBean.getAddress();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addressList", addressList);
		
		return gson.toJson(map);
	}
	
	
	@RequestMapping(value = "/MobileQueryAddress", method = RequestMethod.GET)
	@ResponseBody
	public String getMobileQueryAddress(String jsonAddress) {
		Type targetType = new TypeToken<HashMap<String, Address>>() {
		}.getType();
		Map<String, Address> map = gson.fromJson(jsonAddress, targetType);
		return addressService.queryAddress(String.valueOf(map.get("address").getAddress_areaid()));
	}
	
	
	@RequestMapping(value = "/WebUpdateAddress", method = RequestMethod.GET)
	@ResponseBody
	public String WebUpdateAddress(HttpServletRequest request , ModelMap modelMap) {
		return addressService.updateAddress(request.getParameter("addressIdk").toString(),request.getParameter("addressNamek").toString());
	}
	
	
	@RequestMapping(value = "/WebDeleteAddress", method = RequestMethod.GET)
	@ResponseBody
	public String WebDeleteAddress(HttpServletRequest request , ModelMap modelMap) {
		
		return addressService.deleteAddress(request.getParameter("addressIdk").toString());
	}
}
