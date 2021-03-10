var rootURL = "http://localhost:8080/SmartGym/rest/timetable";

var findAll = function() {
		//console.log('findAll');
	$.ajax({type: 'GET', url: rootURL, datatype: "json", success: renderList});
};

function renderList(timetable) {
	$.each(timetable, function(index, timetable){
		$('#timetable').append('<tr><td>'+timetable.classCode+'</td><td>'+timetable.className+'</td><td>'+
				timetable.instructor+'</td><td>'+timetable.weekDay+'</td><td>'+timetable.weekDay+'</td></tr>' );
	});	
}

$(document).ready(function(){
	findAll();
	
	
});
