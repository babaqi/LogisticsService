package com.glodon.util;

import java.util.List;

public class PageUtil {
	
	/**
	 * @param url 跳转路径
	 * @param recordCount 记录总数
	 * @param objects 当前页记录
	 * */
	public PageUtil(String url, int recordCount,List<Object> objects){
		this.url = url;
		this.recordCount = recordCount;
		this.objects = objects;
	}
	
	/**
	 * @param url 跳转路径
	 * @param recordCount 记录总数
	 * @param objects 当前页记录
	 * */
	public PageUtil(String url, int recordCount){
		this.url = url;
		this.recordCount = recordCount;
	}
	
	/**
	 * @param url 跳转路径
	 * @param recordCount 记录总数
	 * @param page 当前页码
	 * */
	public PageUtil(String url, int recordCount , int page){
		this.url = url;
		this.recordCount = recordCount;
		this.page = page;
	}
	
	//当前页码
	private int page = 1;
	//每页记录数
	private int pageSize = 10;
	//总页数
	private int pageCount;
	//记录总数
	private int recordCount;
	//当前页面的记录集合
	private List objects;
	//跳转路径
	private String url;
	//分页采用ajax提交用到，得到js方法名字
	private String methodName = "pagingAjax";
	//分页采用form提交用到，得到form名字
	private String formName = "pagingForm";
	private String pagingStr = "";



	//自用变量
	private int startPage = 1;
	private int endPage = 1;
	private int extendPage = 8;
	private String para = "?page=";
	
	/**
	 * 采用get方式分页
	 * */
	public String PagingWithGet(Boolean UrlIncludeParas){
		if(UrlIncludeParas){
			para = "&page=";
		}
		pageCompute();
		if(pageCount == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		//构建分页HTML
		sb.append("<!-- 分页开始 -->");
		sb.append("<div class=\"fenye\">");
		sb.append("<div class=\"jy\"><span>共" + pageCount + "页</span>到<input class=\"input\" id=\"skipPage\" type=\"text\" />页<input class=\"qd\" type=\"button\" onclick=\"javascript:pagingSkip()\" value=\"确定\" /></div>");
		sb.append ("<ul>");
		if(startPage != 1)
			sb.append("<li><a href=\"" + url + para + 1 +"\">首页</a></li>");
		sb.append("<li><a href=\"" + url + para + ((page - 1)<1?1:(page - 1)) +"\">上一页</a></li>");
		
		for(int i = startPage; i <= endPage; i++){
			if(page == i)
				sb.append("<li><a class=\"select\" >" + i + "</a></li>");
			else
				sb.append("<li><a href=\"" + url + para + i + "\">" + i + "</a></li>");
		}
		sb.append("<li><a href=\"" + url + para + ((page + 1)>pageCount?pageCount:(page + 1)) +"\">下一页</a></li>");
		if(endPage != pageCount)
			sb.append("<li><a href=\"" + url + para + pageCount +"\">尾页</a></li>");

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- 分页结束 -->");
		sb.append("<script type='text/javascript'>");
		sb.append("function pagingSkip(){");
		sb.append(" var page = document.getElementById('skipPage').value;");
		sb.append("if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;");
		sb.append("location.href = \""+ url + para + "\" + page;");
		sb.append("}");
		sb.append("</script>");
		this.pagingStr = sb.toString();
		return sb.toString();
	}
	
	/**
	 * 采用form方式分页
	 * */
	public String PagingWithForm(){
		pageCompute();
		if(pageCount == 0)
			return "";
		String action = url + para;
		StringBuilder sb = new StringBuilder();
		//构建分页HTML
		sb.append("<!-- 分页开始 -->");
		sb.append("<div class=\"fenye\">");
		sb.append("<div class=\"jy\"><span>共" + pageCount + "页</span>到<input class=\"input\" id=\"skipPage\" type=\"text\" />页<input class=\"qd\" type=\"button\" onclick=\"javascript:pagingFormSkip()\" value=\"确定\" /></div>");
		sb.append ("<ul>");
		if(startPage != 1)
			sb.append("<li><a href=\"javascript:pagingForm("  + 1 +")\">首页</a></li>");
		sb.append("<li><a href=\"javascript:pagingForm(" + ((page - 1)<1?1:(page - 1)) +")\">上一页</a></li>");
				
		for(int i = startPage; i <= endPage; i++){
			if(page == i)
				sb.append("<li><a class=\"select\" >" + i + "</a></li>");
			else
				sb.append("<li><a href=\"javascript:pagingForm(" + i + ")\">" + i + "</a></li>");
		}
		sb.append("<li><a href=\"javascript:pagingForm(" + ((page + 1)>pageCount?pageCount:(page + 1)) +")\">下一页</a></li>");
		if(endPage != pageCount)
			sb.append("<li><a href=\"javascript:pagingForm(" + pageCount +")\">尾页</a></li>");

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- 分页结束 -->");
		//form提交方法
		sb.append("<script type='text/javascript'>");
		sb.append("function pagingForm(pageNum){");
		sb.append("	document.getElementById('" + formName + "').action=\"" + action + "\"+ pageNum;");
		sb.append("	document.getElementById('" + formName + "').submit();");
		sb.append("}");
		sb.append("function pagingFormSkip(){");
		sb.append(" var page = document.getElementById('skipPage').value;");
		sb.append("if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;");
		sb.append("	document.getElementById('" + formName + "').action=\"" + action + "\"+ page;");
		sb.append("	document.getElementById('" + formName + "').submit();");
		sb.append("}");
		sb.append("</script>");
		this.pagingStr = sb.toString();
		return sb.toString();
	}
	
	/**
	 * 采用Ajax方式分页
	 * */
	public String PagingWithAjax(){
	    //时间戳加入到分页执行方法名中，确保当页面有多个分页时方法区分开
	    String time = System.currentTimeMillis()+"";
		pageCompute();
		if(pageCount == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		//构建分页HTML
		sb.append("<!-- 分页开始 -->");
		sb.append("<div class=\"fenye\"><input type=\"hidden\" class=\"thisPage\" value=\""+page+"\">");
		sb.append("<div class=\"jy\"><span>共" + pageCount + "页</span>到<input class=\"input\" type=\"text\" />页<input class=\"qd\" type=\"button\" onclick=\"javascript:pagingSkip"+time+"(this)\" value=\"确定\" /></div>");
        sb.append ("<ul>");
		if(startPage != 1)
			sb.append("<li><a href=\"javascript:" + methodName + "("  + 1 +")\">首页</a></li>");
		sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page - 1)<1?1:(page - 1)) +")\">上一页</a></li>");
				
