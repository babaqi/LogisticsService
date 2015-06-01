<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'navigation.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content="1080">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet">
<script src="<%=path%>/js/jquery-2.1.1.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>

</head>

<body>
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a
			href="/LogisticsServer/context/submitOrder">发布</a>
		</li>
		<li class="active"><a
			href="/LogisticsServer/context/webQueryInfo?state=0&page=0">等待</a>
		</li>
		<li class="active"><a
			href="/LogisticsServer/context/webQueryInfo?state=1&page=0">已接</a>
		</li>
		<li class="active"><a
			href="/LogisticsServer/context/webQueryInfo?state=2&page=0">配送</a>
		</li>
		<li class="active"><a
			href="/LogisticsServer/context/webQueryInfo?state=3&page=0">历史</a>
		</li >
		<li class="active"><a href="/LogisticsServer/user/index">退出</a>
		</li>
	</ul>
</body>
</html>
