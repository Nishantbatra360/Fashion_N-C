package com.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.entity.Wishlist;

public class WishlistDbUtil {

	private DataSource dataSource;

	public WishlistDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public List<Wishlist> getWishlistedItems(String inputEmail) throws SQLException{
		List<Wishlist> wishlists=new ArrayList<Wishlist>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * from wishlists where email=\""+inputEmail+"\"";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				String email = myRs.getString("email");
				int productId = myRs.getInt("productId");
				
				Wishlist item=new Wishlist(email,productId);	
				
				wishlists.add(item);
			}
			
			return wishlists;
		}
		finally {
			close(myConn, myStmt, myRs);
		}		
	}
	
	public void removeItem(String email,int productId) throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		
		myConn = dataSource.getConnection();
		
		String sql = "DELETE from wishlists where email=\""+email+"\" and productId=\""+productId+"\"";
		
		myStmt = myConn.createStatement();
		int result=myStmt.executeUpdate(sql);
		
		close(myConn,myStmt,null);
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