		for(int i = startPage; i <= endPage; i++){
			if(page == i)
				sb.append("<li><a class=\"select\" >" + i + "</a></li>");
			else
				sb.append("<li><a href=\"javascript:" + methodName + "(" + i + ")\">" + i + "</a></li>");
		}
		sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page + 1)>pageCount?pageCount:(page + 1)) +")\">下一页</a></li>");
		if(endPage != pageCount)
			sb.append("<li><a href=\"javascript:" + methodName + "(" + pageCount +")\">尾页</a></li>");

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- 分页结束 -->");
		sb.append("<script type='text/javascript'>");
		sb.append("function pagingSkip"+time+"(obj){");
		sb.append(" var page = obj.previousSibling.previousSibling.value;");
		sb.append("if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;");
		sb.append(methodName + "(page);");
		sb.append("}");
		sb.append("</script>");
		this.pagingStr = sb.toString();
		return sb.toString();
	}
	
	/**
	 * 工程折扣页面分页样式
	 * */
	public String PagingWithAjaxForDuty(){
	    //时间戳加入到分页执行方法名中，确保当页面有多个分页时方法区分开
	    String time = System.currentTimeMillis()+"";
		pageCompute();
		if(pageCount == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		//构建分页HTML
		sb.append("<!-- 分页开始 -->");
		sb.append("<div class=\"fenye\" style=\"width:640px;\"><input type=\"hidden\" class=\"thisPage\" value=\""+page+"\">");
		sb.append("<div class=\"jy\"><span>共" + pageCount + "页</span>到<input class=\"input\" type=\"text\" />页<input class=\"qd\" type=\"button\" onclick=\"javascript:pagingSkip"+time+"(this)\" value=\"确定\" /></div>");
        sb.append ("<ul class=\"ckhs\">");
		if(startPage != 1)
			sb.append("<li><a href=\"javascript:" + methodName + "("  + 1 +")\">首页</a></li>");
		sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page - 1)<1?1:(page - 1)) +")\">上一页</a></li>");
				
		for(int i = startPage; i <= endPage; i++){
			if(page == i)
				sb.append("<li><a class=\"select\" >" + i + "</a></li>");
			else
				sb.append("<li><a href=\"javascript:" + methodName + "(" + i + ")\">" + i + "</a></li>");
		}
		sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page + 1)>pageCount?pageCount:(page + 1)) +")\">下一页</a></li>");
		if(endPage != pageCount)
			sb.append("<li><a href=\"javascript:" + methodName + "(" + pageCount +")\">尾页</a></li>");

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- 分页结束 -->");
		sb.append("<script type='text/javascript'>");
		sb.append("function pagingSkip"+time+"(obj){");
		sb.append(" var page = obj.previousSibling.previousSibling.value;");
		sb.append("if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;");
		sb.append(methodName + "(page);");
		sb.append("}");
		sb.append("</script>");
		this.pagingStr = sb.toString();
		return sb.toString();
	}
	/*public String PagingWithAjax(){
        pageCompute();
        if(pageCount == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        //构建分页HTML
        sb.append("<!-- 分页开始 -->");
        sb.append("<div class=\"fenye\">");
        sb.append("<div class=\"jy\"><span>共" + pageCount + "页</span>到<input class=\"input\" id=\"skipPage\" type=\"text\" />页"
                + "<input class=\"qd\" type=\"button\" onclick=\"var page = obj.previousSibling.previousSibling.value;"
                + "if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;"
                        + methodName + "(page);\" value=\"确定\" /></div>");
        sb.append ("<ul>");
        if(startPage != 1)
            sb.append("<li><a href=\"javascript:" + methodName + "("  + 1 +")\">首页</a></li>");
        sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page - 1)<1?1:(page - 1)) +")\">上一页</a></li>");
                
        for(int i = startPage; i <= endPage; i++){
            if(page == i)
                sb.append("<li><a class=\"select\" >" + i + "</a></li>");
            else
                sb.append("<li><a href=\"javascript:" + methodName + "(" + i + ")\">" + i + "</a></li>");
        }
        sb.append("<li><a href=\"javascript:" + methodName + "(" + ((page + 1)>pageCount?pageCount:(page + 1)) +")\">下一页</a></li>");
        if(endPage != pageCount)
            sb.append("<li><a href=\"javascript:" + methodName + "(" + pageCount +")\">尾页</a></li>");

        sb.append("</ul>");
        sb.append("</div>");
        sb.append("<!-- 分页结束 -->");
//        sb.append("<script type='text/javascript'>");
//        sb.append("function pagingSkip(obj){");
//        sb.append(" var page = obj.previousSibling.previousSibling.value;");
//        sb.append("if(page == \"\" || isNaN(page)|| page > "+ pageCount +" || page < 1) return;");
//        sb.append(methodName + "(page);");
//        sb.append("}");
//        sb.append("</script>");
        this.pagingStr = sb.toString();
        return sb.toString();
    }*/
	
	/**
	 * 计算分页HTML显示的起始页与结束页
	 * */
	private void pageCompute(){
		//计算总页数
		if(recordCount % pageSize == 0)
			pageCount = recordCount / pageSize;
		else
			pageCount = recordCount / pageSize + 1;
		
		if (pageCount > extendPage)
	    {
	        if (page - (extendPage / 2) > 0)
	        {
	            if (page + (extendPage / 2) < pageCount)
	            {
	                startPage = page - (extendPage / 2);
	                endPage = startPage + extendPage - 1;
	            }
	            else
	            {
	                endPage = pageCount;
	                startPage = endPage - extendPage + 1;
	            }
	        }
	        else
	        {
	            endPage = extendPage;
	        }
	    }
	    else
	    {
	        startPage = 1;
	        endPage = pageCount;
	    }
	}
	
	/**
	 * 得到当前页码
	 * */
	public int getPage() {
		return page;
	}
	/**
	 * 设置当前页码
	 * */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * 得到每页记录数
	 * */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页记录数
	 * */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 得到总页数
	 * */
	public int getPageCount() {
		return pageCount;
	}
	/**
	 * 设置总页数
	 * */
	public void setPageCount(int pageCount) {
		pageCount = pageCount;
	}
	
	/**
	 * 得到记录总数
	 * */
	public int getRecordCount() {
		return recordCount;
	}
	/**
	 * 设置记录总数
	 * */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	/**
	 * 得到当前页面的记录集合
	 * */
	public List getObjects() {
		return objects;
	}
	/**
	 * 设置当前页面的记录集合
	 * */
	public void setObjects(List objects) {
		this.objects = objects;
	}
	
	/**
	 * 得到跳转路径
	 * */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置跳转路径
	 * */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 得到js方法名
	 * */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * 分页采用ajax提交时用到，设置js方法名
	 * */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * 得到form名
	 * */
	public String getFormName() {
		return formName;
	}
	/**
	 * 分页采用form提交时用到，设置form名
	 * */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	public String getPagingStr() {
		return pagingStr;
	}

	public void setPagingStr(String pagingStr) {
		this.pagingStr = pagingStr;
	}

	public int getExtendPage() {
		return extendPage;
	}

	public void setExtendPage(int extendPage) {
		this.extendPage = extendPage;
	}
	
}
