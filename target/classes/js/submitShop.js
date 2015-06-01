$(document).ready(function() {
	$("input[id^='deleteShopButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(17, id.length);
		var shopId = $("#shop_id_" + num).val();
		cityMap = {
				shopIdK : shopId
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/shop/webdeleteShop",
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


function submitShop() {
	var shopName = document.getElementById('shop_name').value;
	var shopPwd = document.getElementById('shop_pwd').value;
	var shopTel = document.getElementById('shop_tel').value;
	var shopAddress = document.getElementById('shop_address').value;
	var shopCityId = document.getElementById('city_select').value;
	var shopAreaId = document.getElementById('area_select').value;
	
	shopsubmitmap = {
			shopNameK : shopName,
			shopPwdK : shopPwd,
			shopTelK : shopTel,
			shopAddressK : shopAddress,
			shopCityIdK : shopCityId,
			shopAreaIdK:shopAreaId
	}

	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/shop/WebInsertShop",
		data : shopsubmitmap,
		success : function(data) {
			alert("保存成功!");
			location.reload(true);
		},
		error : function(data) {
			alert("保存失败!");
		}
	});
}