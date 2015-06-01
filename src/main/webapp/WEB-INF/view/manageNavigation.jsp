<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet">
<script src="<%=path%>/js/jquery-2.1.1.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>

<style>
	.menu { padding:0; margin:0; list-style-type:none;}
	.menu li { background:#FFD1A4; margin-right:1px; float:left; color:#fff; }
	.menu li a { display:block; width:80px; text-align:center; height:32px; line-height:32px; color:#fff; font-size:13px; text-decoration:none;}
	
	.cur{  color: #fff;text-decoration: none; background-color: #337ab7;  outline: 0;}
</style>

<ul class="nav nav-pills nav-stacked" id="menu">
	<li ><a
			href="/LogisticsServer/context/gonggao">首页</a>
		</li>
	<li ><a
			href="/LogisticsServer/city/WebQueryCity">城市管理</a>
		</li>
		<li ><a
			href="/LogisticsServer/area/WebQueryArea">区域管理</a>
		</li>
		<li ><a
			href="/LogisticsServer/address/WebQueryAddress">地址管理</a>
		</li>
		<li ><a
			href="/LogisticsServer/user/WebQueryUser">用户管理</a>
		</li>
		<li ><a
			href="/LogisticsServer/shop/WebQueryAllShop">商家管理</a>
		</li>
		<li ><a
			href="/LogisticsServer/context/webMoveQueryOrder?state=0&page=-1&pageType=">订单调度</a>
		</li>
		 <li ><a
			href="/LogisticsServer/count/webCountOrder">订单统计</a>
		</li > 
		<li ><a href="/LogisticsServer/user/index">退出</a>
		</li>
	</ul>


<script type="text/javascript">
  var urlstr = location.href;
  //alert(urlstr);
  var urlstatus=false;
  $("#menu a").each(function () {
    if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
      $(this).addClass('cur'); urlstatus = true;
    } else {
      $(this).removeClass('cur');
    }
  });
  if (!urlstatus) {$("#menu a").eq(0).addClass('cur'); }
</script>


	