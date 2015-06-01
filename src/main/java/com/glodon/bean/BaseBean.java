package com.glodon.bean;

/**
 * 返回客户端基类Bean
 * 
 * @author Muz
 *
 *         2015年1月30日 下午3:29:12
 */
public class BaseBean {

	/** 返回给客户端的code **/
	public String retcode;

	/** 返回给客户端信息 **/
	public String retmsg;

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

}
