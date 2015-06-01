package com.glodon.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glodon.bean.AddressBean;
import com.glodon.bean.AreaBean;
import com.glodon.bean.CityBean;
import com.glodon.bean.ShopBean;
import com.glodon.bean.ShopListBean;
import com.glodon.dto.ShowShopDto;
import com.glodon.dto.ShowUserDto;
import com.glodon.model.Shop;
import com.glodon.service.AddressService;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.glodon.service.ShopService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AddressService addressService;

	private Gson gson = new Gson();

	/**
	 * 
	 * 注册
	 */
	@RequestMapping(value = "/insertShop", method = RequestMethod.GET)
	@ResponseBody
	public String insertShop(String jsonShop) {
		Type targetType = new TypeToken<HashMap<String, Shop>>() {
		}.getType();
		Map<String, Shop> map = gson.fromJson(jsonShop, targetType);
		Shop shop = new Shop();
		shop = map.get("shop");
		return shopService.insertShop(shop);
	}

	/**
	 * 
	 * WebInsertShop
	 */
	@RequestMapping(value = "/WebInsertShop", method = RequestMethod.GET)
	public String WebInsertShop(HttpServletRequest request, ModelMap modelMap) {
		Shop shop = new Shop();
		shop.setShop_address(request.getParameter("shopAddressK").toString());
		shop.setShop_area_id(Integer.parseInt(request.getParameter(
				"shopAreaIdK").toString()));
		shop.setShop_city_id(Integer.parseInt(request.getParameter(
				"shopCityIdK").toString()));
		shop.setShop_name(request.getParameter("shopNameK").toString());
		shop.setShop_pwd(request.getParameter("shopPwdK").toString());
		shop.setShop_tel(request.getParameter("shopTelK").toString());
		String result = shopService.insertShop(shop);
		return "submitShop";
	}

	/**
	 * denglu
	 */
	@RequestMapping(value = "/queryShop", method = RequestMethod.GET)
	@ResponseBody
	public String queryShop(String jsonShop) {
		Type targetType = new TypeToken<HashMap<String, Shop>>() {
		}.getType();
		Map<String, Shop> map = gson.fromJson(jsonShop, targetType);
		String shopName = map.get("shop").getShop_name();
		String shoppwd = map.get("shop").getShop_pwd();

		return shopService.queryShop(shopName, shoppwd);
	}

	/**
	 * Webdenglu
	 */
	@RequestMapping(value = "/WebQueryShop", method = RequestMethod.GET)
	public String WebQueryShop(HttpServletRequest request, ModelMap modelMap) {
		String backstageSelect = request.getParameter("backstage_select");
		String result = shopService.queryShop(
				request.getParameter("user_name"),
				request.getParameter("user_pwd"));
		ShopBean shopBean = new ShopBean();
		Type targetType = new TypeToken<ShopBean>() {
		}.getType();
		shopBean = gson.fromJson(result, targetType);
		if (shopBean.getRetcode().equals("0")) {
			HttpSession session = request.getSession();
			session.setAttribute("shopid", shopBean.getShop().getShop_id());
			session.setAttribute("shopcityid", shopBean.getShop()
					.getShop_city_id());
			session.setAttribute("shoptel", shopBean.getShop().getShop_tel());
			session.setAttribute("shopareaid", shopBean.getShop()
					.getShop_area_id());
			session.setAttribute("shopaddress", shopBean.getShop()
					.getShop_address());
			session.setAttribute("shopname", shopBean.getShop().getShop_name());
			if (backstageSelect.equals("1")) {
				String areaResult = areaService.queryArea();
				String cityResult = cityService.queryCity();
				String addressResult = addressService.queryAddress("1");

				AreaBean areaBean = new AreaBean();
				CityBean cityBean = new CityBean();
				AddressBean addressBean = new AddressBean();
				Type areatargetType = new TypeToken<AreaBean>() {
				}.getType();
				Type citytargetType = new TypeToken<CityBean>() {
				}.getType();
				Type addresstargetType = new TypeToken<AddressBean>() {
				}.getType();
				areaBean = gson.fromJson(areaResult, areatargetType);
				cityBean = gson.fromJson(cityResult, citytargetType);
				addressBean = gson.fromJson(addressResult, addresstargetType);
				modelMap.put("areaList", areaBean.getArea());
				modelMap.put("cityList", cityBean.getCity());
				modelMap.put("addressList", addressBean.getAddress());
				return "submitOrder";
			} else {
				return "gonggao";
			}

		} else {
			return shopBean.getRetmsg();
		}
	}

	/**
	 * webQueryAllShop
	 */
	@RequestMapping(value = "/WebQueryAllShop", method = RequestMethod.GET)
	public String WebQueryAllShop(HttpServletRequest request, ModelMap modelMap) {
		String shopResult = shopService.queryAllShop();
		ShopListBean shopListBean = new ShopListBean();
		Type shopTargetType = new TypeToken<ShopListBean>() {
		}.getType();
		shopListBean = gson.fromJson(shopResult, shopTargetType);

		String cityResult = cityService.queryCity();
		CityBean cityBean = new CityBean();
		Type cityTargetType = new TypeToken<CityBean>() {
		}.getType();
		cityBean = gson.fromJson(cityResult, cityTargetType);

		String areaResult = areaService.queryArea();
		AreaBean areaBean = new AreaBean();
		Type areaTargetType = new TypeToken<AreaBean>() {
		}.getType();
		areaBean = gson.fromJson(areaResult, areaTargetType);

		modelMap.put("cityList", cityBean.getCity());
		modelMap.put("areaList", areaBean.getArea());
		List<String> shopCityList = new ArrayList<String>();
		List<String> shopAreaList = new ArrayList<String>();
		if (shopListBean.getShop().size() >= 1) {
			for (int i = 0; i < shopListBean.getShop().size(); i++) {
				for (int m = 0; m < cityBean.getCity().size(); m++) {
					if (shopListBean.getShop().get(i).getShop_city_id() == cityBean
							.getCity().get(m).getCity_id()) {
						shopCityList.add(cityBean.getCity().get(m)
								.getCity_name());
					}
				}
			}

			for (int i = 0; i < shopListBean.getShop().size(); i++) {
				for (int m = 0; m < areaBean.getArea().size(); m++) {
					if (shopListBean.getShop().get(i).getShop_area_id() == areaBean
							.getArea().get(m).getArea_id()) {
						shopAreaList.add(areaBean.getArea().get(m)
								.getArea_name());
					}
				}
			}

			List<ShowShopDto> showShopDtoList = new ArrayList<ShowShopDto>();

			for (int i = 0; i < shopListBean.getShop().size(); i++) {
				ShowShopDto showShopDto = new ShowShopDto();
				showShopDto.setShop_address(shopListBean.getShop().get(i)
						.getShop_address());
				showShopDto.setShop_area_name(shopAreaList.get(i));
				showShopDto.setShop_city_name(shopCityList.get(i));
				showShopDto.setShop_id(shopListBean.getShop().get(i)
						.getShop_id());
				showShopDto.setShop_name(shopListBean.getShop().get(i)
						.getShop_name());
				showShopDto.setShop_pwd(shopListBean.getShop().get(i)
						.getShop_pwd());
				showShopDto.setShop_tel(shopListBean.getShop().get(i)
						.getShop_tel());

				showShopDtoList.add(showShopDto);
			}
			modelMap.put("showShopDtoList", showShopDtoList);
		}
		return "submitShop";
	}

	/**
	 *
	 */
	@RequestMapping(value = "/updateShop", method = RequestMethod.GET)
	@ResponseBody
	public String updateShop(String jsonShop) {
		return shopService.updateShop(jsonShop);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/webdeleteShop", method = RequestMethod.GET)
	@ResponseBody
	public String webdeleteShop(HttpServletRequest request , ModelMap modelMap) {
		return shopService.deleteShop(request.getParameter("shopIdK").toString());
	}
}
