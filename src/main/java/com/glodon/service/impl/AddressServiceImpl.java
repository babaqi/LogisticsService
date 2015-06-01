package com.glodon.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.bean.AddressBean;
import com.glodon.bean.AreaBean;
import com.glodon.common.Constants;
import com.glodon.dao.AddressDao;
import com.glodon.model.Address;
import com.glodon.service.AddressService;
import com.glodon.util.EmptyUtil;
import com.google.gson.Gson;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	private Gson gson = new Gson();

	@Override
	public String insertAddress(String addressAreaid,String addressName) {
		// TODO Auto-generated method stub
		Address address = new Address();
		address.setAddress_areaid(Integer.parseInt(addressAreaid));
		address.setAddress_name(addressName);
		
		int id = addressDao.insertAddress(address);
		AddressBean addressBean = new AddressBean();
		if (id>0) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("查询成功");
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(addressBean);
		return result;
		
	}

	@Override
	public String queryAddress(String cityid) {
		// TODO Auto-generated method stub
		List<Address> list = new ArrayList<Address>();
		list = addressDao.queryAddress(Integer.parseInt(cityid));
		AddressBean addressBean = new AddressBean();
		if (EmptyUtil.isNotEmpty(list)) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("查询成功");
			addressBean.setAddress(list);
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(addressBean);
		return result;
	}


	@Override
	public String updateAddress(String addressAreaid, String addressName) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", addressAreaid);
		map.put("name", addressName);
		
		int line = addressDao.updateAddress(map);

		AddressBean addressBean = new AddressBean();
		if (line > 0) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("更新成功");
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(addressBean);
		return result;
	}

	@Override
	public String deleteAddress(String addressAreaid) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", addressAreaid);
		int line = addressDao.deleteAddress(map);

		AddressBean addressBean = new AddressBean();
		if (line > 0) {
			addressBean.setRetcode(Constants.SUCCESS_CODE);
			addressBean.setRetmsg("更新成功");
		} else {
			addressBean.setRetcode(Constants.ERROR_CODE);
			addressBean.setRetmsg("更新失败");
		}
		String result = gson.toJson(addressBean);
		return result;
	}


}
