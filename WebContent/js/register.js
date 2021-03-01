var customerURL = "http://localhost:8080/SmartGym/rest/customer/createCustomer";

var addCustomer = function(){
	$.ajax({type: 'POST', 
		contentType: 'application/json',
		url: customerURL, 
		datatype: "json", 
		data: formToJSON(),
		success: function(){
			alert("Customer registered");
		}});
};

var formToJSON = function (){
	return JSON.stringify({
			"userName" : $('#userName').val(),
			"firstName" : $('#firstName').val(),
			"lastName" : $('#lastName').val(),
			"password" : $('#password').val(),
			"phoneNumber" : $('#phoneNumber').val(),
			"gender" : $('#gender').val(),
			"dateOfBirth" : $('#dateOfBirth').val(),
			"email" : $('#email').val()
	});
}

$(document).ready(function(){
	
	$('#registerButton').click(function() {
		addCustomer();
	});
});
