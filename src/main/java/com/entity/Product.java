package com.entity;

public class Product {
	 
	private int productId;
	public String productName;
	public String size;
	public String color;
	public int stock;
	public int soldQuantity;
	public Gender gender;
	public String productType;
	public String description;
	public double price;
	public String image;
	
	public Product(int productId, String productName,String size,String color,
			int stock, int soldQuantity, Gender gender, String productType,
			String description, double price,String image ) {
		this.productId = productId;
		this.productName = productName;
		this.size = size;
		this.color = color;
		this.stock = stock;
		this.soldQuantity = soldQuantity;
		this.gender = gender;		
		this.productType = productType;
		this.description = description;
		this.price = price;	
		this.image = image;
	}
	
	public Product(String productName, Gender gender, String productType,
			double price,String image ) 
	{		
		this.productName = productName;	
		this.gender = gender;		
		this.productType = productType;		
		this.price = price;	
		this.image = image;
	}
	/*
	public String getProductName() {
		return productName;
	}
	public String getProductName() {
		return productName;
	}
	*/
	
	public void UpdateStock(int quantity)
	{
		this.stock += quantity;
	}
	public void UpdateSoldQuantity(int quantity)
	{
		this.soldQuantity += quantity;
		this.stock -= quantity;
	}

}
