var allCustomersURL = "http://localhost:8080/SmartGym/rest/customer";

var currentCustomer;

var findAllCustomers = function(){
	$.ajax({
		type: 'GET',
		url: allCustomersURL,
		datatype: "json",
		success: showRegisteredCustomers,
	});
};

var findById= function() {
	console.log('findById: ' +customerID);
	$.ajax({
		type: 'GET',
		url: allCustomersURL + '/' + $('#customerID').val(),
		dataType: "json",
		success: function(data){
			$('#updateCustomer').show();
			console.log('findById success: ' + data.userName);
			currentCustomer = data;
			renderDetails(currentCustomer);
		}
	});
};

var updateCustomer= function () {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: allCustomersURL  + '/' + $('#customerID').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			findAllCustomers();
			alert('Customer updated successfully');
            currentCustomer = data;
			renderDetails(currentCustomer);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateCustomer error: ' + textStatus);
		}
	});
};

var deleteCustomer=function() {
	console.log('deleteCustomer');
	$.ajax({
		type: 'DELETE',
		url: allCustomersURL + '/' + $('#customerID').val(),
		success: function(data, textStatus, jqXHR){
			alert('customer deleted successfully');
            findAllCustomers();
            $('#customerID').val("");
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteWine error');
		}
	});
};

var renderDetails=function(customer) {
	$('#userName').val(customer.userName);
	$('#firstName').val(customer.firstName);
	$('#lastName').val(customer.lastName);
	$('#email').val(customer.email);
	$('#password').val(customer.password);
	$('#phoneNumber').val(customer.phoneNumber);
	$('#gender').val(customer.gender);
	$('#dateOfBirth').val(customer.dateOfBirth);
};

var formToJSON = function (){
	var customerID = $('#customerID').val();
	return JSON.stringify({
		"customerID": customerID == "" ? null : customerID, 
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

//shows a list of all clients registered in the database
var showRegisteredCustomers = function(list) {
	$('#customerTable tr').remove();
	$.each(list, function(index, customer){
		$('#customerTable').append('<tr><td>'+customer.customerID+'</td><td>'+customer.userName+'</td><td>'+customer.email+'</td></tr>' );
	});	
}


var showAllCustomers = function(){
	findAllCustomers();
	$('div[id="customerDiv"]').show();
	$(this).hide();
	$('#deleteCustomer').show();
	$('div[id="deleteFormDiv"]').show();
	$('#showCustomerDetailsButton').show();
	$('#updateForm').show();
}


var showCustomerDetails = function(){
	findById();
	$('div[id="customerDiv"]').hide();
	$('div[id="updateForm"]').show();
	$('#showCustomerDetailsButton').toggle();
	$('#deleteCustomer').toggle();
}

$(document).ready(function(){
	$('div[id="customerDiv"]').hide();
	$('div[id="deleteFormDiv"]').hide();
	$('.topnav a').removeClass('active');
    $('#staffPage').addClass('active');
    //button that show customers and change the other buttons behaviour
	$('#showClientsButton').click(showAllCustomers)
	$('#showCustomerDetailsButton').hide();
	$('#deleteCustomer').hide();
	$('#updateForm').hide();
	$('#updateCustomer').hide();
	
	$('#deleteCustomer').click(deleteCustomer);
	$('#showCustomerDetailsButton').click(findById);
	$('#updateCustomer').click(updateCustomer);
	$('#customerID').val("");
	
});
