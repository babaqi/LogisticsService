package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.User;

public interface UserDao {

	public int insert(User user);

	public User query(String username);

	public List<User> queryAllUser();
	public int deleteUser(Map<String,String> map);
}
