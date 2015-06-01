<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="refresh" content="1080">
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
<body >
	<div  id="page_container">
		<div id="banner" class="title">2</div>
		<div id="left" class="title"><%@ include file="manageNavigation.jsp"%></div>
		<div id="center" class="title">无
		</div>
		<div id="bottom" class="title">6</div>
	</div>
</body>
</html>
