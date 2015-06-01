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
<script type="text/javascript"src="<%=request.getContextPath()%>/js/submitShop.js"></script>
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
	height: auto;
	float: right;
	overflow: auto;
}

#bottom {
	clear: both;
	height: 50px;
}


</style>
</head>
<body>
	<div id="page_container" class="panel panel-info">
		<div id="banner" class="panel panel-info">
		<img src="../images/bgcolor.png" height="92px" width="1280px"/> 
		</div>
		<div id="left" class="panel panel-info"><%@ include file="manageNavigation.jsp"%></div>
		<div id="center" class="panel panel-info">
		<div class="panel-heading">
      			<h3 class="panel-title">添加店铺</h3>
  			</div>
			<form id="userForm" name="userForm" method="get" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
									<tr>
										<td>商家名称</td>
										<td><label> <input type="text" id="shop_name" />
										</label>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>商家密码</td>
										<td><input type="text" id="shop_pwd" />
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>商家电话</td>
										<td><input type="text" id="shop_tel" />
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>商家地址</td>
										<td><input type="text" id="shop_address" />
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>所属城市</td>
										<td><select name="city_select" id="city_select">
												<c:forEach items="${cityList }" var="cityList">
													<option value="${cityList.city_id}">${cityList.city_name }</option>
												</c:forEach>

										</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>所属区域</td>
										<td><select name="area_select" id="area_select">
												<c:forEach items="${areaList }" var="areaList">
													<option value="${areaList.area_id }">${areaList.area_name}</option>
												</c:forEach>
										</select>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="2" align="center"><label> <input
												type="Button" name="Submit" onclick="submitShop()"  class="btn btn-primary btn-lg"value="提交" /> </label></td>
									</tr>
								</table>
				</form>
				<div class="panel-heading">
      				<h3 class="panel-title">用户列表</h3>
  			    </div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
					
					<tr>
						<td>商家Id</td>
						<td>商家名</td>
						<td>商家电话</td>
						<td>商家地址</td>
						<td>商家所属城市</td>
						<td>商家所属地区</td>
						<td>操作</td>
					</tr>
					<tr>
					<c:forEach items="${showShopDtoList }" begin="1" var="showShopDtoList" varStatus="status">
					<tr>
						<td><input type="hidden" id="shop_id_${status.count}" value="${showShopDtoList.shop_id}" />${showShopDtoList.shop_id }</td>
						<td>${showShopDtoList.shop_name }</td>
						<td>${showShopDtoList.shop_tel }</td>
						<td>${showShopDtoList.shop_address }</td>
						<td>${showShopDtoList.shop_city_name}</td>
						<td>${showShopDtoList.shop_area_name }</td>
						<td><input type="Button" id="deleteShopButton_${status.count}" value="删除" /></td>
						</tr>
						</c:forEach>
					</tr>
				</table>
		</div>
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
