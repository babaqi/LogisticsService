package com.glodon.dao;

import java.util.List;
import java.util.Map;

import com.glodon.model.Address;
import com.glodon.model.Area;

public interface AddressDao {
	public int insertAddress(Address address);

	public List<Address> queryAddress(int areaid);
	
	
	public int updateAddress (Map<String, String> params);
	
	public int deleteAddress(Map<String, String> params);
}
