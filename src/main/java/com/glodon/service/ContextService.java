package com.glodon.service;

import com.glodon.model.Context;


public interface ContextService {

	/**
	 * 提交表单
	 * 
	 * @param
	 * @return
	 */
	public String submitInfo(Context context);

	/**
	 * 查询表单
	 * 
	 * @param
	 * @return
	 */
	public String queryInfo(String page ,String state,String areaId,String userId,String shopId);
	
	/**
	 * 历史订单根据时间
	 * 
	 * @param
	 * @return
	 */
	public String queryInfobydata(String userId,String shopId,String data);
	/**
	 * 订单调度查询
	 * 
	 * @param
	 * @return
	 */
	public String webMoveQueryOrder(String page ,String state);
	/**
	 * 订单调度更改
	 * 
	 * @param
	 * @return
	 */
	public String webMoveOrder(String contextid ,String userId,String userName,String contextareaId);

	/**
	 * 修改表单
	 * 
	 * @param
	 * @return
	 */
	public String updateInfo(String contextid,String contexttype);

	/**
	 * rob表单
	 * 
	 * @param
	 * @return
	 */
	public String robOrderInfo(String jsoncontext);

}
