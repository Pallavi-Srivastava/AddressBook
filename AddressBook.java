package com.btm.collectionex;

import java.util.Comparator;

public class AddressBook 
{
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private int zip;
	private long phoneNo;
	
	public AddressBook(String firstName, String lastName, String address, String city, String state, int zip,
			long phoneNo) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() 
	{
		return "AddressBook [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", phoneNo=" + phoneNo + "]";
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}


	/*Comparator for sorting the list by FiestName*/
	public static Comparator<AddressBook> NameComparator = new Comparator<AddressBook>() 
	{
		@Override
		public int compare(AddressBook o1, AddressBook o2) 
		{
				   String StudentName1 = o1.getFirstName().toUpperCase();
				   String StudentName2 = o2.getFirstName().toUpperCase();
				   
		    //ascending order		   
			return StudentName1.compareTo(StudentName2);
		}
	};	
	
	/*Comparator for sorting the list by ZipCode*/
    public static Comparator<AddressBook> ZipComparator = new Comparator<AddressBook>() 
    {
			@Override
			public int compare(AddressBook o1, AddressBook o2) 
			{
				int zipSort1 = o1.getZip();
				int zipSort2 = o2.getZip();
				return zipSort1-zipSort2;
			} 
    };
}
