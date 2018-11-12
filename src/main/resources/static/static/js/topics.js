
var totalSum=0;
var selectedOrderKey=null;
var selectedOrderBy=null;
function loadList(){
	var param = {};

	var request_type = "POST";
	var content_type = null;
	var return_data_type = 'json';
	var call_url = contextPath+"/rest/kafka/topics";

	sendRequest(request_type, call_url, param, content_type, return_data_type, function(data){
	    var topics = $("#topics");
	    topics.empty();
	    if(data!=null && data.length>0){
	        $.each(data, function(index, value){
	            var li = document.createElement("li");
	            li.innerHTML = value;
	            topics.append(li);
	        });
	    }
	});
}

var intervalObj = null;
function initBtn(){
}

var requiredEl = [];
function initElement(){
	requiredEl = initBlurNullCheck("frm");
}

$(document).ready(function() {
	var menu_active_idx=1;
	activeMenu(menu_active_idx);

	initElement();
	initBtn();
	loadList();
});