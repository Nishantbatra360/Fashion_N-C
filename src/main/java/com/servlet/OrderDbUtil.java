package com.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

import com.entity.*;

public class OrderDbUtil {

	private DataSource dataSource;

	public OrderDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public List<Order> getOrders(String inputEmail) throws Exception{
		List<Order> orders= new ArrayList<Order>();		
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * from orders where email=\""+inputEmail+"\"";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				int orderId	= myRs.getInt("orderId");
				String address = myRs.getString("address");
				String paymentMethod = myRs.getString("paymentMethod");
				Date date = myRs.getDate("date");
				
				List<OrderedItem> itemList = getOrderedItems(orderId);
				
				if (itemList != null) {
					Order tempOrder = new Order(orderId,inputEmail,itemList,address,paymentMethod,date);					
					orders.add(tempOrder);					
				}					
			}				
			return orders;
		}
		finally {
			close(myConn, myStmt, myRs);
		}		
	}
	
	public Order getOrder(int orderId) throws Exception{			
		Order order = null;		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * from orders where orderId=" + orderId;
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			if (myRs.next()) {		
				String email = myRs.getString("email");
				String address = myRs.getString("address");
				String paymentMethod = myRs.getString("paymentMethod");
				Date date = myRs.getDate("date");				
				
				List<OrderedItem> itemList = getOrderedItems(orderId);	
				
				if (itemList != null) {
					order = new Order(orderId,email,itemList,address,paymentMethod,date);
					//order = new Order(orderId,email,address,paymentMethod,date);
					return order;
				}
				return null;				
			}
			else 
				return null;		
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}		
	}
	
	public List<OrderedItem> getOrderedItems(int orderId) throws Exception {
		
		List<OrderedItem> items = new ArrayList<OrderedItem>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;			
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * from `ordered-items` where orderId="+orderId;
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {				
				int productId = myRs.getInt("productId");
				String size = myRs.getString("size");
				int qty=myRs.getInt("qty");
				OrderedItem tempItem = new OrderedItem(orderId,productId, size, qty);
				items.add(tempItem);								
			}			
			return items;
		}
		finally {
			close(myConn, myStmt, myRs);
		}		
	}
		
	public int addOrder(String email,String address,String paymentMethod) throws Exception {		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id = 0;
		
		try {			
			myConn = dataSource.getConnection();
		
			String sql = "insert into orders "
					   + "(email, address, paymentMethod, date) "
					   + "values (?, ?, ?,?)";
			
			myStmt = myConn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);			
			
			myStmt.setString(1, email);
			myStmt.setString(2, address);
			myStmt.setString(3, paymentMethod);
			myStmt.setDate(4, (java.sql.Date) new java.sql.Date(0));			
			myStmt.execute();
			
//			int rowAffected = myStmt.executeUpdate();
//			if(rowAffected == 1)
//			{                
//               myRs = myStmt.getGeneratedKeys();
//                if(myRs.next())
//                    id = myRs.getInt("orderId");
//                	 id = myRs.getInt(1);   
//            }	
			sql="SELECT * FROM orders ORDER BY orderId desc";
			Statement myStmt2=myConn.createStatement();
			myRs=myStmt2.executeQuery(sql);
			myRs.next();
			return myRs.getInt("orderId");			
		}
		finally {close(myConn, myStmt, null);}
	}
	
	public boolean addOrderedItem(int orderId, Cart item) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;		
		
		try {			
			myConn = dataSource.getConnection();
		
			String sql = "insert into `ordered-items` "
					   + "(orderId, productId, size, qty) "
					   + "values (?, ?, ?,?)";
			
			myStmt = myConn.prepareStatement(sql);						
			
			myStmt.setInt(1, orderId);
			myStmt.setInt(2, item.getProductId());
			myStmt.setString(3, item.getSize());
			myStmt.setInt(4, item.getQty());	
			
			int rowAffected = myStmt.executeUpdate();		
			return (rowAffected > 0);	
		}
		finally {close(myConn, myStmt, null);}
		
	}

	public boolean orderExists(int orderId) throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;		
		
		try {
			myConn = dataSource.getConnection();			
			String sql = "select * from `fashion_n&c`.orders where orderId=" + orderId; 					
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);			
			
			if (myRs.next()) {				
				return true;
			}
			else return false;			
		}
		finally {close(myConn, myStmt, myRs);}	
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
	
	
	
}
