package com.glodon.service;

public interface AddressService {

	public String insertAddress(String addressAreaid,String addressName);

	public String queryAddress(String cityid);

	public String updateAddress(String addressAreaid,String addressName);

	public String deleteAddress(String addressAreaid);

}
