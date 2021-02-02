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

    public String addCustomer(String firstName, String lastName, String email, String password, String phonenumber,
    		String gender, String dateOfBirth ) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhoneNumber(phonenumber);
        customer.setGender(gender);
        customer.setDateOfBirth(dateOfBirth);
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
