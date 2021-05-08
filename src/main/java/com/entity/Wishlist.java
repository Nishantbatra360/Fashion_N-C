package com.entity;

public class Wishlist {
	private String email;	
	private int productId;
	
	public Wishlist(String email, int productId) {
		this.email = email;
		this.productId = productId;		
	}
	public String getEmail() {
		return email;
	}

	public int getProductId() {
		return productId;
	}

}
