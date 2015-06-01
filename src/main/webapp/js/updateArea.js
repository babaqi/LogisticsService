$(document).ready(function() {
	$("#areaListTable").on("click", ".submitAreaButton", function() {
		//var id = $(this).attr("id");
		//var num = id.substr(17, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		//var areaId = $("#area_id_" + num).val();
		//var areaName = $("#area_name_" + num).val();
		var areaId = $(this).parents("tr").find(".area_id").val();
		var areaName = $(this).parents("tr").find(".area_name").val();
		areaMap = {
			areaIdk : areaId,
			areaNamek : areaName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/area/WebUpdateAddress",
			data : areaMap,
			success : function(data) {
				alert("保存成功!");
				location.reload(true);
			},
			error : function(data) {
				alert("保存失败!");
			}
		});

	});
	
	$("#areaListTable").on("click", ".deleteAreaButton", function() {
		//var id = $(this).attr("id");
		//var num = id.substr(17, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		//var areaId = $("#area_id_" + num).val();
		//var areaName = $("#area_name_" + num).val();
		var areaId = $(this).parents("tr").find(".area_id").val();
		var areaName = $(this).parents("tr").find(".area_name").val();
		areaMap = {
			areaIdk : areaId,
			areaNamek : areaName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/area/deleteArea",
			data : areaMap,
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




function submitArea() {
	var areaName = document.getElementById('area_name_add').value;
	var cityselect = document.getElementById('city_select');
	var city = cityselect.options[cityselect.selectedIndex].value;
	var arr = city.split(",");
	var cityid = arr[0];
	var cityname = arr[1];
	
	areasubmitmap = {
		areaNameK : areaName,
		cityidK:cityid
	}

	if(areaName=="")
	{
		alert("请输入地区名!");
		return;
	}
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/area/WebSubmitArea",
		data : areasubmitmap,
		success : function(data) {
			alert("保存成功!");
			location.reload(true);
		},
		error : function(data) {
			alert("保存失败!");
		}
	});
}

function serchArea() {
	var cityselect = document.getElementById('city_select2');
	var city = cityselect.options[cityselect.selectedIndex].value;
	var arr = city.split(",");
	var cityid = arr[0];
	var cityname = arr[1];
	citysubmitmap = {
			cityidK:cityid
	}
	
	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/area/WebQueryAreaById",
		data : citysubmitmap,
		dataType:"json",
		success : function(data) {
			var html = "<tr><td>地点Id</td><td>地点名</td><td>操作</td></tr>";
			if(data["areaList"]!=null)
			{
				for(var i = 0; i < data["areaList"].length; i++){
					html += "<tr>" +
							"<td><input type=\"hidden\" id=\"area_id_" + i + "\" class=\"area_id\" value=\" " +data["areaList"][i].area_id + "\" />"+data["areaList"][i].area_id+"</td>" +
							"<td><input value=\""+data["areaList"][i].area_name+"\" id=\"area_name_"+i+"\" class=\"area_name\" name=\"area_name\" /></td>" +
							"<td><input type=\"button\" id=\"submitAreaButton_"+i+"\" class=\"submitAreaButton\" name=\"Submit\" value=\"提交\" /></td>" +
							"<td><input type=\"button\" id=\"deleteAreaButton_"+i+"\" class=\"deleteAreaButton\" name=\"Delete\" value=\"删除\" /></td>" +
							"</tr>";
				}
				$("#areaListTable").html(html);
			}else
			{
				html += "<tr>" +
				"<td></td>" +
				"<td></td>" +
				"<td></td>" +
				"</tr>";
				$("#areaListTable").html(html);
				alert("无数据!");
			}
			
		},
		error : function(data) {
			alert(data);
			alert("刷新失败!");
		}
	});
}

