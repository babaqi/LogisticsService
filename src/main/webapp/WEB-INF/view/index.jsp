<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<form id="form1" name="form1" method="get"
		action="/LogisticsServer/shop/WebQueryShop">
		<table width="451" height="276" border="1" align="center"
			cellpadding="1" cellspacing="0" bordercolor="#666666">
			<tr>
				<td><table width="347" height="177" border="0" align="center"
						cellpadding="0" cellspacing="0" bordercolor="#333333">
						<tr>
							<td width="76">&nbsp;</td>
							<td width="168">&nbsp;</td>
							<td width="103">&nbsp;</td>
						</tr>
						<tr>
							<td>用户名</td>
							<td><label> <input type="text" name="user_name" />
							</label></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="text" name="user_pwd" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>select</td>
							<td><select name="backstage_select">
									<%-- <c:forEach items="${city }" var="city">
								<option value="city.city_id">${city.city_name }</option>
							</c:forEach> --%>
									<option value="1">后台1</option>
									<option value="2">管理后台</option>
							</select></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="2" align="center"><label> <input
									type="submit" name="Submit" value="提交" /> </label>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>