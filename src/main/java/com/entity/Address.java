package com.entity;

public class Address {
	private String apartment;
	private String streetName;    
    private String city; 
    private String province;
    private String postalCode;
    private String country; 
    
    public Address (String apartment, String streetName, String city, 
    		String province, String postalCode, String country) {
    	this.apartment = apartment;
    	this.streetName = streetName;
    	this.city = city;
    	this.province = province;
    	this.postalCode = postalCode;
    	this.country = country;    	    	
    }
    
    public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	} 
    

}
