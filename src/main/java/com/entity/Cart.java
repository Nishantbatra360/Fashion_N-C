package com.entity;

public class Cart {
	private String email;
	private int productId;
	private String size;
	private int qty;
	
	public Cart(String email,int productId,String size,int qty) {
		this.email=email;
		this.productId=productId;
		this.size=size;
		this.qty=qty;
	}

	public String getEmail() {
		return email;
	}

	public int getProductId() {
		return productId;
	}

	public String getSize() {
		return size;
	}

	public int getQty() {
		return qty;
	}
	
	
}
