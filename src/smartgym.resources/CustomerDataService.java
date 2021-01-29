package smartgym.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDataService {
	
    private List<Customer> customerList = new ArrayList<>();

    private static CustomerDataService ourInstance = new CustomerDataService();

    public static CustomerDataService getInstance() {
        return ourInstance;
    }

    public String addCustomer(Customer customer) {
        String newId = Integer.toString(customerList.size() + 1);
        customer.setId(newId);
        customerList.add(customer);
        return newId;
    }

    public String addCustomer(String firstName,String lastName,String email, String password, String phoneNumber,
   		 String gender, Date dateOfBirth, String newId)  {
    	
        Customer customer = new Customer(firstName, lastName, email, password, phoneNumber, gender, dateOfBirth, newId) ;
        
        return addCustomer(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }

        return null;
    }
}
