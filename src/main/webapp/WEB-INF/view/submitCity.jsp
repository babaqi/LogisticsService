<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content="1080">
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery-2.1.1.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/updateCity.js"></script>
<title>我的文件夹</title>

<style type="text/css">
div {
	background-color: #fff;
	border: 1px solid #000;
	margin: 2px;
}

#page_container {
	border: 0px;
	width: 100%;
	margin: 0 auto;
	text-align: left;
}

#banner {
	height: 100px;
	margin-bottom:30px;
	background-color: #3598DB;
}

#left {
	width: 10%;
	height: auto;
	float: left;
}

#center {
	width: 89%;
	height:auto;
	float: right;
	overflow: auto;
}

#bottom {
	clear: both;
	height: 50px;
}


</style>
</head>
<body bgcolor="#F65327">
	<div id="page_container" class="panel panel-info">
		<div id="banner" class="panel panel-info">
		<img src="../images/bgcolor.png" height="92px" width="1280px"/> 
		</div>
		<div id="left" class="panel panel-info"><%@ include file="manageNavigation.jsp"%></div>
		<div id="center" class="panel panel-info">
			<div class="panel-heading">
      			<h3 class="panel-title">添加城市</h3>
  			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
					<tr>
						<td>城市名</td>
						<td colspan="2"><input id="city_name_add" name="city_name_add" /></td>
					</tr>
					<tr>
						<td colspan="3"><input type="button" id="city_add_btn" name="city_add_btn" onclick="submitCity()" value="添加" /></td>
					</tr>
				</table>
				<br/>
					<div class="panel-heading">
      			<h3 class="panel-title">城市列表</h3>
  			</div>
			<table  width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
					<tr>
						<td>城市Id</td>
						<td>城市名</td>
						<td>操作</td>
					</tr>
					<tr>
						<c:forEach items="${cityList}" var="cityList" varStatus="status">
							<tr>
								<td><input type="hidden" id="city_id_${status.count}"
									value="${cityList.city_id}" />${cityList.city_id}</td>
								<td><input value="${cityList.city_name}"
									id="city_name_${status.count}" name="city_name" /></td>
								<td><input type="button"
									id="submitCityButton_${status.count}" name="Submit" value="提交" />
									<%-- <input type="button"
									id="deleteCityButton_${status.count}" name="Delete" value="删除" /> --%>
								</td>
							</tr>
						</c:forEach>
					</tr>
				</table>
		</div>
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
