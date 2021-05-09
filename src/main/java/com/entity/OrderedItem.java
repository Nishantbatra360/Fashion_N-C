package com.entity;

public class OrderedItem {
	private int orderId;	
	private int productId;
	private String size;
	private int qty;
	
	
	public OrderedItem(int orderId, int productId, String size, int qty) {
		this.orderId = orderId;
		this.productId = productId;
		this.size = size;
		this.qty = qty;
	}
	
	public OrderedItem(int productId, String size, int qty) {		
		this.productId = productId;
		this.size = size;
		this.qty = qty;
	}
	public int getOrderId() {
		return orderId;
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
