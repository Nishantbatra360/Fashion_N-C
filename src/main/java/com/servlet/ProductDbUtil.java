package com.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;

public class ProductDbUtil {
	private DataSource dataSource;
		
	public ProductDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;		
	}
	
	public List<Product> getAll() throws Exception {//Get all products 				
		List<Product> products = new ArrayList<>();		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;			
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.products";			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			while (myRs.next()) {
				int theId = myRs.getInt("Id");
				String theName = myRs.getString("name");				
				Gender theGender = Gender.valueOf(myRs.getString("gender"));
				String theType = myRs.getString("type");
				Double thePrice = Double.parseDouble(myRs.getString("price"));				
				String theImage = myRs.getString("image");
				
				Product tempProduct = new Product(theId,theName,theGender,theType,thePrice,theImage);				
				products.add(tempProduct);				
			}			
			return products;
		}
		finally {close(myConn, myStmt, myRs);}				
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {	myRs.close();}			
			if (myStmt != null) { myStmt.close();}			
			if (myConn != null) { myConn.close();}
		}
		catch (Exception exc) {exc.printStackTrace();}			
	}
	
	public List<Product> getProductsByGender(String gen) throws Exception {//Get Products by Gender			
		List<Product> products = new ArrayList<>();		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;			
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.products "
					+ "WHERE gender=\"" + gen + "\"";					
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			while (myRs.next()) {
				int theId = myRs.getInt("Id");
				String theName = myRs.getString("name");				
				Gender theGender = Gender.valueOf(gen);
				String theType = myRs.getString("type");
				Double thePrice = Double.parseDouble(myRs.getString("price"));				
				String theImage = myRs.getString("image");				
				Product tempProduct = new Product(theId,theName,theGender,theType,thePrice,theImage);				
				products.add(tempProduct);					
			}			
			return products;
		}
		finally {close(myConn, myStmt, myRs);}				
	}
	
	public List<Product> getProductsByType(String productType) throws Exception {//Get Products by Gender			
		List<Product> products = new ArrayList<>();		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;			
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.products"
					+ "WHERE type=\"" + productType + "\" ";					
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			while (myRs.next()) {
				int theId = myRs.getInt("Id");
				String theName = myRs.getString("name");				
				Gender theGender = Gender.valueOf(myRs.getString("gender"));				
				Double thePrice = Double.parseDouble(myRs.getString("price"));				
				String theImage = myRs.getString("image");				
				Product tempProduct = new Product(theId,theName,theGender,productType,thePrice,theImage);				
				products.add(tempProduct);					
			}			
			return products;
		}
		finally {close(myConn, myStmt, myRs);}				
	}
	
	public List<Product> getProductsBySearch(String searchStr) throws Exception{
		List<Product> products = new ArrayList<>();		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;			
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.products"
					+ " WHERE name LIKE \"%"+searchStr+"%\" OR description LIKE \"%"+searchStr+"%\"";					
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			while (myRs.next()) {
				int theId = myRs.getInt("Id");
				String theName = myRs.getString("name");				
				Gender theGender = Gender.valueOf(myRs.getString("gender"));				
				Double thePrice = Double.parseDouble(myRs.getString("price"));
				String type=myRs.getString("type");
				String theImage = myRs.getString("image");				
				Product tempProduct = new Product(theId,theName,theGender,type,thePrice,theImage);				
				products.add(tempProduct);					
			}			
			return products;
		}
		finally {close(myConn, myStmt, myRs);}	
	}
	
	public Product getProductById(String productId) throws Exception {//Get Products by Name			
		Product product = null;		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		int theProductId = Integer.parseInt(productId);
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.products WHERE Id=" + theProductId;
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			
			if (myRs.next()) {
				int theId = myRs.getInt("Id");
				String theName = myRs.getString("name");
				String theColor = myRs.getString("color");
				Gender theGender = Gender.valueOf(myRs.getString("gender"));
				String theType = myRs.getString("type");
				String theDescription = myRs.getString("description");
				Double thePrice = Double.parseDouble(myRs.getString("price"));				
				String theImage = myRs.getString("image");	
				
				List<Stock> theStocks = getStocks(theId);
				
				product = new Product (theId,theName,theColor,theGender,theType,theDescription,thePrice,theImage,theStocks);
				
			}			
			return product;
		}
		finally {close(myConn, myStmt, myRs);}				
	}
	
	public List<Stock> getStocks(int productId) throws Exception {
		
		List<Stock> stocks = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();			
			String sql = "SELECT * FROM `fashion_n&c`.stocks WHERE productId=" + productId + " ORDER BY size";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			
			while (myRs.next()) {				
				String theSize = myRs.getString("size");	
				int theStock = myRs.getInt("stock");
				int theSoldNb = myRs.getInt("soldQuantity");			
				
				Stock stock = new Stock(theSize,theStock,theSoldNb);		
				stocks.add(stock);
			}			
			return stocks;
		}
		finally {close(myConn, myStmt, myRs);}				
	}
	
	public List<Stock> getAllStocks() throws Exception {
	       
        List<Stock> stocks = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
       
        try {
            myConn = dataSource.getConnection();           
            String sql = "SELECT * FROM stocks";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);           
           
            while (myRs.next()) { 
            	int theProductId = myRs.getInt("productId");
                String theSize = myRs.getString("size");   
                int theStock = myRs.getInt("stock");
                int theSoldNb = myRs.getInt("soldQuantity");           
               
                Stock stock = new Stock(theProductId,theSize,theStock,theSoldNb);       
                stocks.add(stock);
            }           
            return stocks;
        }
        finally {close(myConn, myStmt, myRs);}               
    }
	
	public int getUnitsInStock(String productId, String size) throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		int theProductId = Integer.parseInt(productId);
		
		try {
			myConn = dataSource.getConnection();			
			String sql = "select stock from `fashion_n&c`.stocks where productId =" + theProductId 
					+" AND size=\"" + size + "\"";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			
			if (myRs.next()) {				
				int theStock = myRs.getInt("stock");
				return theStock;
			}
			else return 0;			
		}
		finally {close(myConn, myStmt, myRs);}		
	}	
		
	public boolean wishlistExists(Wishlist wishlist) throws Exception {		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;		
		
		try {
			myConn = dataSource.getConnection();			
			String sql = "select * from `fashion_n&c`.wishlists where email=\"" + wishlist.getEmail()+"\" AND productId =" + wishlist.getProductId();
							
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			
			if (myRs!= null) {				
				return true;
			}
			else return false;			
		}
		finally {close(myConn, myStmt, myRs);}	
	}
	
	public void addWishlist(Wishlist theWishlist) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {			
			myConn = dataSource.getConnection();
		
			String sql = "insert into wishlists "
					   + "(email, productId) "
					   + "values (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);			
			
			myStmt.setString(1, theWishlist.getEmail());
			myStmt.setInt(2, theWishlist.getProductId());			
			myStmt.execute();
		}
		finally {close(myConn, myStmt, null);}	
		
	}
	
}
