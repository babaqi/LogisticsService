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
	src="<%=request.getContextPath()%>/js/submitUser.js"></script>
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
	height: 459px;
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
	<div id="page_container"  class="panel panel-info">
		<div id="banner"  class="panel panel-info">2</div>
		<div id="left"  class="panel panel-info"><%@ include file="manageNavigation.jsp"%></div>
		<div id="center"  class="panel panel-info">
		<div class="panel-heading">
      			<h3 class="panel-title">添加用户</h3>
  			</div>
			<form id="userForm" name="userForm" method="get">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
								<tr>
									<td>用户名</td>
									<td><label> <input type="text" id="user_name" />
									</label>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>密码</td>
									<td><input type="text" id="user_pwd" />
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>联系电话</td>
									<td><input type="text" id="user_tel" />
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>用户配送城市</td>
									<td><select name="city_select" id="city_select">
											<c:forEach items="${cityList }" var="cityList">
												<option value="${cityList.city_id}">${cityList.city_name}</option>
											</c:forEach>

									</select>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>用户配送区域</td>
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
									<td colspan="2" align="center"><label> <input  class="button white"
											type="Button" name="Submit" onclick="submitUser()" value="提交" />
									</label></td>
								</tr>
							</table>
			</form>
						<div class="panel-heading">
      			<h3 class="panel-title">用户列表</h3>
  			</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table table-striped">
						<thead>
							<tr>
							<th>用户Id</th>
							<th>用户名</th>
							<th>用户电话</th>
							<th>用户配送城市</th>
							<th>用户配送地区</th>
							<th>操作</th>
							</tr>
						</thead>
						<tr >
							<c:forEach items="${showUserDtoList }" var="showUserDtoList"  varStatus="status">
								<tr>
								<td><input type="hidden" id="user_id_${status.count}" value="${showUserDtoList.user_id}" />${showUserDtoList.user_id }</td>
								<td>${showUserDtoList.user_name}</td>
								<td><input id="user_tel_${status.count}" value="${showUserDtoList.user_tel}" /></td>
								<td>${showUserDtoList.user_city_name}</td>
								<td>${showUserDtoList.user_area_name}</td> 
								<td>
								<%-- <select name="user_area_name_${status.count}">
											<c:forEach items="${areaList }" var="areaList">
												<option value="${areaList.area_id }">${areaList.area_name}</option>
											</c:forEach>
								</select> --%>
									<input type="Button" id="updateUserButton_${status.count}" value="删除" />
								</td>
						<%-- <td><input id="user_area_name_${status.count}" value="${showUserDtoList.user_area_name}" />${showUserDtoList.user_area_name}</td>  --%>
								</tr>
							</c:forEach>
						</tr>
					</table>
		</div>
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
