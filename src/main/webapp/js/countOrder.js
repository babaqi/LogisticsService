function SerachOrder() {
	
	var areaid = document.getElementById('areaID').value;
	var yearSelect = document.getElementById('year');
	var year = yearSelect.options[yearSelect.selectedIndex].value;
	var monthSelect = document.getElementById('month');
	var month = monthSelect.options[monthSelect.selectedIndex].value;
	 var daySelect = document.getElementById('day');
	 var day = daySelect.options[daySelect.selectedIndex].value;
	 
	 
	 var data = year+"-"+month+"-"+day;
	 
	 
	countMap = {
			areaidK : areaid,
			dataK:data
	}
	
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/count/webSerachCountOrder",
		data : countMap,
		dataType:"json",
		success : function(data) {
			
			
			if(data["countUserDtoList"]!=null)
			{
				var html ="<tr>"+
				  "<td width=\"170\">配送</td>"+
				  "<td>(单数)送达</td>"+
				  "<td>(单数)问题</td>"+
				  "<td>(单数)收款</td>"+
				  "</tr>";
				
				for(var i = 0; i < data["countUserDtoList"].length; i++){
					html += "<tr>"+
							"<td width=\"170\">"+data["countUserDtoList"][i].userName+"</td>"+
							"<td>"+data["countUserDtoList"][i].userArriveOrder+"</td>"+
							"<td>"+data["countUserDtoList"][i].userProblemOrder+"</td>"+
							"<td>"+data["countUserDtoList"][i].userPrice+"</td>"+
							"</tr>";
				}
				$("#UserCount").html(html);
			}
			
			if(data["countShopDtoList"]!=null)
			{
				var html ="<tr>"+
				"<td width=\"200\">配送</td>"+
				"<td>(单数)送达</td>"+
				"<td>(单数)问题</td>"+
				"<td>(单数)收款</td>"+
				"</tr>";
				for(var i = 1; i < data["countShopDtoList"].length; i++){
					html += "<tr>"+
					"<td width=\"200\">"+data["countShopDtoList"][i].shopName+"</td>"+
					"<td>"+data["countShopDtoList"][i].shopArriveOrder+"</td>"+
					"<td>"+data["countShopDtoList"][i].shopProblemOrder+"</td>"+
					"<td>"+data["countShopDtoList"][i].shopPrice+"</td>"+
					"</tr>";
				}
				$("#ShopCount").html(html);
			}
			
			if(data["contextList"]!=null)
			{
				var html ="<tr><td>订单编号</td><td>下单时间</td><td>金额</td><td>商家</td><td>送达地址</td><td>配送员</td></tr>";
				for(var i = 0; i < data["contextList"].length; i++){
					html += "<tr>"+
							"<td>"+data["contextList"][i].context_id+"</td>"+
							"<td>"+data["contextList"][i].context_timer+"</td>"+
							"<td>"+data["contextList"][i].context_price+"</td>"+
							"<td>"+data["contextList"][i].context_shop_name+"</td>"+
							"<td>"+data["contextList"][i].context_address+"</td>"+
							"<td>"+data["contextList"][i].context_user_name+"</td>"+
							"</tr>";
					
				}
				$("#ContextCount").html(html);
			}else
			{
				alert("无数据!");
			}
			
		},
		error : function(data) {
			alert(data);
			alert("刷新失败!");
		}
	});
	
}