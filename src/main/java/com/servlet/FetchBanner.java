package com.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.annotation.Resource;

/**
 * Servlet implementation class FetchBanner
 */
@WebServlet("/FetchBanner")
public class FetchBanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/fashion_nc")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBanner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			
			String sql="Select * FROM banners";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			boolean isFirst=true;
			while(myRs.next()) {
				String firstName=myRs.getString("type");
				String path=myRs.getString("path");
				
				if(isFirst) {
				out.println("<div class=\"item active\">\r\n"
						+ "        <img src=\""+path+"\""+" alt=\"Los Angeles\" style=\"width:100%;\">\r\n"
						+ "        <div class=\"carousel-caption\">\r\n"
						+ "          <h3>Los Angeles</h3>\r\n"
						+ "          <p>LA is always so much fun!</p>\r\n"
						+ "        </div>\r\n"
						+ "      </div>");
				}
				else {
					out.println("<div class=\"item\">\r\n"
							+ "        <img src=\""+path+"\""+" alt=\"Los Angeles\" style=\"width:100%;\">\r\n"
							+ "        <div class=\"carousel-caption\">\r\n"
							+ "          <h3>Los Angeles</h3>\r\n"
							+ "          <p>LA is always so much fun!</p>\r\n"
							+ "        </div>\r\n"
							+ "      </div>");
				}
				isFirst=false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
