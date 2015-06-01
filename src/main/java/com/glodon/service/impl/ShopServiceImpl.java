package com.glodon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.ShopBean;
import com.glodon.bean.ShopListBean;
import com.glodon.bean.UserBean;
import com.glodon.common.Constants;
import com.glodon.dao.ShopDao;
import com.glodon.model.Shop;
import com.glodon.service.ShopService;
import com.glodon.util.EmptyUtil;
import com.google.gson.Gson;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	private Gson gson = new Gson();

	@Override
	public String insertShop(Shop shop) {
		// TODO Auto-generated method stub
	
		ShopBean shopBean = new ShopBean();
		boolean isExist = isUserExist(shop.getShop_name());
		if (!isExist) {
			int id = shopDao.insertShop(shop);
			if (id > 0) {
				shopBean.setRetcode(Constants.SUCCESS_CODE);
				shopBean.setRetmsg("注册成功");
				shop.setShop_id(id);
				shopBean.setShop(shop);
			} else {
				shopBean.setRetcode(Constants.ERROR_CODE);
				shopBean.setRetmsg("注册失败");
			}
		}
		String result = gson.toJson(shopBean);
		return result;
	}

	@Override
	public String queryShop(String shopName,String shopPwd) {
		// TODO Auto-generated method stub
		
		ShopBean baseBean = new ShopBean();
		Shop shop = new Shop();
		shop = shopDao.queryShop(shopName);
		// 判断用户是否已经存在
		if (EmptyUtil.isNotEmpty(shop)) {
			if (shop.getShop_pwd().equals(shopPwd)) {
				baseBean.setRetcode(Constants.SUCCESS_CODE);
				baseBean.setRetmsg("登录成功");
				baseBean.setShop(shop);
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

	@Override
	public String updateShop(String jsonShop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteShop(String shopId) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("shopId", shopId);
		int line = shopDao.deleteshop(map);

		ShopBean shopBean = new ShopBean();
		if (line > 0) {
			shopBean.setRetcode(Constants.SUCCESS_CODE);
			shopBean.setRetmsg("更新成功");
		} else {
			shopBean.setRetcode(Constants.ERROR_CODE);
			shopBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(shopBean);
		return result;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean isUserExist(String username) {
		Shop shop = shopDao.queryShop(username);
		if (EmptyUtil.isNotEmpty(shop)) {
			return true;
		}
		return false;
	}

	@Override
	public String queryAllShop() {
		// TODO Auto-generated method stub
		ShopListBean shopListBean = new ShopListBean();
		List<Shop> shopList = shopDao.queryAllShop();
		
		if (EmptyUtil.isNotEmpty(shopList)) {
			shopListBean.setRetcode(Constants.SUCCESS_CODE);
			shopListBean.setRetmsg("登录成功");
			shopListBean.setShop(shopList);
		} else {
			shopListBean.setRetcode(Constants.ERROR_CODE);
			shopListBean.setRetmsg("登录失败,用户名不存在");
		}
		String result = gson.toJson(shopListBean);
		return result;
	}

}
