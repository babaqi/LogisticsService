$(document).ready(function() {
	$("input[id^='move_']").click(function() {
		
		if(window.confirm('你确定更换人员吗？')){
			var id = $(this).attr("id");
			var num = id.substr(5, id.length);
			var contextId = $("#context_id_" + num).val();
			var contextareaId = $("#context_areaid_" + num).val();
//			var userSelect = $("#user_select_" + num).val();
//			var userId=userSelect.split('_')[0];
//			var userName=userSelect.split('_')[1];
			contextMap = {
					contextIdk : contextId,
					contextareaIdK : contextareaId,
//					userIdk:userId,
//					userNamek:userName
				}
			jQuery.ajax({
				type : 'GET',
				url : "/LogisticsServer/context/webMoveOrder",
				data : contextMap,
				success : function(data) {
					alert("成功!");
					location.reload(true);
				},
				error : function(data) {
					alert("分配失败!");
				}
			});
         }else{
            return false;
        }
	});
});
