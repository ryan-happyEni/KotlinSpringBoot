
var totalSum=0;
var selectedOrderKey=null;
var selectedOrderBy=null;
function loadList(){
	var param = {};

    var topic = $("#topic").val();
    if(topic==""){
	    clearInterval(intervalObj);
        $("#topic").focus();
        alert("Input topic.");
        return;
    }
    $("#btn_read").hide();
    $("#btn_cancel").show();
    $("#topic").prop("readonly", true);
	var request_type = "POST";
	var content_type = null;
	var return_data_type = 'json';
	var call_url = contextPath+"/rest/kafka/read/"+topic;

	sendRequest(request_type, call_url, param, content_type, return_data_type, function(data){
	    var log = $("#log");
	    var orgHtml = log.html();
	    console.log(data)
	    if(data!=null && data.length>0){
	        $.each(data, function(index, value){
	            orgHtml +="<br>"+JSON.stringify(value);
	        });

	        log.html(orgHtml);
	    }
	});
}

function doSend(){
	if(checkValidation()){
		var msg = "Do you send it?";

		if(confirm(msg)){
            var topic = $("#topic").val();
	        var param = getFormData($('#frm'));
	    	param = JSON.stringify(param);

	    	var request_type = "POST";
	    	var content_type = "application/json; charset=UTF-8";
			var return_data_type = 'json';
	        var call_url = contextPath+"/rest/kafka/send/"+topic;

			sendRequest(request_type, call_url, param, content_type, return_data_type, function(data){
				console.log(data)
			});
		}
	}
}

function checkValidation(){
	for(var i=0; i<requiredEl.length; i++){
		if(!isnull(requiredEl[i].id)){
			return false;
		}
	}

	return true;
}


var intervalObj = null;
function initBtn(){
	$("#btn_send").click(function(){doSend();});
	$("#btn_read").click(function(){
	    intervalObj = setInterval(function(){loadList();}, 1000*1)
	});
	$("#btn_cancel").click(function(){
	    $("#btn_read").show();
	    $("#btn_cancel").hide();
        $("#topic").prop("readonly", false);
	    clearInterval(intervalObj);
	});
	$("#btn_cancel").hide();
}

var requiredEl = [];
function initElement(){
	requiredEl = initBlurNullCheck("frm");
}

$(document).ready(function() {
	var menu_active_idx=2;
	activeMenu(menu_active_idx);

	initElement();
	initBtn(); 
});