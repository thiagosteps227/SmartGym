var customerURL = "http://localhost:8080/SmartGym/rest/customer/createCustomer";


//method to add a customer when the button register is pressed
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
			"email" : $('#email').val(),
			"activity" : $("input[type='checkbox']:checked").val()
	});
}

//to check empty inputs
var validateForm = function() {

	  var formInvalid = false;
	  $('#registerForm input').each(function() {
	    if ($(this).val() === '') {
	      formInvalid = true;
	    }
	  });

	 // if (formInvalid)
	 //   alert('One or Two fields are empty. Please fill up all fields');
	  return formInvalid;
	}

$(document).ready(function(){
	$('#registerButton').click(function() {
		if(validateForm()=== true){
			alert('One or Two fields are empty. Please fill up all fields');
		} else {
			addCustomer();
		}
		
		
	});
	$('div[id="customerDiv"]').hide();
	
	//to make the navbar white when you are in the register page
	$('.topnav a').removeClass('active');
    $('#registerPage').addClass('active');

});
