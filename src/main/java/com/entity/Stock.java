package com.entity;

public class Stock {	
	
	private int productId;
	private String size;
	private int unitsInStock;	
	private int soldQuantity;	
	
	public Stock(String size, int unitsInStock,int soldQuantity) {		
		this.size = size;
		this.unitsInStock = unitsInStock;
		this.soldQuantity = soldQuantity;
	}
	public Stock(int productId, String size, int unitsInStock,int soldQuantity) {		
		this.productId = productId;
		this.size = size;
		this.unitsInStock = unitsInStock;
		this.soldQuantity = soldQuantity;
	}
	
	public int getProductId() {return productId;}
	public String getSize() {return size;}
	public int getUnitsInStock() {return unitsInStock;}
	public int getSoldQuantity() {return soldQuantity;}
	
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}	

	public void UpdateStock(int quantity)//when adding more items in Stock
	{
		this.unitsInStock += quantity;
	}
	public void UpdateSoldQuantity(int quantity) //when some item was sold
	{
		this.soldQuantity += quantity;
		this.unitsInStock -= quantity;
	} 
	
	public boolean outOfStock() {
		return (unitsInStock == 0);		
	}
	
	public boolean isEnoughStock(int quantity) {
		return (unitsInStock >= quantity);
	}
}
