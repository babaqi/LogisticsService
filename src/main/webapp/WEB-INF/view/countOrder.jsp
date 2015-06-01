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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.1.js"></script>
<!--  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/countOrder.js"></script>
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
	height: auto;
	float: left;
}

#center {
	width: 89%;
	height: auto;
	float: right;
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
		<div id="left" class="panel panel-info"><%@ include
				file="manageNavigation.jsp"%></div>
		<div id="center" class="panel panel-info">
			<div class="panel-heading">
				<h3 id="rightTitle" class="panel-title">订单统计</h3>
			</div>
			<div id="rightContent" class="panel panel-info">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 id="rightTitle" class="panel-title">订单筛选</h3>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="table table-striped">
						<tr>
							<td width="170">所属区域</td>
							<td><select name="area" class="textbox" id="areaID">
							<!-- <option value="-1" selected>权限内所有</option>
									<option value="1">测试区域</option>
									<option value="2">悟空送跑腿</option>
									<option value="3">齐鲁师范</option>
									<option value="4">窑头商圈</option>
									<option value="5">洪家楼商圈</option>
									<option value="12">山师山艺</option> -->
									<c:forEach items="${areaList }" var="area">
								<option value="${area.area_id }">${area.area_name }</option>
							</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>查询日期</td>
							<td>
							<select name="year"  id="year">
									<option value="2015" selected>2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
									<option value="2021">2021</option> 
							</select>
							
							<select name="month"  id="month">
									<option value="01" selected>1月</option>
									<option value="02">2月</option>
									<option value="03">3月</option>
									<option value="04">4月</option>
									<option value="05">5月</option>
									<option value="06">6月</option>
									<option value="07">7月</option> 
									<option value="08">8月</option> 
									<option value="09">9月</option> 
									<option value="10">10月</option> 
									<option value="11">11月</option> 
									<option value="12">12月</option> 
							</select>
							
							<select name="day" id="day">
								<option value="01" selected>1号</option>
									<option value="02">2号</option>
									<option value="03">3号</option>
									<option value="04">4号</option>
									<option value="05">5号</option>
									<option value="06">6号</option>
									<option value="07">7号</option> 
									<option value="08">8号</option> 
									<option value="09">9号</option> 
								<c:forEach begin="10" end="31" varStatus="status">
									<option value=${status.index } >${status.index }号</option>
								</c:forEach>
							</select>
							
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input id="searchBtn"
								type="button" onclick="SerachOrder()" value="查询">
							</td>
						</tr>
					</table>
				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-striped">
					<tr>
						<td valign="top">
							<div class="panel panel-info" style="margin-top:20px;">
								<div class="panel-heading">
									<h3 id="rightTitle" class="panel-title">配送员收款（全部页）</h3>
								</div>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" id="UserCount" class="table table-striped">
										<tr>
											<td width="170">配送</td>
											<td>(单数)送达</td>
											<td>(单数)问题</td>
											<td>(单数)收款</td>
										</tr>
								</table>
							</div>
						</td>
						<td valign="top">
							<div class="panel panel-info" style="margin-top:20px;margin-left:20px;">
								<div class="panel-heading">
									<h3 id="rightTitle" class="panel-title">商家营业额（全部页）</h3>
								</div>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" id="ShopCount" class="table table-striped">
										<tr>
											<td width="200">商家</td>
											<td>(单数)送达</td>
											<td>(单数)问题</td>
											<td>(单数)收款</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<div class="panel panel-info" style="margin-top:20px;">
					<div class="panel-heading">
						<h3 id="rightTitle" class="panel-title">详细订单列表</h3>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" id="ContextCount" class="table table-striped">
							
							
							<tr>
								<td>订单编号</td>
								<td>下单时间</td>
								<td>金额</td>
								<td>商家</td>
								<td>送达地址</td>
								<td>配送员</td>
							</tr>
							<c:forEach items="${contextList}" var="contextList" >
							<tr>
								<td>${contextList.context_id}</td>
								<td>${contextList.context_timer}</td>
								<td>${contextList.context_price}</td>
								<td>${contextList.context_shop_name}</td>
								<td>${contextList.context_address}</td>
								<td>${contextList.context_user_name}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		
		
		
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
