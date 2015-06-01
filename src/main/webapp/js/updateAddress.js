$(document).ready(function() {
	$("input[id^='submitaddressButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(20, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var addressId = $("#address_id_" + num).val();
		var addressName = $("#address_name_" + num).val();
		addressMap = {
			addressIdk : addressId,
			addressNamek : addressName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/address/WebUpdateAddress",
			data : addressMap,
			success : function(data) {
				alert("保存成功!");
				location.reload(true);
			},
			error : function(data) {
				alert("保存失败!");
			}
		});

	});
	
	$("input[id^='deleteaddressButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(20, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var addressId = $("#address_id_" + num).val();
		var addressName = $("#address_name_" + num).val();
		addressMap = {
			addressIdk : addressId,
			addressNamek : addressName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/address/WebDeleteAddress",
			data : addressMap,
			success : function(data) {
				alert("保存成功!");
				location.reload(true);
			},
			error : function(data) {
				alert("保存失败!");
			}
		});

	});
});

function submitaddress() {
	var addressName = document.getElementById('address_name_add').value;
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
	 
	addresssubmitmap = {
		addressNameK : addressName,
		areaidK:areaid,
		citynameK:cityname,
		areanameK:areaname
	}
	
	if(addressName=="")
	{
		alert("请输入地址名!");
		return;
	}

	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/address/WebSubmitAddress",
		data : addresssubmitmap,
		success : function(data) {
			alert("保存成功!");
			location.reload(true);
		},
		error : function(data) {
			alert("保存失败!");
		}
	});
}
function queryAddress() {
	var areaselect = document.getElementById('area_select2');
	var areaid = areaselect.options[areaselect.selectedIndex].value;
	addresssubmitmap = {
			areaidK:areaid
	}
	
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/address/queryAddress",
		data : addresssubmitmap,
		dataType:"json",
		success : function(data) {
			var html = "<tr><td>地址Id</td><td>地址名</td><td>操作</td></tr>";
			if(data["addressList"]!=null)
			{
				for(var i = 0; i < data["addressList"].length; i++){
					html += "<tr>" +
							"<td><input type=\"hidden\" id=\"address_id_" + i + "\" value=\" " +data["addressList"][i].address_id + "\" />"+data["addressList"][i].address_id+"</td>" +
							"<td><input value=\""+data["addressList"][i].address_name+"\" id=\"address_name_"+i+"\" name=\"address_name\" /></td>" +
							"<td><input type=\"button\" id=\"submitaddressButton_"+i+"\" name=\"Submit\" value=\"提交\" /></td>" +
							"</tr>";
				}
				$("#addressListTable").html(html);
			}else
			{
				html += "<tr>" +
				"<td></td>" +
				"<td></td>" +
				"<td></td>" +
				"</tr>";
				$("#addressListTable").html(html);
				alert("无数据!");
			}
			
		},
		error : function(data) {
			alert("刷新失败!");
		}
	});
}
function cityChange() {
	var addressName = document.getElementById('address_name_add').value;
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
function cityChange2() {
	var cityselect2 = document.getElementById('city_select2');
	var cityid = cityselect2.options[cityselect2.selectedIndex].value;
	
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
				html += "<option value=" + data["areaList"][i].area_id + ">" + data["areaList"][i].area_name + "</option>";
			}
			$("#area_select2").html(html);
		},
		error : function(data) {
			alert("刷新失败!");
		}
	});
}

