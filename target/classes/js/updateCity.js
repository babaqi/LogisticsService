$(document).ready(function() {
	$("input[id^='submitCityButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(17, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var cityId = $("#city_id_" + num).val();
		var cityName = $("#city_name_" + num).val();
		cityMap = {
			cityIdk : cityId,
			cityNamek : cityName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/city/WebUpdateCity",
			data : cityMap,
			success : function(data) {
				alert("保存成功!");
				location.reload(true);
			},
			error : function(data) {
				alert("保存失败!");
			}
		});

	});
	
	$("input[id^='deleteCityButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(17, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var cityId = $("#city_id_" + num).val();
		var cityName = $("#city_name_" + num).val();
		cityMap = {
			cityIdk : cityId,
			cityNamek : cityName
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/city/deleteCity",
			data : cityMap,
			success : function(data) {
				alert("删除成功!");
				location.reload(true);
			},
			error : function(data) {
				alert("删除失败!");
			}
		});

	});
	
	
});
function submitCity() {
	var cityName = document.getElementById('city_name_add').value;
	citysubmitmap = {
		cityNameK : cityName,
	}

	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/city/WebInsertCity",
		data : citysubmitmap,
		success : function(data) {
			alert("保存成功!");
			location.reload(true);
		},
		error : function(data) {
			alert("保存失败!");
		}
	});
}
