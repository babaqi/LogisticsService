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
import com.glodon.bean.ContextBean;
import com.glodon.bean.UserListBean;
import com.glodon.dao.ContextDao;
import com.glodon.model.Context;
import com.glodon.model.User;
import com.glodon.service.AddressService;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.glodon.service.ContextService;
import com.glodon.service.UserService;
import com.glodon.util.PageUtil;
import com.glodon.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/context")
public class ContextController {
	private ContextDao contextDao;

	private Gson gson = new Gson();
	@Autowired
	private ContextService contextService;
	@Autowired
	private UserService userService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AddressService addressService;

	/**
	 * 提交表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/submitInfo", method = RequestMethod.GET)
	@ResponseBody
	public String submitInfo(String jsoncontext) {
		Type targetType = new TypeToken<HashMap<String, Context>>() {
		}.getType();
		Map<String, Context> map = gson.fromJson(jsoncontext, targetType);
		Context context;
		context = map.get("context");
		context.setContext_timer(Utils.nowTime());
		return contextService.submitInfo(context);
	}

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webSubmit", method = RequestMethod.GET)
	public String webSubmit(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		Context context = new Context();
		context.setContext_address(request.getParameter("contextaddressk"));
		context.setContext_amountofmoney(request
				.getParameter("contextamountofmoneyk"));
		context.setContext_areaid(Integer.parseInt(request.getParameter("addressidk")));
		context.setContext_cityid(Integer.parseInt(session.getAttribute(
				"shopcityid").toString()));
		context.setContext_infomation(request
				.getParameter("contextinfomationk"));
		context.setContext_phone(request.getParameter("contextphonek"));
		context.setContext_price(request.getParameter("contextpricek"));
		context.setContext_shop_address(session.getAttribute("shopaddress")
				.toString());
		context.setContext_shop_id(session.getAttribute("shopid").toString());
		context.setContext_shop_name(session.getAttribute("shopname")
				.toString());
		context.setContext_shop_tel(session.getAttribute("shoptel").toString());
		context.setContext_timer(Utils.nowTime());
		String result = contextService.submitInfo(context);
		Type targetType = new TypeToken<ContextBean>() {
		}.getType();
		ContextBean contextBean = gson.fromJson(result, targetType);
		modelMap.put("recode", contextBean.getRetcode());
		return "submitOrder";
	}

	/**
	 * 查询表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryInfo", method = RequestMethod.GET)
	@ResponseBody
	public String queryInfo(String jsoncontext) {
		Type targetType = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsoncontext, targetType);
		String result = contextService.queryInfo(map.get("page"),
				map.get("state"), map.get("areaId"), map.get("userid"),
				map.get("shopId"));
		return result;
	}
	/**
	 * 历史订单 根据时间查询
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryInfobydata", method = RequestMethod.GET)
	@ResponseBody
	public String queryInfobydata(String jsoncontext) {
		Type targetType = new TypeToken<HashMap<String, Object>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsoncontext, targetType);
		String result = contextService.queryInfobydata( map.get("userid"),map.get("shopid"),
				map.get("time"));
		return result;
	}

	
	@RequestMapping(value = "/webQueryInfo", method = RequestMethod.GET)
	public String webQueryInfo(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		String result = contextService.queryInfo(request.getParameter("page"),
				request.getParameter("state"), request.getParameter("areaId"),
				request.getParameter("userId"), session.getAttribute("shopid").toString());
		Type targetTypea = new TypeToken<ContextBean>() {
		}.getType();
		ContextBean contextBean = gson.fromJson(result, targetTypea);
		List<Context> list = contextBean.getContext();
		modelMap.put("context", list);
		return "showOrder";
	}

	
	
	@RequestMapping(value = "/gonggao", method = RequestMethod.GET)
	public String aa() {
		return "gonggao";
	}
	
	@RequestMapping(value = "/submitOrder", method = RequestMethod.GET)
	public String submitOrder(HttpServletRequest request ,ModelMap modelMap) {
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
	}

	/**
	 * 修改表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateInfo", method = RequestMethod.GET)
	@ResponseBody
	public String updateInfo(String jsoncontext) {
		Type targetType = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsoncontext, targetType);
		String contextid = map.get("context_id");
		String contexttypemap = map.get("state");
		return contextService.updateInfo(contextid, contexttypemap);
	}

	/**
	 * web修改表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webUpdateInfo", method = RequestMethod.GET)
	public String webUpdateInfo(HttpServletRequest request, ModelMap modelMap) {
		String contextid = request.getParameter("contextIdk");
		String contexttypemap = "5";
		String result = contextService.updateInfo(contextid, contexttypemap);
		return "showOrder";
	}
	
	//____________________________________________________________________________________
	/**
	 * webMoveOrder
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webMoveQueryOrder", method = RequestMethod.GET)
	public String webMoveQueryOrder(HttpServletRequest request, ModelMap modelMap) {
		int pagenum=0;
		if(request.getParameter("pageType").equals("up"))
		{
			if(Integer.parseInt(request.getParameter("page"))>0)
			{
				pagenum = Integer.parseInt(request.getParameter("page"))-1;
			}
			
		}
		
		if(request.getParameter("pageType").equals("down"))
		{
			pagenum = Integer.parseInt(request.getParameter("page"))+1;
		}
		
		
		String result = contextService.webMoveQueryOrder(String.valueOf(pagenum),
				request.getParameter("state"));
		Type targetTypea = new TypeToken<ContextBean>() {
		}.getType();
		ContextBean contextBean = gson.fromJson(result, targetTypea);
		List<Context> list = contextBean.getContext();
		
		String userResult = userService.queryAllUser();
		UserListBean userListBean = new UserListBean();
		Type userTargetType = new TypeToken<UserListBean>() {
		}.getType();
		userListBean = gson.fromJson(userResult, userTargetType);
		
		modelMap.put("pagenum",pagenum);
		modelMap.put("context", list);
		modelMap.put("user_select", userListBean.getUser());
		return "orderMove";
	}
	/**
	 * webMoveOrder
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/webMoveOrder", method = RequestMethod.GET)
	public String webMoveOrder(HttpServletRequest request, ModelMap modelMap) {
		String contextid = request.getParameter("contextIdk");
		String contextareaId = request.getParameter("contextareaIdK");
		String userId = request.getParameter("userIdk");
		String userName = request.getParameter("userNamek");
		
		String result = contextService.webMoveOrder(contextid, userId, userName,contextareaId);
		return "showOrder";
	}
//________________________________________________________________________________________
	/**
	 * rob表单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/robOrderInfo", method = RequestMethod.GET)
	@ResponseBody
	public String robOrderInfo(String jsoncontext) {
		return contextService.robOrderInfo(jsoncontext);
	}
	



}
