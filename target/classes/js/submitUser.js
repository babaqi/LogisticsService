$(document).ready(function() {
	$("input[id^='updateUserButton_']").click(function() {
		var id = $(this).attr("id");
		var num = id.substr(17, id.length);
		// tempArray[1]
		// $("#context_id_" + num).val();
		var username = $("#user_name_" + num).val();
		var usertel = $("#user_tel_" + num).val();
		var userId = $("#user_id_" + num).val();
		cityMap = {
				userIdK : userId
		}
		jQuery.ajax({
			type : 'GET',
			url : "/LogisticsServer/user/WebDeleteUser",
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

function submitUser() {
	var userName = document.getElementById('user_name').value;
	var userPwd = document.getElementById('user_pwd').value;
	var UserTel = document.getElementById('user_tel').value;
	var UserCityId = document.getElementById('city_select').value;
	var UserAreaId = document.getElementById('area_select').value;
	
	usersubmitmap = {
			userNameK : userName,
			userPwdK : userPwd,
			UserTelK : UserTel,
			UserCityIdK : UserCityId,
			UserAreaIdK : UserAreaId
			
	}

	jQuery.ajax({
		type : 'GET',
		url : "/LogisticsServer/user/webRegister",
		data : usersubmitmap,
		success : function(data) {
			alert("保存成功!");
			location.reload(true);
		},
		error : function(data) {
			alert("保存失败!");
		}
	});
}

