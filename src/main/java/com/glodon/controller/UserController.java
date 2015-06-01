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

import com.glodon.bean.AreaBean;
import com.glodon.bean.CityBean;
import com.glodon.bean.UserBean;
import com.glodon.bean.UserListBean;
import com.glodon.dto.ShowUserDto;
import com.glodon.model.User;
import com.glodon.service.AreaService;
import com.glodon.service.CityService;
import com.glodon.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	
	private Gson gson = new Gson();

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String registerView(HttpServletRequest request, ModelMap modelMap) {
		// String result = cityService.queryCity();
		// CityBean cityBean = new CityBean();
		// Type targetType = new TypeToken<CityBean>() {
		// }.getType();
		// cityBean = gson.fromJson(result, targetType);
		// modelMap.put("city", cityBean.getCity());
		return "Login";
	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public String register(String jsonuser) {
		Type targetType = new TypeToken<HashMap<String, User>>() {
		}.getType();
		Map<String, User> map = gson.fromJson(jsonuser, targetType);
		User user = map.get("user");
		return userService.register(user);
	}

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/webRegister", method = RequestMethod.GET)
	@ResponseBody
	public String webRegister(HttpServletRequest request, ModelMap modelMap) {
		String user_name = request.getParameter("userNameK");
		String user_pwd = request.getParameter("userPwdK");
		String user_tel = request.getParameter("UserTelK");
		String user_city_id = request.getParameter("UserCityIdK");
		String user_area_id = request.getParameter("UserAreaIdK");
		
		User user = new User();
		user.setUser_area_id(Integer.parseInt(user_area_id));
		user.setUser_city_id(Integer.parseInt(user_city_id));
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		user.setUser_tel(user_tel);
		String result = userService.register(user);
		// user = map.get("user");
		return "submitUser";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(String jsonuser) {
		Type targetType = new TypeToken<HashMap<String, User>>() {
		}.getType();
		Map<String, User> map = gson.fromJson(jsonuser, targetType);
		User user = map.get("user");
		return userService.login(user);
	}

	@RequestMapping(value = "/webLogin", method = RequestMethod.GET)
	public String webLogin(HttpServletRequest request, User user,
			ModelMap modelMap) {
		String backstageSelect = request.getParameter("backstage_select");
		String result = userService.login(user);
		UserBean userBean = new UserBean();
		Type targetType = new TypeToken<UserBean>() {
		}.getType();
		userBean = gson.fromJson(result, targetType);
		if (userBean.getRetcode().equals("0")) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userBean.getUser().getUser_id());
			session.setAttribute("usercityid", userBean.getUser()
					.getUser_city_id());
			session.setAttribute("usertel", userBean.getUser().getUser_tel());
			session.setAttribute("userareaid", userBean.getUser().getUser_area_id());
			if (backstageSelect.equals("1")) {
				String areaResult = areaService.queryArea();

				AreaBean areaBean = new AreaBean();
				Type addresstargetType = new TypeToken<AreaBean>() {
				}.getType();
				areaBean = gson.fromJson(areaResult, addresstargetType);
				modelMap.put("areaList", areaBean.getArea());
				return "submitOrder";
			} else {
				return "submitUser";
			}

		} else {
			return userBean.getRetmsg();
		}

	}

	/**
	 * WebQueryUser
	 */
	@RequestMapping(value = "/WebQueryUser", method = RequestMethod.GET)
	public String WebQueryUser(HttpServletRequest request, User user,
			ModelMap modelMap) {
		String userResult = userService.queryAllUser();
		UserListBean userListBean = new UserListBean();
		Type userTargetType = new TypeToken<UserListBean>() {
		}.getType();
		userListBean = gson.fromJson(userResult, userTargetType);
		
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
		
//		modelMap.put("userList", userListBean.getUser());
		modelMap.put("cityList", cityBean.getCity());
		modelMap.put("areaList", areaBean.getArea());
		List<String> userCityList = new ArrayList<String>();
		List<String> userAreaList = new ArrayList<String>();
		if(userListBean.getUser()!=null){
		for(int i = 0;i<userListBean.getUser().size();i++)
		{
			for(int m = 0;m<cityBean.getCity().size();m++)
			{
				if(userListBean.getUser().get(i).getUser_city_id()==cityBean.getCity().get(m).getCity_id())
				{
					userCityList.add(cityBean.getCity().get(m).getCity_name());
				}
			}
		}
		
		for(int i = 0;i<userListBean.getUser().size();i++)
		{
			for(int m = 0;m<areaBean.getArea().size();m++)
			{
				if(userListBean.getUser().get(i).getUser_area_id()==areaBean.getArea().get(m).getArea_id())
				{
					userAreaList.add(areaBean.getArea().get(m).getArea_name());
				}
			}
		}
		
		List<ShowUserDto> showUserDtoList = new ArrayList<ShowUserDto>();
		for(int i = 0;i<userListBean.getUser().size();i++)
		{
			ShowUserDto showUserDto = new ShowUserDto();
			showUserDto.setUser_area_name(userAreaList.get(i));
			showUserDto.setUser_city_name(userCityList.get(i));
			showUserDto.setUser_id(userListBean.getUser().get(i).getUser_id());
			showUserDto.setUser_name(userListBean.getUser().get(i).getUser_name());
			showUserDto.setUser_pwd(userListBean.getUser().get(i).getUser_pwd());
			showUserDto.setUser_tel(userListBean.getUser().get(i).getUser_tel());
			showUserDtoList.add(showUserDto);
		}
		modelMap.put("showUserDtoList", showUserDtoList);
		}
		return "submitUser";
	}
	
	/**
	 * WebUpdateUser
	 */
	@RequestMapping(value = "/WebDeleteUser", method = RequestMethod.GET)
	@ResponseBody
	public String WebDeleteUser(HttpServletRequest request,ModelMap modelMap) 
	{
		return userService.deleteUser(request.getParameter("userIdK").toString());
	}

}
