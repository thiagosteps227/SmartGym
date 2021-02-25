let rootURL = "http://localhost:8080/SmartGym/rest/classtable";
let customerURL = "http://localhost:8080/SmartGym/rest/customer/createCustomer";

let findAll = function() {
		//console.log('findAll');
	$.ajax({type: 'GET', url: rootURL, datatype: "json", success: renderList});
};

$("#registerButton").click(function(){
	$.ajax({type: 'POST', data: customer, url: customerURL, datatype: "json", success: createCustomer});
});

function createCustomer(customer){
	let customer = JSON.stringify({
			"userName" : $("#userName").val(),
			"firstName" : $("#firstName").val(),
			"lastName" : $("#lastName").val(),
			"password" : $("#password").val(),
			"phonenumber" : $("#phonenumber").val(),
			"gender" : $("#gender").val(),
			"dateOfBirth" : $("#dateOfBirth").val(),
			"email" : $("#email").val()
	});
}

function renderList(classtable) {
	$.each(classtable, function(index, classtable){
		$('#classtable').append('<tr><td>'+classtable.className+'</td><td>'+classtable.personLimit+'</td><td>'+classtable.pricePerClass+'</td><td>'+classtable.priceTwelveWeeks+'</td></tr>' );
	});	
}

$(document).ready(function(){
	findAll();
	createCustomer();
	$("#registerButton").click(function(){
		$.ajax({type: 'POST', data: customer, url: customerURL, datatype: "json", success: createCustomer});
	});
});


