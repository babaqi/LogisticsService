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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/submitOrder.js"></script>
<title>我的文件夹</title>

<style type="text/css">
div {
	background-color: #fff;
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
	height: 459px;
	float: left;
}

#center {
	width: 89%;
	height: 459px;
	float: right;
}

#bottom {
	clear: both;
	height: 50px;
}

<!--
body,table {
	font-size: 12px;
}

table {
	table-layout: fixed;
	empty-cells: show;
	border-collapse: collapse;
	margin: 0 auto;
}

td {
	height: 20px;
}

h1,h2,h3 {
	font-size: 12px;
	margin: 0;
	padding: 0;
}

.title {
	background: #FFF;
	border: 1px solid #9DB3C5;
	padding: 1px;
	width: 90%;
	margin: 20px auto;
}

.title h1 {
	line-height: 31px;
	text-align: center;
	background: #2F589C url(th_bg2.gif);
	background-repeat: repeat-x;
	background-position: 0 0;
	color: #FFF;
}

.title th,.title td {
	border: 1px solid #CAD9EA;
	padding: 5px;
}

/*这个是借鉴一个论坛的样式*/
table.t1 {
	border: 1px solid #cad9ea;
	color: #666;
}

table.t1 th {
	background-image: url(th_bg1.gif);
	background-repeat: :repeat-x;
	height: 30px;
}

table.t1 td,table.t1 th {
	border: 1px solid #cad9ea;
	padding: 0 1em 0;
}

table.t1 tr.a1 {
	background-color: #f5fafe;
}
.white {
	color: #606060;
	border: solid 1px #b7b7b7;
	background: #fff;
	background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#ededed));
	background: -moz-linear-gradient(top,  #fff,  #ededed);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#ededed');
}
s-->
</style>
</head>
<body>
	<div id="page_container" class="panel panel-info">
		<div id="banner" class="panel panel-info">
		<img src="../images/bgcolor.png" height="92px" width="1280px"/> 
		</div>
		<div id="left" class="panel panel-info"><%@ include file="shopNavigation.jsp"%></div>
		<div id="center" class="panel panel-info">
		 	<div class="panel-heading">
      			<h3 class="panel-title">发布需求</h3>
  			</div>
			<form id="orderForm" name="orderForm" method="get">
					<table width="100%" class="table table-striped">
						<tr>
							<td>送达地址</td>
							<td>
							  <select name="city_select" id="city_select"onchange="cityChange()" >
							    <c:forEach items="${cityList }" var="cityList">
								  <option value="${cityList.city_id },${cityList.city_name}" >${cityList.city_name}</option>
							    </c:forEach>
					 		  </select>
					          <select name="area_select" id="area_select"  onchange="areaChange()">
								<c:forEach items="${areaList }" var="area">
								  <option value="${area.area_id },${area.area_name }">${area.area_name }</option>
							    </c:forEach>
							</select>
					          <select name="address_select" id="address_select">
								<c:forEach items="${addressList }" var="address">
								  <option value="${address.address_id },${address.address_name }">${address.address_name }</option>
							    </c:forEach>
							 </select>
							<input type="text" id="context_address"
								name="context_address" /></td>
						</tr>
						<tr>
							<td>收件电话</td>
							<td><input type="text" id="context_phone"
								name="context_phone" /></td>
						</tr>
						<tr>
							<td>餐品原价</td>
							<td><input type="text" id="context_price"
								name="context_price" /></td>
						</tr>
						<tr>
							<td>实收金额</td>
							<td><input type="text" id="context_amountofmoney"
								name="context_amountofmoney" /></td>
						</tr>
						<tr>
							<td>菜品信息</td>
							<td><input type="text" id="context_infomation"
								name="context_infomation" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" class="btn btn-primary btn-lg" id="context_submit"
								name="context_submit" onclick="submitOrder()" value="提交订单"  margin-right="10px" /></td>
						</tr>
					</table>
				</form>
		</div>
		<div id="bottom" class="panel panel-info">6</div>
	</div>
</body>
</html>
