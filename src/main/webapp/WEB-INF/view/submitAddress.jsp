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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/updateAddress.js"></script>
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
<body>
	<div id="page_container" class="panel panel-info">
		<div id="banner" class="panel panel-info">
		<img src="../images/bgcolor.png" height="92px" width="1280px"/> 
		</div>
		<div id="left" class="panel panel-info"><%@ include file="manageNavigation.jsp"%></div>
		<div id="center" class="panel panel-info">
			<div class="panel-heading">
      			<h3 class="panel-title">添加地址</h3>
  			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped" >
				<tr>
					<td>选择所属城市</td>
					<td colspan="2"><select name="city_select" id="city_select"
						onchange="cityChange()">
							<c:forEach items="${cityList }" var="cityList">
								<option value="${cityList.city_id },${cityList.city_name}" >${cityList.city_name
									}</option>
							</c:forEach>
					</select>
					</td>
					<td>选择所属地区</td>
					<td colspan="2"><select name="area_select" id="area_select">
							<c:forEach items="${areaList }" var="area">
								<option value="${area.area_id },${area.area_name }">${area.area_name }</option>
							</c:forEach>
					</select>
					</td>
					<td>地址名</td>
					<td colspan="2"><input id="address_name_add" name="address_name_add" />
					</td>
				</tr>
				<tr>
					<td colspan="6"><input type="button" id="address_add_btn"
						name="address_add_btn" onclick="submitaddress()" value="添加" />
					</td>
				</tr>
			</table>
		<br/>
		
				<div class="panel-heading">
      				<h3 class="panel-title">选择筛选条件</h3>
  				</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped" >
				<tr>
					<td>选择所属城市</td>
					<td colspan="2"><select name="city_select2" id="city_select2"
						onchange="cityChange2()">
							<c:forEach items="${cityList }" var="cityList">
								<option value="${cityList.city_id }">${cityList.city_name}</option>
							</c:forEach>
					</select>
					</td>
					<td>选择所属地区</td>
					<td colspan="2"><select name="area_select2" id="area_select2">
							<c:forEach items="${areaList }" var="area">
								<option value="${area.area_id }">${area.area_name }</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="3"><input type="button" id="address_query_btn"
						name="address_query_btn" onclick="queryAddress()" value="查询" />
					</td>
				</tr>
			</table>
		<br/>	
				<div class="panel-heading">
      				<h3 class="panel-title">地址列表</h3>
  				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped"  id ="addressListTable">
						<tr>
							<td>地址Id</td>
							<td>地址名</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${addressList}" var="addressList"
								varStatus="status" >
							<tr >
								<td><input type="hidden" id="address_id_${status.count}"
									value="${addressList.address_id}" />${addressList.address_id}</td>
								<td><input value="${addressList.address_name}"
									id="address_name_${status.count}" name="address_name" />
								</td>
								<td><input type="button"
									id="submitaddressButton_${status.count}" name="Submit"
									value="提交" />
									<input type="button"
									id="deleteaddressButton_${status.count}" name="Delete"
									value="删除" />
								</td>
							</tr>
						</c:forEach>
					</table>
		</div>
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
