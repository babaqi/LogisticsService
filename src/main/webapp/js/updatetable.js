var contextMap;
$(document).ready(function() {
	$("input[id^='submitButton_']").click(function() {
		alert(1);
		var id = $(this).attr("id");
		var num = id.substr(13, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var contextId = $("#context_id_" + num).val();
		var contextShopId = $("#context_shop_id_" + num).val();
		var contextAddress = $("#context_address_" + num).val();
		var contextPhone = $("#context_phone_" + num).val();
		var contextPrice = $("#context_price_" + num).val();
		var contextAmountofmoney = $("#context_amountofmoney_" + num).val();
		var contextInfomation = $("#context_infomation_" + num).val();
		var contextType = $("#context_type_" + num).val();
		var contextUserid = $("#context_userid_" + num).val();
		var contextCityid = $("#context_cityid_" + num).val();
		var contextShopName = $("#context_shop_name_" + num).val();
		var contextShopAddress = $("#context_shop_address_" + num).val();
		var contextUserName = $("#context_user_name_" + num).val();
		var contextTimer = $("#context_timer_" + num).val();
		contextMap = {
			contextIdk : contextId,
			contextShopIdk : contextShopId,
			contextAddressk : contextAddress,
			contextPhonek : contextPhone,
			contextPricek : contextPrice,
			contextAmountofmoneyk : contextAmountofmoney,
			contextInfomationk : contextInfomation,
			contextUseridk: contextUserid,
			contextCityidk : contextCityid,
			contextShopNamek : contextShopName,
			contextShopAddressk : contextShopAddress,
			contextUserNamek : contextUserName,
			contextTimerk : contextTimer

		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/context/webSubmit",
			data : contextMap,
			success : function(data) {
				alert("成功!");
			},
			error : function(data) {
				alert("分配失败!");
			}
		});

	});
});