package com.glodon.service;

import com.glodon.model.User;


public interface UserService {

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	public String register(User user);
	/**
	 * 查询所有用户
	 * 
	 * @param user
	 * @return
	 */
	public String queryAllUser();

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(User user);

	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean isUserExist(String username);
	/**
	 * 删除用户
	 * 
	 * @param username
	 * @return
	 */
	public String deleteUser(String userId);

}
