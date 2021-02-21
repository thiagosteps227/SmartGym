let rootURL = "http://localhost:8080/SmartGym/rest/classtable";

let findAll = function() {
		//console.log('findAll');
	$.ajax({type: 'GET', url: rootURL, datatype: "json", success: renderList});
	
};

function renderList(classtable) {
	$.each(classtable, function(index, classtable){
		$('#classtable').append('<tr><td>'+classtable.className+'</td><td>'+classtable.personLimit+'</td><td>'+classtable.pricePerClass+'</td><td>'+classtable.priceTwelveWeeks+'</td></tr>' );
	});	
}

$(document).ready(function(){
	findAll();
});


