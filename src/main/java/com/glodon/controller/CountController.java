package com.glodon.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
import com.glodon.bean.ContextBean;
import com.glodon.bean.ShopListBean;
import com.glodon.bean.UserListBean;
import com.glodon.dto.CountShopDto;
import com.glodon.dto.CountUserDto;
import com.glodon.model.Area;
import com.glodon.model.Context;
import com.glodon.model.Shop;
import com.glodon.model.User;
import com.glodon.service.AreaService;
import com.glodon.service.CountService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/count")
public class CountController {
	private Gson gson = new Gson();

	@Autowired
	private CountService countService;
	@Autowired
	private AreaService areaService;

	/**
	 * 查询表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/countOrder", method = RequestMethod.GET)
	@ResponseBody
	public String countOrder(String jsoncontext) {
		Type targetType = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsoncontext, targetType);
		String time = map.get("time");
		String result = countService.countOrder(time);
		return result;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webCountOrder", method = RequestMethod.GET)
	public String webCountOrder(HttpServletRequest request, ModelMap modelMap) {

		String result = areaService.queryArea();
		Type targetType = new TypeToken<AreaBean>() {
		}.getType();
		AreaBean areaBean = gson.fromJson(result, targetType);
		List<Area> areaList = areaBean.getArea();
		modelMap.put("areaList", areaList);

		return "countOrder";
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webSerachCountOrder", method = RequestMethod.GET)
	@ResponseBody
	public String webSerachCountOrder(HttpServletRequest request,
			ModelMap modelMap) {

		String areaid = request.getParameter("areaidK").toString();
		String data = request.getParameter("dataK").toString();
		String Orderresult = countService.queryOrder(areaid, data);
		Type OrdertargetTypea = new TypeToken<ContextBean>() {
		}.getType();
		ContextBean contextBean = gson.fromJson(Orderresult, OrdertargetTypea);
		List<Context> contextlist = contextBean.getContext();

		if (contextlist != null && contextlist.size() != 0) {
			String Shopresult = countService.queryShop(areaid);
			Type ShoptargetTypea = new TypeToken<ShopListBean>() {
			}.getType();
			ShopListBean shopListBean = gson.fromJson(Shopresult,
					ShoptargetTypea);
			List<Shop> shopList = shopListBean.getShop();

			List<CountShopDto> countShopDtoList = new ArrayList<CountShopDto>();

			for (int u = 0; u < shopList.size(); u++) {
				CountShopDto countShopDto = new CountShopDto();
				int ShopArriveOrderNum = 0;
				int ShopProblemOrderNum = 0;
				Double ShopPrice = 0.0;
				for (int i = 0; i < contextlist.size(); i++) {
					if (Integer.parseInt(contextlist.get(i).getContext_shop_id()) == shopList.get(u).getShop_id()) {
						if (contextlist.get(i).getContext_type() == 3) {
							ShopArriveOrderNum = ShopArriveOrderNum + 1;
							ShopPrice = ShopPrice
									+ Double.parseDouble(contextlist.get(i)
											.getContext_price());
						}
						if (contextlist.get(i).getContext_type() == 5) {
							ShopProblemOrderNum = ShopProblemOrderNum + 1;
						}
					}
				}
				countShopDto.setShopArriveOrder(String
						.valueOf(ShopArriveOrderNum));
				countShopDto.setShopName(shopList.get(u).getShop_name());
				countShopDto.setShopPrice(String.valueOf(ShopPrice));
				countShopDto.setShopProblemOrder(String
						.valueOf(ShopProblemOrderNum));
				countShopDtoList.add(countShopDto);
			}

			String Userresult = countService.queryUser(areaid);
			Type UsertargetTypea = new TypeToken<UserListBean>() {
			}.getType();
			UserListBean userListBean = gson.fromJson(Userresult,
					UsertargetTypea);
			List<User> userlist = userListBean.getUser();

			List<CountUserDto> countUserDtoList = new ArrayList<CountUserDto>();

			for (int u = 0; u < userlist.size(); u++) {
				CountUserDto countUserDto = new CountUserDto();
				int UserArriveOrderNum = 0;
				int UserProblemOrderNum = 0;
				Double UserPrice = 0.0;
				for (int i = 0; i < contextlist.size(); i++) {
					if (contextlist.get(i).getContext_userid() == userlist.get(
							u).getUser_id()) {
						if (contextlist.get(i).getContext_type() == 3) {
							UserArriveOrderNum = UserArriveOrderNum + 1;
							UserPrice = UserPrice
									+ Double.parseDouble(contextlist.get(i)
											.getContext_price());
						}
						if (contextlist.get(i).getContext_type() == 5) {
							UserProblemOrderNum = UserProblemOrderNum + 1;
						}

					}

				}
				countUserDto.setUserName(userlist.get(u).getUser_name());
				countUserDto.setUserArriveOrder(String
						.valueOf(UserArriveOrderNum));
				countUserDto.setUserProblemOrder(String
						.valueOf(UserProblemOrderNum));
				countUserDto.setUserPrice(String.valueOf(UserPrice));
				countUserDtoList.add(countUserDto);

			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("contextList", contextlist);
			map.put("countShopDtoList", countShopDtoList);
			map.put("countUserDtoList", countUserDtoList);
			return gson.toJson(map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("contextList", contextlist);
			return gson.toJson(map);
		}

	}
}
