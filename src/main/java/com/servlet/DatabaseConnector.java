package com.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class DatabaseConnector {
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;

	public DatabaseConnector(DataSource dataSource) {
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet getAll(String tableName) throws SQLException {
		String sql = "Select * from "+tableName;
		myRs = myStmt.executeQuery(sql);
		return myRs;
	}
	public ResultSet getConditional(String tableName,String columnName,String value) throws SQLException {
		String sql="Select * from "+tableName+" where "+columnName+"="+value;
		myRs=myStmt.executeQuery(sql);
		return myRs;
	}
	public ResultSet getDistinct(String columnName,String tableName) throws SQLException {
		String sql="Select Distinct("+columnName+") from "+tableName;
		myRs=myStmt.executeQuery(sql);
		return myRs;
	}
	public void closeConnection() {
		try {
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
