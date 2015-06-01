package com.glodon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.ContextBean;
import com.glodon.bean.ShopListBean;
import com.glodon.bean.UserListBean;
import com.glodon.bean.countbean;
import com.glodon.common.Constants;
import com.glodon.dao.CountDao;
import com.glodon.model.Context;
import com.glodon.model.Shop;
import com.glodon.model.User;
import com.glodon.service.CountService;
import com.glodon.util.EmptyUtil;
import com.glodon.util.Utils;
import com.google.gson.Gson;

@Service("countService")
public class CountServiceImpl implements CountService {
	@Autowired
	private CountDao countDao;
	Gson gson = new Gson();

	@Override
	public String countOrder(String context_timer) {
		// TODO Auto-generated method stub
		int count = countDao.countOrder(context_timer);
		String couna = String.valueOf(count);
		countbean acountbean = new countbean();
		if (couna!=null) {
			acountbean.setRetcode(Constants.SUCCESS_CODE);
			acountbean.setRetmsg("查询成功");
			acountbean.setCount(couna);
		} else {
			acountbean.setRetcode(Constants.ERROR_CODE);
			acountbean.setRetmsg("查询失败");
		}
		String result = gson.toJson(acountbean);
		return result;
	}

	@Override
	public String queryOrder(String areaid, String data) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("areaid", areaid);
		paramMap.put("data", data);
		paramMap.put("nextdata", Utils.getSpecifiedDayAfter(data));
		
		List<Context> contextList = countDao.queryOrder(paramMap);
		ContextBean contextBean = new ContextBean();
		if (EmptyUtil.isNotEmpty(contextList)) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("查询成功");
			contextBean.setContext(contextList);
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(contextBean);
		return result;

	}

	@Override
	public String queryUser(String areaid) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("areaid", areaid);

		List<User> userList = countDao.queryUser(paramMap);
		UserListBean contextBean = new UserListBean();
		if (EmptyUtil.isNotEmpty(userList)) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("查询成功");
			contextBean.setUser(userList);
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(contextBean);
		return result;
	}

	@Override
	public String queryShop(String areaid) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("areaid", areaid);

		List<Shop> userList = countDao.queryShop(paramMap);
		ShopListBean contextBean = new ShopListBean();
		if (EmptyUtil.isNotEmpty(userList)) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("查询成功");
			contextBean.setShop(userList);
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(contextBean);
		return result;
	}

}
