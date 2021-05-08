package com.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

import com.entity.Cart;

public class CartDbUtil {

	private DataSource dataSource;

	public CartDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public List<Cart> getCartItems(String inputEmail) throws SQLException{
		List<Cart> carts=new ArrayList<Cart>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * from carts where email=\""+inputEmail+"\"";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				String email = myRs.getString("email");
				int productId = myRs.getInt("productId");
				String size = myRs.getString("size");
				int qty=myRs.getInt("qty");
				
				Cart cart=new Cart(email,productId,size,qty);	
				
				carts.add(cart);
			}
			
			return carts;
		}
		finally {
			close(myConn, myStmt, myRs);
		}		
	}
	
	public void removeItem(String email,int productId) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		myConn = dataSource.getConnection();
		
		String sql = "DELETE from carts where email=\""+email+"\" and productId=\""+productId+"\"";
		
		myStmt = myConn.createStatement();
		int result=myStmt.executeUpdate(sql);
		
		close(myConn,myStmt);
	}
	
	
	private void close(Connection myConn,Statement myStmt,ResultSet myRs) {
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection myConn,Statement myStmt) {
		try {
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
