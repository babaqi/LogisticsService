///**
// * glodon.com Inc.
// * Copyright (c) 2004-2014 All Rights Reserved.
// * com.wzjl.common
// */
//package com.glodon.util;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//
//
///**
// * 这里描述这个方法的作用
// * @author liuy-8 2014年11月4日 上午9:54:33
// */
//public class SessionFilter implements Filter{
//	private static List<String> foregroundFilterWords;
//	private static final  Logger logger = Logger.getLogger(SessionFilter.class);
//	
//	/**
//	 * <p>Title: init</p> 
//	 * <p>Description: </p> 
//	 * @param filterConfig
//	 * @throws ServletException 
//	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
//	 */
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//	
//
//    /**
//     * @Description 检测url路径是否过滤
//     * @author liuy-8
//     * @date 2015年1月21日 下午11:10:02 
//     * @param requestURI 检测是否过滤的URL
//     * @param url 需过滤的url
//     * @return 是否过滤
//     */
//    private boolean isFilter(String requestURI,String url){
//    	boolean isFilter = false;
//    	//http://172.16.231.5:8080/companyshop/19
//    	//第一种情况,过滤中间部分，包含/companyshop/的过滤掉
//    	if(requestURI.lastIndexOf("/" + url + "/") != -1){
//    		isFilter = true;
//    	}
//    	//第二种情况过滤尾部
//    	else if(requestURI.substring(requestURI.lastIndexOf("/")).equals("/" + url)){
//    		isFilter = true;
//    	}    	
//    	return isFilter;
//    }
//	
//	/**
//	 * <p>Title: doFilter</p> 
//	 * <p>Description: </p> 
//	 * @param req
//	 * @param resp
//	 * @param chain
//	 * @throws IOException
//	 * @throws ServletException 
//	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
//	 */
//	public void doFilter(ServletRequest req, ServletResponse resp,
//			FilterChain chain) throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) req;
//        final HttpServletResponse response = (HttpServletResponse) resp;
//        String requestURL = request.getRequestURI();
//        //拼接请求参数
//        if(null != request.getQueryString())
//        	requestURL = requestURL +"?"+request.getQueryString();
//		//前台拦截
//		boolean foregroundFlag = true;
//
//        if(foregroundFlag){
//	        HttpSession session = request.getSession();
//	        if(null != session){
//	        	if(null != session.getAttribute(Constants.SESSION_USER_INFO)){
//	        		//获取存取的用户信息
//	            	User user = (User) session.getAttribute(Constants.SESSION_USER_INFO);
//	            	//检测user中信息是否重新读取,需要则重新读取
//	            	checkUser(user);
//	        	}
//	        	else{
//	        		session.setAttribute(Constants.SESSION_RETURN_URL, requestURL);
//	        		//登陆
//	        		response.sendRedirect("/login");
//	        		return;
//	        	}
//	        }
//        }
//      	
//		//否则放过
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * <p>Title: destroy</p> 
//	 * <p>Description: </p>  
//	 * @see javax.servlet.Filter#destroy() 
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	/**
//	 * @Description 检测user中信息是否重新读取
//	 * @author liuy-8
//	 * @date 2015年1月21日 下午11:12:23 
//	 * @param user 当前登录用户信息
//	 */
//	private void checkUser(User user){
//		//重新读取用户的收藏夹信息
//		if(null == user.getUserFavarite()){
//    		FavoriteService favoriteService = (FavoriteService) SpringContextUtil.getBean("favoriteService");
//    		user.setUserFavarite(favoriteService.selectAll(user.getUserId()));
//    	}
//		//重新读取用户的搜索方案信息
//		if(null == user.getUserProjectSearchPlan()){
//			ProjectSearchPlanService projectSearchPlanService = (ProjectSearchPlanService) SpringContextUtil.getBean("projectSearchPlanService");
//			user.setUserProjectSearchPlan(projectSearchPlanService.selectPlanFormsByUserId(user.getUserId()));
//		}
//		
//	}
//
//}
