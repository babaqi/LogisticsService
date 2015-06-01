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
<script type="text/javascript"src="<%=request.getContextPath()%>/js/moveOrder.js"></script>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"> --%>
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
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
	height: 20%;
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
#center2 {
	width: 100%;
	height: 100%;
	float: center;
}

#bottom {
	clear: both;
	height: 50px;
}
.fenye02{ border: 0px; width:auto; margin:0 auto; height:auto; overflow:hidden; float:center; position:relative; font-size:12px; color:#5a5a5a; line-height:22px;}
.fenye02 .fy{ float:left; left:50%; position:relative;}
.fy li{ float:left; position:relative; left:-50%; margin-right:6px;}
.fy li a{ display:block; padding:0 10px; color:#5a5a5a; border:1px solid #ccc; height:22px; line-height:22px;}
.fy li .selected{ border:1px solid #4980d1; color:#fff; background:#4980d1;}
.fy li .jy{ width:50px; height:22px; border:1px solid #d2d2d2; margin:0 4px;}
.fy li .qdb{ width:42px; height:22px; border:1px solid #8aaada; background:#dceaff; color:#5a5a5a; cursor:pointer;}
.fy li .qdb:hover{ opacity:0.8;}

</style>
</head>
<body>
	<div id="page_container" class="panel panel-info">
		<div id="banner" class="panel panel-info">2</div>
		<div id="left" class="panel panel-info">
			<ul class="nav nav-pills nav-stacked" id="menu">
				<li ><a href="/LogisticsServer/context/gonggao">首页</a></li>
				<li ><a href="/LogisticsServer/city/WebQueryCity">城市管理</a></li>
				<li ><a href="/LogisticsServer/area/WebQueryArea">区域管理</a></li>
				<li ><a href="/LogisticsServer/address/WebQueryAddress">地址管理</a></li>
			    <li ><a href="/LogisticsServer/user/WebQueryUser">用户管理</a></li>
				<li ><a href="/LogisticsServer/shop/WebQueryAllShop">商家管理</a></li>
				<li class="active" ><a href="/LogisticsServer/context/webMoveQueryOrder?state=0&page=-1&pageType=">订单调度</a></li>
				<li ><a href="/LogisticsServer/count/webCountOrder">订单统计</a></li > 
				<li ><a href="/LogisticsServer/user/index">退出</a></li>
			</ul>
		</div>
		<div id="center" class="panel panel-info" >
		<form id="orderMoveForm" name="orderMoveForm" method="get" action="/LogisticsServer/context/webMoveQueryOrder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped" >
					<c:forEach items="${context}" var="context" varStatus="status">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
							<tr class="success">
								<td colspan="4" >${context.context_timer}</td>
							</tr>
							<tr>
								<td>商家名称</td>
								<td colspan="3">${context.context_shop_name}</td>
							</tr>
							<tr>
								<td>送达地址</td>
								<td colspan="3">${context.context_address}</td>
							</tr>
							<tr>
								<td>应收款项</td>
								<td>${context.context_amountofmoney}</td>
								<td>订单状态</td>
								<td>${context.context_type}</td>
							</tr>
							<tr>
								<td>接单人</td>
								<!-- bgcolor="#E3E3E3"  -->
								<td>${context.context_user_name}</td>
								<td>联系电话</td>
								<td>${context.context_phone}</td>
							</tr>
							<tr>
								<td colspan="4" align="center" >
								<input type="hidden" id="context_id_${status.count}" value="${context.context_id}"> 
								<select name="user_select" id="user_select_${status.count}">
												<c:forEach items="${user_select }" var="user_select">
													<option value="${user_select.user_id }_${user_select.user_name }">${user_select.user_name}</option>
												</c:forEach>
										</select>
								<input type="button" id="move_${status.count}" name="Move" value="更换人员" class="button"  /></td>
							</tr>
						</table>
						 <hr size="20" color="#0000FF" />
					</c:forEach>
				</table>
				 <div class="fenye02">
				    <ul class="fy">
				      <li><a href="/LogisticsServer/context/webMoveQueryOrder?state=0&page=-1&pageType=">首页</a></li>
				      <li><a href="/LogisticsServer/context/webMoveQueryOrder?state=0&page=${pagenum}&pageType=up">上一页</a></li>
				      <li>${pagenum+1}</li>
				      <li><a href="/LogisticsServer/context/webMoveQueryOrder?state=0&page=${pagenum}&pageType=down">下一页</a></li>
				    </ul>
  				</div>
  			</form>	
		</div>
		
		
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
