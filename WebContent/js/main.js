let rootURL = "http://localhost:8080/SmartGym/rest/classtable";

let findAll = function() {
		//console.log('findAll');
	$.ajax({type: 'GET', url: rootURL, datatype: "json", success: renderList});
	
};

function renderList(classtable) {
	$.each(classtable, function(index, classtable){
		$('#classtable').append('<li><a href="#" '+ classtable.classID+'">'+classtable.className+'</a>'+" Euro per Class:  "+classtable.pricePerClass+'</li>');
	});	
}

$(document).ready(function(){
	findAll();
});


