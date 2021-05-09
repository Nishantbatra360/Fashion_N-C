package com.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.entity.PasswordEncryption;
import com.entity.User;

public class UserDbUtil {
	private DataSource dataSource;

	public UserDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public User getUser(String email,String password) throws Exception {
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="Select * from users where email=\""+email+"\""+" and password=\""+password+"\"";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			if(myRs.next()) {
				String retrievedName=myRs.getString("name");
				String retrievedEmail=myRs.getString("email");
				String retrievedContact=myRs.getString("contact");
				return new User(retrievedEmail, retrievedName, retrievedContact);
			}
			else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public User getUser(String email) throws Exception {
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="Select * from users where email=\""+email+"\"";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			if(myRs.next()) {				
				String retrievedEmail=myRs.getString("email");
				String retrievedContact=myRs.getString("contact");
				String retrievedName=myRs.getString("name");
				return new User(retrievedEmail, retrievedName, retrievedContact);
			}
			else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public boolean userExists(String email) throws Exception {
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="Select * from users where email=\""+email+"\"";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			if(myRs.next()) {	
				return true;
			}
			else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	
	
	public boolean addUser(String email,String name,String password,String contact) throws Exception{
		Connection myConn=null;
		Statement myStmt=null;
		int rowsAffected=0;
		
		password=PasswordEncryption.encrypt(password);
		
		try {
			myConn=dataSource.getConnection();
			String sql="Insert into users values("+ "\""+email+ "\", "
	                + "\"" +name+ "\"," + "\""+password+"\","
	                        + "\"" +contact+"\")";
			myStmt=myConn.createStatement();
			rowsAffected=myStmt.executeUpdate(sql);
			if(rowsAffected>0) {
				return true;
			}
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	
	public void changeInfo(String email,String name,String contact) {
		Connection myConn=null;
		Statement myStmt=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="UPDATE users SET name=\""+name+"\",contact=\""+contact+"\" where email=\""+email+"\"";
			myStmt=myConn.createStatement();
			myStmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	
	public boolean changePassword(String email,String oldPassword,String newPassword) {
		Connection myConn=null;
		Statement myStmt=null;
		
		oldPassword=PasswordEncryption.encrypt(oldPassword);
		newPassword=PasswordEncryption.encrypt(newPassword);
		int rowsAffected=0;
		
		try {
			myConn=dataSource.getConnection();
			String sql="UPDATE users SET password=\""+newPassword+"\" where email=\""+email+"\" and password=\""+oldPassword+"\"";
			myStmt=myConn.createStatement();
			rowsAffected=myStmt.executeUpdate(sql);
			if(rowsAffected==1) {
				return true;
			}
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			close(myConn,myStmt,null);
		}
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
