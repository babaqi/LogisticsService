function submitOrder() {

	// var recode = document.getElementById('recode').value;
	// if (recode == 0) {
	// alert("订单发布成功");
	// } else {
	// alert("订单发布失败");
	// }
	var addressselect = document.getElementById('address_select');
	 var address = addressselect.options[addressselect.selectedIndex].value;
	 var arr = address.split(",");
	 var addressid = arr[0]; 
	 var addressname = arr[1]; 
	 var areaselect = document.getElementById('area_select');
	 var area = areaselect.options[areaselect.selectedIndex].value;
	 var arr2 = area.split(",");
	 var areaid = arr2[0];
	 var areaname = arr2[1];
	var contextaddress = addressname+document.getElementById('context_address').value;
	
	var contextphone = document.getElementById('context_phone').value;
	var contextprice = document.getElementById('context_price').value;
	var contextamountofmoney = document.getElementById('context_amountofmoney').value;
	var contextinfomation = document.getElementById('context_infomation').value;
	 
	 cityidmap = {
			 addressidk : areaid,
			 contextaddressk :contextaddress,
			 contextphonek :contextphone,
			 contextpricek :contextprice,
			 contextamountofmoneyk :contextamountofmoney,
			 contextinfomationk :contextinfomation
			}
	if (contextaddress == null || contextaddress == undefined
			|| contextaddress == '') {
		alert("地址不能为空！");
		return false;
	} else if (contextphone == null || contextphone == undefined
			|| contextphone == '') {
		alert("电话不能为空！");
		return false;
	} else if (contextprice == null || contextprice == undefined
			|| contextprice == '') {
		alert("价格不能为空！");
		return false;
	} else if (contextamountofmoney == null
			|| contextamountofmoney == undefined || contextamountofmoney == '') {
		alert("应付价格不能为空！");
		return false;
	} else if (contextinfomation == null || contextinfomation == undefined
			|| contextinfomation == '') {
		alert("信息不能为空！");
		return false;
	} else {
		$.ajax({
//			cache : true,
//			type : "Get",
//			url : "/LogisticsServer/context/webSubmit",
//			data : $('#orderForm').serialize(),// 你的formid
//			async : false,
//			error : function(request) {
//				alert("订单发布失败");
//			},
//			success : function(data) {
//				alert("订单发布成功");
//			}
			
			type : 'GET',
			url : "/LogisticsServer/context/webSubmit",
			data : cityidmap,
			success : function(data) {
				alert("订单发布成功");
			},
			error : function(data) {
				alert("刷新失败!");
			}
		});
	}

}

function cityChange() {
	var cityselect = document.getElementById('city_select');
	 var city = cityselect.options[cityselect.selectedIndex].value;
	 var areaselect = document.getElementById('area_select');
	 var area = areaselect.options[areaselect.selectedIndex].value;
	 
	 var arr = city.split(",");
	 var cityid = arr[0]; 
	 var cityname = arr[1]; 
	 var arr2 = area.split(",");
	 var areaid = arr2[0];
	 var areaname = arr2[1];
	 
	 cityidmap = {
			 cityidK : cityid,
			}
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/address/abcd",
		data : cityidmap,
		dataType:"json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data["areaList"].length; i++){
				html += "<option value=" + data["areaList"][i].area_id +","+data["areaList"][i].area_name+ ">" + data["areaList"][i].area_name + "</option>";
			}
			$("#area_select").html(html);
		},
		error : function(data) {
			alert("刷新失败!");
		}
	});
}


function areaChange() {
	 var areaselect = document.getElementById('area_select');
	 var area = areaselect.options[areaselect.selectedIndex].value;
	 
	 var arr2 = area.split(",");
	 var areaid = arr2[0];
	 var areaname = arr2[1];
	 
	 cityidmap = {
			 areaidK : areaid,
			}
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/address/WebQueryAddressByareaId",
		data : cityidmap,
		dataType:"json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data["addressList"].length; i++){
				html += "<option value=" + data["addressList"][i].address_id +","+data["addressList"][i].address_name+ ">" + data["addressList"][i].address_name + "</option>";
			}
			$("#address_select").html(html);
		},
		error : function(data) {
			alert("刷新失败!");
		}
	});
}