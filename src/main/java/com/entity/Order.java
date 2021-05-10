package com.entity;

import java.util.Date;
import java.util.List;

public class Order {
	
	private int orderId;
	private String email;
	private List<OrderedItem> orderedItems;	
	private String address;
	private String paymentMethod;	
	private Date dateOrdered;
	

	
	public Order(int orderId, String email,List<OrderedItem> orderedItems,String address, String paymentMethod, Date dateOrdered) {
		this.orderId = orderId;
		this.email = email;
		this.orderedItems = orderedItems;
		this.address = address;
		this.paymentMethod = paymentMethod;	
		this.dateOrdered = dateOrdered;
	}
	
	/*
	public Order(int orderId, String email,List<OrderedItem> orderedItems,String address, String paymentMethod) {
		this.orderId = orderId;
		this.email = email;
		this.orderedItems = orderedItems;
		this.address = address;
		this.paymentMethod = paymentMethod;			
	}*/
	
	public Order(String email,List<OrderedItem> orderedItems,String address, String paymentMethod) {		
		this.email = email;
		this.orderedItems = orderedItems;
		this.address = address;
		this.paymentMethod = paymentMethod;			
	}
	
	public Order(String email,String address, String paymentMethod)	{
		this.email = email;		
		this.address = address;
		this.paymentMethod = paymentMethod;					
	}
	
	public Order(int orderId, String email,String address, String paymentMethod, Date dateOrdered )	{
		this.orderId = orderId;
		this.email = email;		
		this.address = address;
		this.paymentMethod = paymentMethod;	
		this.dateOrdered = dateOrdered;
	}
	
	
	public int getOrderId() {
		return orderId;
	}
	public String getEmail() {
		return email;
	}

	public List<OrderedItem> getOrderedItems() {
		return orderedItems;
	}

	public String getAddress() {
		return address;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}	
	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

}
