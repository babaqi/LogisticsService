<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="1080">
<title>无标题文档</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/updatetable.js"></script>
<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet">
</head>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<body>
	<%-- <p>${name }</p>
	<p>${xingbie }</p>  --%>

	<table width="100%" height="100%" border="1">
		<tr>
			<td colspan="3">头</td>
		</tr>
		<tr>
			<td width="200" valign="top"><%@ include
					file="shopNavigation.jsp"%></td>
			<td colspan="2"><table class="table">
					<thead>
						<tr>
							<th>context_id</th>
							<th>context_shop_id</th>
							<th>context_address</th>
							<th>context_phone</th>
							<th>context_price</th>
							<th>context_amountofmoney</th>
							<th>context_infomation</th>
							<th>context_type</th>
							<th>context_userid</th>
							<th>context_cityid</th>
							<th>context_shop_name</th>
							<th>context_shop_address</th>
							<th>context_user_name</th>
							<th>context_timer</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${context}" var="context" varStatus="status">
							<tr class="warning">
								<td><input value="${context.context_id}"
									id="context_id_${status.count}" name="context_id" /></td>
								<td><input value="${context.context_shop_id}"
									id="context_shop_id_${status.count}" name="context_shop_id" />
								</td>
								<td><input value="${context.context_address}"
									id="context_address_${status.count}" name="context_address" />
								</td>
								<td><input value="${context.context_phone}"
									id="context_phone_${status.count}" name="context_phone" /></td>
								<td><input value="${context.context_price}"
									id="context_price_${status.count}" name="context_price" /></td>
								<td><input value="${context.context_amountofmoney}"
									id="context_amountofmoney_${status.count}"
									name="context_amountofmoney" /></td>
								<td><input value="${context.context_infomation}"
									id="context_infomation_${status.count}"
									name="context_infomation" /></td>
								<td><input value="${context.context_type}"
									id="context_id_${status.count}" name="context_type" /></td>
								<td><input value="${context.context_userid}"
									id="context_type_${status.count}" name="context_userid" /></td>
								<td><input value="${context.context_cityid}"
									id="context_cityid_${status.count}" name="context_cityid" /></td>
								<td><input value="${context.context_shop_name}"
									id="context_shop_name_${status.count}" name="context_shop_name" />
								</td>
								<td><input value="${context.context_shop_address}"
									id="context_shop_address_${status.count}"
									name="context_shop_address" /></td>
								<td><input value="${context.context_user_name}"
									id="context_user_name_${status.count}" name="context_user_name" />
								</td>
								<td><input value="${context.context_timer}"
									id="context_timer_${status.count}" name="context_timer" /></td>
								<td><input type="button" id="submitButton_${status.count}"
									name="Submit" value="提交" /></td>

							</tr>

						</c:forEach>

					</tbody>
				</table>
			</td>
		</tr>
	</table>








</body>
</html>