package com.glodon.bean;

import java.util.List;

import com.glodon.model.User;

public class UserListBean extends BaseBean {

	private List<User> user;
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}


}
