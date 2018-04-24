package com.contactapi.contactapp.entity;

/* contactid
 * firstname
 * lastname
 * fullname
 * adress
 * email
 * mobilenumber
 * password
 * Skills
 * */

public class Contact {
	
	private int contactId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String adress;
	private String email;
	private int mobileNumber;
	private String password;
	
	public Contact(){}
	public Contact(int contactId){
		this.contactId = contactId;
	}
	public Contact(String fullName){
		this.fullName = fullName;
	}
	
	public Contact(int contactId, String firstName, String lastName, String fullName, String adress,
			String email, int mobileNumber, String password){
		
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.adress = adress;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public HashMap<Skill, Level> getContactSkills() {
		return contactSkills;
	}

	public void setContactSkills(HashMap<Skill, Level> contactSkills) {
		this.contactSkills = contactSkills;
	}
	*/
}

