package com.glodon.bean;


import java.util.List;

import com.glodon.model.Context;

public class ContextBean extends BaseBean {

	private List<Context> context;
	private String count;

	public List<Context> getContext() {
		return context;
	}

	public void setContext(List<Context> context) {
		this.context = context;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}



	

}
