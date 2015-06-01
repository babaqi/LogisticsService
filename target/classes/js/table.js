$(document).ready(function(){
	$("^submitButton_").click(function(){
		alert(1);
		var id = $(this).attr("id");
		var tempArray = $.split(id, "_");
		tempArray[1]
		$("#context_id_" + tempArray[1]).val();
		var contextId = $("#context_id_1").val();
		$.ajax({
			data: {contextIdP : contextId}
		}
				);
	});
	
	var row = "<tr class='warning'><td><input value='' id='context_id_'" + 1 + " name='context_id'/></td></tr>";
	$("#tbody").append(row);
	$("#input").parent().remove();
});

function onxxxxClick(){
	
	var row = "<tr class='warning'><td><input value='' id='context_id_'" + 1 + " name='context_id'/></td></tr>";
	$("#tbody").append(row);
	
}

