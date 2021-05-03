package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.entity.Gender;
import com.entity.Product;

/**
 * Servlet implementation class FetchMenServlet
 */
@WebServlet("/FetchMenServlet")
public class FetchMenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Resource(name="jdbc/eshop")
	private DataSource dataSource;
	
	/**
     * @see HttpServlet#HttpServlet()     * 
     */
    public FetchMenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{			
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");		
				
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			ArrayList<Product> productList = new ArrayList<Product>();
			
			myConn=dataSource.getConnection();
			
			String sql="SELECT productName, price, image, productType\r\n"
					+ "FROM eshop.products\r\n"
					+ "WHERE gender = \"MEN\"\r\n"
					+ "GROUP BY productName, price, image";
			
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			String text = "";
			
			
			while(myRs.next()) { 
				String pName=myRs.getString("productName");
				String pPrice = String.valueOf(myRs.getBigDecimal("price"));				
				String pImage=myRs.getString("image");		
				String pType=myRs.getString("productType");				
				
				Product p = new Product(pName,Gender.MEN,pType,Double.valueOf(pPrice),pImage);
				productList.add(p);				
				
				out.println(pName);
				out.println(pPrice);
				out.println(pImage);
				out.println(pType);					
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
