package com.entity;

public class User {
	
	String email;
	String name;
	String contact;
	
	public User(String email,String name,String contact) {
		this.email=email;
		this.name=name;
		this.contact=contact;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
}
