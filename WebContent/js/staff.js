var allCustomersURL = "http://localhost:8080/SmartGym/rest/customer";

var findAllCustomers = function(){
	$.ajax({
		type: 'GET',
		url: allCustomersURL,
		datatype: "json",
		success: showRegisteredCustomers,
	});
};

var findById= function(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: allCustomersURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#deleteCustomer').show();
			console.log('findById success: ' + data.name);
			currentCustomer = data;
			renderDetails(currentCustomer);
		}
	});
};

var renderDetails=function(customer) {
	$('#customerID').val(customer.customerID);

};
//shows a list of all clients registered in the database
function showRegisteredCustomers(customer) {
	$.each(customer, function(index, customer){
		//$('#customerTable').append('<li><a href="#" id="' + customer.id + '">'+customer.firstName+'</a></li>')
		$('#customerTable').append('<tr><td>'+customer.userName+'</td><td>'+customer.firstName+'</td><td>'+customer.email+'</td></tr>' );
	});	
}

$(document).on("click", '#customerTable a', function(){
	findById(this.id);
	
	});

var showAllCustomers = function(){
	findAllCustomers();
	$('div[id="customerDiv"]').show();
	$(this).hide();
	$('#deleteCustomer').show();
}

var deleteCustomers = function(){
	$('div[id="deleteDiv"]').show();
	
}

$(document).ready(function(){
	$('div[id="customerDiv"]').hide();
	$('div[id="deleteDiv"]').hide();
	$('.topnav a').removeClass('active');
    $('#staffPage').addClass('active');
    //button that show customers and change the other buttons behaviour
	$('#showCustomersButton').click(showAllCustomers)
	$('#deleteCustomer').hide();
	$('#deleteCustomer').click(deleteCustomers);
	
});