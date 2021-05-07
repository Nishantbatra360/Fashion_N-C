package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Product {
	 
	private int productId;
	private String productName;	
	private String color;	
	private Gender gender;
	private String productType;
	private String description;
	private double price;
	private String image;
	private List<Stock> stocks;	
	
	
	public Product(int productId,String productName, Gender gender, String productType,
			double price,String image ) {	
		this.productId = productId;
		this.productName = productName;	
		this.gender = gender;		
		this.productType = productType;		
		this.price = price;	
		this.image = image;
		this.stocks = new ArrayList<>();
	}
	
	public Product(String productName,String color,	Gender gender, String productType,
			String description, double price,String image, List<Stock> stocks ) {		
		this.productName = productName;		
		this.color = color;		
		this.gender = gender;		
		this.productType = productType;
		this.description = description;
		this.price = price;	
		this.image = image;
		this.stocks = stocks;
	}	
	
	public Product(int productId, String productName,String color,	Gender gender, String productType,
			String description, double price,String image, List<Stock> stocks ) {		
		this.productId = productId;
		this.productName = productName;		
		this.color = color;		
		this.gender = gender;		
		this.productType = productType;
		this.description = description;
		this.price = price;	
		this.image = image;
		this.stocks = stocks;
	}	
	
	public int getProductId() {return productId;}
	public String getProductName() {return productName;}	
	public String getColor() {return color;}	
	public Gender getGender() {return gender;}
	public String getProductType() {return productType;}
	public String getDescription() {return description;}
	public double getPrice() {return price;}
	public String getImage() {return image;}
	public List<Stock> getStocks() {return stocks;}
	
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
}
