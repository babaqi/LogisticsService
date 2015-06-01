package com.glodon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.AreaBean;
import com.glodon.bean.UserBean;
import com.glodon.bean.UserListBean;
import com.glodon.common.Constants;
import com.glodon.dao.UserDao;
import com.glodon.model.User;
import com.glodon.service.UserService;
import com.glodon.util.EmptyUtil;
import com.google.gson.Gson;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private Gson gson = new Gson();

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String register(User user) {
		// 判断用户是否已经存在
		boolean isExist = isUserExist(user.getUser_name());
		UserBean userBean = new UserBean();
		if (!isExist) {
			int id = userDao.insert(user);
			if (id > 0) {
				userBean.setRetcode(Constants.SUCCESS_CODE);
				userBean.setRetmsg("注册成功");
				user.setUser_id(id);
				userBean.setUser(user);
			} else {
				userBean.setRetcode(Constants.ERROR_CODE);
				userBean.setRetmsg("注册失败");
			}
		} else {
			userBean.setRetcode(Constants.ERROR_CODE);
			userBean.setRetmsg("用户名已存在");
		}

		String result = gson.toJson(userBean);
		return result;

	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public String login(User usera) {
		UserBean baseBean = new UserBean();
		User user = userDao.query(usera.getUser_name());
		// 判断用户是否已经存在
		if (EmptyUtil.isNotEmpty(user)) {
			if (user.getUser_pwd().equals(usera.getUser_pwd())) {
				baseBean.setRetcode(Constants.SUCCESS_CODE);
				baseBean.setRetmsg("登录成功");
				baseBean.setUser(user);
			} else {
				baseBean.setRetcode(Constants.ERROR_CODE);
				baseBean.setRetmsg("登录失败,账号或密码错误");
			}
		} else {
			baseBean.setRetcode(Constants.ERROR_CODE);
			baseBean.setRetmsg("登录失败,用户名不存在");
		}
		String result = gson.toJson(baseBean);
		return result;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public boolean isUserExist(String username) {
		User user = userDao.query(username);
		if (EmptyUtil.isNotEmpty(user)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有用户
	 */
	@Override
	public String queryAllUser() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		list = userDao.queryAllUser();
		UserListBean userListBean = new UserListBean();
		if (EmptyUtil.isNotEmpty(list)) {
			userListBean.setRetcode(Constants.SUCCESS_CODE);
			userListBean.setRetmsg("登录成功");
			userListBean.setUser(list);
		} else {
			userListBean.setRetcode(Constants.ERROR_CODE);
			userListBean.setRetmsg("登录失败,用户名不存在");
		}
		String result = gson.toJson(userListBean);
		return result;
	}

	/**
	 * 删除用户
	 */
	@Override
	public String deleteUser(String userId) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		int line = userDao.deleteUser(map);

		UserBean userBean = new UserBean();
		if (line > 0) {
			userBean.setRetcode(Constants.SUCCESS_CODE);
			userBean.setRetmsg("更新成功");
		} else {
			userBean.setRetcode(Constants.ERROR_CODE);
			userBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(userBean);
		return result;
	}

}
