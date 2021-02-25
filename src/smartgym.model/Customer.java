package smartgym.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long phoneNumber;
	private char gender;
	private Date dateOfBirth;
	private String userName;
	
	public Customer(String firstName,String lastName,String email, String password,long phoneNumber,
		 char gender, Date dateOfBirth,String userName) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;
		this.phoneNumber=phoneNumber;
		this.gender=gender;
		this.dateOfBirth= dateOfBirth;
		this.userName=userName;
		
	}
	
	public Customer() {
		
	}

	
	public String getUserName() {
		return userName;
	}

	
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
