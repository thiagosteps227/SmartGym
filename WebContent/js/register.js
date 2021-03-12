var customerURL = "http://localhost:8080/SmartGym/rest/customer/createCustomer";
var onlyCustomer= "http://localhost:8080/SmartGym/rest/customer";


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

//method to find the customer by username and email
var findByUsernameAndEmail= function() {
	$.ajax({
		type: 'GET',
		url: onlyCustomer + '/query?userName=' + $('#userName').val()+'&email='+$('#email').val(),
		dataType: "json",
		success: function(data){
			currentCustomer = data;
			existingUser(currentCustomer);
		}
	});
};

//method to find the customer by ID
var findById= function() {
	$.ajax({
		type: 'GET',
		url: allCustomersURL + '/' + $('#customerID').val(),
		dataType: "json",
		success: function(data){
			console.log('findById success: ' + data.userName);
			currentCustomer = data;
			renderDetails(currentCustomer);
		}
	});
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

//to check existing customer
var userExists = false;
var existingUser = function(customer) {
	
	var name = customer.userName;
	var email = customer.email;
	if (name === $('#userName').val() || email === $('#email').val()){
		userExists = true;
	} else {
		userExists = false;
	}
	return userExists;
};



//to check empty inputs
var validateForm = function() {

	  var formInvalid = false;
	  $('#registerForm input').each(function() {
	    if ($(this).val() === '') {
	      formInvalid = true;
	    }
	  });

	  return formInvalid;
}

$(document).ready(function(){
	$('#registerButton').click(function() {
		if(validateForm()=== true){
				alert('One or Two fields are empty. Please fill up all fields');
		} else if ( userExists === true){
				alert("IF AND ELSE There is a customer registered with this username or email")
		} else {
			addCustomer();
		}
	});
	$('div[id="customerDiv"]').hide();
	
	//to make the navbar white when you are in the register page
	$('.topnav a').removeClass('active');
    $('#registerPage').addClass('active');

});
