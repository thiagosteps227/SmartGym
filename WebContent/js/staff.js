var allCustomersURL = "http://localhost:8080/SmartGym/rest/customer";
var classtableURL = "http://localhost:8080/SmartGym/rest/classtable";
var timetableURL = "http://localhost:8080/SmartGym/rest/timetable";
var currentCustomer;

var findAllCustomers = function(){
	$.ajax({
		type: 'GET',
		url: allCustomersURL,
		datatype: "json",
		success: showRegisteredCustomers,
	});
};

var findAllClasses = function(){
	$.ajax({
		type: 'GET',
		url: classtableURL,
		datatype: "json",
		success: renderClasses,
	});
};

var findTimetable = function(){
	$.ajax({
		type: 'GET',
		url: timetableURL,
		datatype: "json",
		success: renderTimetable,
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

var addClass = function(){
	$.ajax({type: 'POST', 
		contentType: 'application/json',
		url: classtableURL, 
		datatype: "json", 
		data: classFormToJSON(),
		success: function(){
			alert("Class added");
			findAllClasses();
		}});
};

var addTimetable = function(){
	$.ajax({type: 'POST', 
		contentType: 'application/json',
		url: timetableURL, 
		datatype: "json", 
		data: timetableFormToJSON(),
		success: function(data, textStatus, jqXHR){
			alert("Timetable added");
			findTimetable();
		},
		error: function(jqXHR, textStatus, errorThrown){
			var errorString = JSON.stringify(jqXHR.responseText)
			if( jqXHR.status === 403){
				alert(errorString);
			}
		}
	});
};

var deleteTimetable=function(id) {
	$.ajax({
		type: 'DELETE',
		url: timetableURL + '/' + id,
		success: function(data, textStatus, jqXHR){
			alert('Timetable deleted successfully');
            findTimetable();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Deleting time table error');
		}
	});
};

var updateTimetable= function (id) {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: timetableURL  + '/' + id,
		dataType: "json",
		data: timetableFormToJSON(),
		success: function(data, textStatus, jqXHR){
			findTimetable();
			alert('Timetable updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('update Timetable error: ' + textStatus);
			var errorString = JSON.stringify(jqXHR.responseText)
			if( jqXHR.status === 403){
				alert(errorString);
			}
		}
	});
};

var deleteClass=function(id) {
	$.ajax({
		type: 'DELETE',
		url: classtableURL + '/' + id,
		success: function(data, textStatus, jqXHR){
			alert('Class deleted successfully');
            findAllClasses();
            $('#classID').val("");
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Delete Class error');
		}
	});
};

var updateClass= function (id) {
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: classtableURL  + '/' + id,
		dataType: "json",
		data: classFormToJSON(),
		success: function(data, textStatus, jqXHR){
			findAllClasses();
			alert('Classes updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateClassesr error: ' + textStatus);
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
//show timetable
function renderTimetable(timetable) {
	$('#timetable tr').remove();
	$.each(timetable, function(index, timetable){
		$('#timetable').append('<tr><td>'+timetable.classCode+'</td><td>'+timetable.className+'</td><td>'+
				timetable.instructor+'</td><td>'+timetable.weekDay+'</td><td>'+timetable.classTime+'</td></tr>' );
	});	
}
//show classes details
function renderClasses(classtable) {
	$('#classtable tr').remove();
	$.each(classtable, function(index, classtable){
		$('#classtable').append('<tr><td>'+classtable.classID+'</td><td>'+classtable.className+'</td><td>'+classtable.personLimit+'</td><td>'+classtable.pricePerClass+
				'</td><td>'+classtable.priceTwelveWeeks+'</td></tr>' );
	});	
}
//show customer details
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
//stringify the timetable form
var timetableFormToJSON = function (){
	var classCode = $('#classCode').val();
	return JSON.stringify({
		"classCode": classCode == "" ? null : classCode, 
			"className" : $('#TTclassName').val(),
			"instructor" : $('#instructor').val(),
			"weekDay" : $('#weekDay').val(),
			"classTime" : $('#classTime').val(),
	});
}
//stringify the classtable form
var classFormToJSON = function (){
	var classID = $('#classID').val();
	return JSON.stringify({
		"classID": classID == "" ? null : classID, 
			"className" : $('#className').val(),
			"personLimit" : $('#personLimit').val(),
			"pricePerClass" : $('#pricePerClass').val(),
			"priceTwelveWeeks" : $('#priceTwelveWeeks').val(),
	});
}
//stringify the customer form
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
//displays all the customers when the button client is clicked
var showAllCustomers = function(){
	findAllCustomers();
	$('div[id="customerDiv"]').show();
	$(this).hide();
	$('#showClassesButton').hide();
	$('#showTimetableButton').hide();
	$('#deleteCustomer').show();
	$('div[id="deleteFormDiv"]').show();
	$('#showCustomerDetailsButton').show();
	$('#updateForm').show();
}
//show classes in the div classes
var showClasses = function(){
	findAllClasses();
	$('div[id="classtableDiv"]').show();
	$(this).hide();
	$('#showClientsButton').hide();
	$('#showTimetableButton').hide();
	$('div[id="updateClassForm"]').show();
}
//show timetable div
var showTimetable = function(){
	findTimetable();
	$('div[id="timetableDiv"]').show();
	$('div[id="updateTimetableForm"]').show();
	$(this).hide();
	$('#showClientsButton').hide();
	$('#showClassesButton').hide();
	//$('div[id="updateClassForm"]').show();
}

//In the DIV customer form
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
	$('div[id="classtableDiv"]').hide();
	$('div[id="timetableDiv"]').hide();
	$('div[id="updateClassForm"]').hide();
	$('div[id="updateTimetableForm"]').hide();
	$('.topnav a').removeClass('active');
    $('#staffPage').addClass('active');
    //button that show customers and change the other buttons behaviour
	$('#showClientsButton').click(showAllCustomers)
	//button that shows the classes
	$('#showClassesButton').click(showClasses)
	//show time table
	$('#showTimetableButton').click(showTimetable);
	
	$('#showCustomerDetailsButton').hide();
	$('#deleteCustomer').hide();
	$('#updateForm').hide();
	$('#updateCustomer').hide();
	//all about classes
	$('#createClass').click(addClass);
	$(document).on("click", '#deleteClass', function(e){
		e.preventDefault;
		var id=$('#classID').val();
		deleteClass(id);
	})
	$(document).on("click", '#updateClass', function(e){
		e.preventDefault;
		var id=$('#classID').val();
		updateClass(id);
	})
	//all about customers
	$('#deleteCustomer').click(deleteCustomer);
	$('#showCustomerDetailsButton').click(findById);
	$('#updateCustomer').click(updateCustomer);
	$('#customerID').val("");
	//all about timetable
	$('#createTimetable').click(addTimetable);
	$(document).on("click", '#deleteTimetable', function(e){
		e.preventDefault;
		var id=$('#classCode').val();
		deleteTimetable(id);
	})
	$(document).on("click", '#updateTimetable', function(e){
		e.preventDefault;
		var id=$('#classCode').val();
		updateTimetable(id);
	})
	
});
