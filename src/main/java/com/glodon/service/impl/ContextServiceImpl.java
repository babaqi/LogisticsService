package com.glodon.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.glodon.bean.ContextBean;
import com.glodon.common.Constants;
import com.glodon.dao.ContextDao;
import com.glodon.model.Context;
import com.glodon.service.ContextService;
import com.glodon.util.EmptyUtil;
import com.glodon.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("contextService")
public class ContextServiceImpl implements ContextService {

	@Autowired
	private ContextDao contextDao;

	private Gson gson = new Gson();

	/**  
	 * 提交表单
	 */
	public String submitInfo(Context context) {
		// TODO Auto-generated method stub

		int id = contextDao.insert(context);

		ContextBean contextBean = new ContextBean();
		if (id > 0) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("发布成功");
			final String appKey = "1d9d6d741a37d639b4615b43";
			final String masterSecret = "c15ff679d3cceb16e52dc9d5";
			JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
			// For push, all you need do is to build PushPayload object.
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
					.setAudience(Audience.alias(String.valueOf(context.getContext_areaid())))
					.setNotification(Notification.alert("您有新的订单!")).build();
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				System.out.println("Should review the error, and fix the request"
						+ e);
				System.out.println("HTTP Status: " + e.getStatus());
				System.out.println("Error Code: " + e.getErrorCode());
				System.out.println("Error Message: " + e.getErrorMessage());
			}

		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("发布失败");
		}

		String result = gson.toJson(contextBean);
		return result;
	}

	/**
	 * 更新表单
	 */
	@Override
	public String updateInfo(String contextid, String contexttype) {
		// TODO Auto-generated method stub

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("context_id", Integer.parseInt(contextid));
		paramMap.put("context_type", Integer.parseInt(contexttype));
		// paramMap.put("context_userid", Integer.parseInt(map.get("userid")));

		int id = contextDao.update(paramMap);

		ContextBean contextBean = new ContextBean();
		if (id > 0) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("更新成功");
			// Context context = new Context();
			// context.setContext_id(id);
			// List<Context> c = new ArrayList<Context>();
			// c.add(context);
			// contextBean.setContext(c);
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("更新失败");
		}

		String result = gson.toJson(contextBean);
		return result;
	}

	/**
	 * 查询表单
	 */

	public String queryInfo(String page, String state, String areaId,
			String userId, String shopId) {
		// TODO Auto-generated method stub

		int pagea = Integer.parseInt(page);

		int beginnum = pagea * 10;
		int endnum = 10;
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("beginnum", beginnum);
		paramMap.put("endnum", endnum);
		if (state != null) {
			paramMap.put("context_type", Integer.parseInt(state));
		}
		if (areaId != null) {
			paramMap.put("areaId", Integer.parseInt(areaId));
		}
		if (userId != null) {
			paramMap.put("userId", Integer.parseInt(userId));
		}
		if (shopId != null) {
			paramMap.put("shopId", Integer.parseInt(shopId));
		}

		List<Context> contextList = contextDao.query(paramMap);
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

	/**
	 * rob表单
	 */
	@Override
	public String robOrderInfo(String jsoncontext) {
		// TODO Auto-generated method stub
		Type targetType = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsoncontext, targetType);
		map.get("context_id");
		String shopid=map.get("shop_id");
		map.get("state");
		map.get("userid");
		map.get("username");
		
		String context_id = contextDao.robOrderInfo(Integer.parseInt(map
				.get("context_id")));
		
		ContextBean contextBean = new ContextBean();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (context_id.equals("0")) {
			paramMap.put("context_id", Integer.parseInt(map.get("context_id")));
			paramMap.put("context_type", Integer.parseInt(map.get("state")));
			paramMap.put("context_userid", Integer.parseInt(map.get("userid")));
			paramMap.put("username", map.get("username").toString());
			
			int id = contextDao.update(paramMap);
			if (id > 0) {
				contextBean.setRetcode(Constants.SUCCESS_CODE);
				contextBean.setRetmsg("抢单成功");
				final String appKey = "70d2ce47983937286f07bfcc";
				final String masterSecret = "b005712a35a3e43a9e46bc1d";
				JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
				// For push, all you need do is to build PushPayload object.
				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
						.setAudience(Audience.alias(String.valueOf(shopid)))
						.setNotification(Notification.alert("您的订单已被接收!")).build();
				try {
					PushResult result = jpushClient.sendPush(payload);
					System.out.println(result);
				} catch (APIConnectionException e) {
					e.printStackTrace();
				} catch (APIRequestException e) {
					System.out.println("Should review the error, and fix the request"
							+ e);
					System.out.println("HTTP Status: " + e.getStatus());
					System.out.println("Error Code: " + e.getErrorCode());
					System.out.println("Error Message: " + e.getErrorMessage());
				}
			} else {
				contextBean.setRetcode(Constants.ERROR_CODE);
				contextBean.setRetmsg("抢单失败");
			}
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("抢单失败");
		}
		String result = gson.toJson(contextBean);
		return result;
	}

	@Override
	public String webMoveQueryOrder(String page, String state) {
		// TODO Auto-generated method stub
		int pagea = Integer.parseInt(page);

		int beginnum = pagea * 10;
		int endnum = 10;
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("beginnum", beginnum);
		paramMap.put("endnum", endnum);
		paramMap.put("context_type", Integer.parseInt(state));
		

		List<Context> contextList = contextDao.movequery(paramMap);
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
	public String webMoveOrder(String contextid, String userId, String userName,String contextareaId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("context_id", Integer.parseInt(contextid));
//		paramMap.put("context_userid", Integer.parseInt(userId));
//		paramMap.put("context_user_name", userName);
		// paramMap.put("context_userid", Integer.parseInt(map.get("userid")));

//		int id = contextDao.webMoveOrder(paramMap);

		ContextBean contextBean = new ContextBean();
//		if (id > 0) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("更新成功");
			final String appKey = "1d9d6d741a37d639b4615b43";
			final String masterSecret = "c15ff679d3cceb16e52dc9d5";
			JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
			// For push, all you need do is to build PushPayload object.
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
					.setAudience(Audience.alias(String.valueOf(contextareaId)))
					.setNotification(Notification.alert("您有新的订单!")).build();
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				System.out.println("Should review the error, and fix the request"
						+ e);
				System.out.println("HTTP Status: " + e.getStatus());
				System.out.println("Error Code: " + e.getErrorCode());
				System.out.println("Error Message: " + e.getErrorMessage());
			}
			// Context context = new Context();
			// context.setContext_id(id);
			// List<Context> c = new ArrayList<Context>();
			// c.add(context);
			// contextBean.setContext(c);
//		} else {
//			contextBean.setRetcode(Constants.ERROR_CODE);
//			contextBean.setRetmsg("更新失败");
//		}

		String result = gson.toJson(contextBean);
		return result;
		
	}

	/**
	 * 所有平台，推送目标是别名为 "alias1"
	 * 
	 * */
	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.alias("1"))
				.setNotification(Notification.alert("所有平台，推送目标是别名!")).build();
	}

	/**
	 * 查询历史记录根据时间
	 */
	@Override
	public String queryInfobydata(String userId,String shopId, String data) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		paramMap.put("shopId", shopId);
		paramMap.put("data", data);
		paramMap.put("nextdata", Utils.getSpecifiedDayAfter(data));
		List<Context> contextList = contextDao.queryOrderbydata(paramMap);
		ContextBean contextBean = new ContextBean();
		if (EmptyUtil.isNotEmpty(contextList)) {
			contextBean.setRetcode(Constants.SUCCESS_CODE);
			contextBean.setRetmsg("查询成功");
			contextBean.setContext(contextList);
			contextBean.setCount(String.valueOf(contextList.size()));
		} else {
			contextBean.setRetcode(Constants.ERROR_CODE);
			contextBean.setRetmsg("查询失败");
		}

		String result = gson.toJson(contextBean);
		return result;
	}
}
