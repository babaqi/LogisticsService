$(document).ready(function() {
	$("input[id^='cancel_']").click(function() {
		
		if(window.confirm('你确定要取消交易吗？')){
			var id = $(this).attr("id");
			var num = id.substr(7, id.length);
			var contextId = $("#context_id_" + num).val();
			
			contextMap = {
					contextIdk : contextId,
				}
			jQuery.ajax({
				type : 'GET',
				url : "/LogisticsServer/context/webUpdateInfo",
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
