package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.entity.Product;
/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDbUtil productDbUtil;
       
	@Resource(name="jdbc/fashion_nc")
	private DataSource dataSource;
	
	private String theCommand = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			productDbUtil = new ProductDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			theCommand = request.getParameter("command");
											
			if (theCommand == null) {
				theCommand = "LIST";
			}			
						
			switch (theCommand) {
			
			case "LIST":			
				listProducts(request, response);				
				break;			
			case "FILTER-GENDER":
				filterGender(request, response);
				break;
			case "FILTER-TYPE":
				filterType(request, response);
				break;
			case "VIEW-PRODUCT":
				viewProduct(request,response);	
			case "ADD-WISHLIST":
				addToWishList(request,response);
				/*
			case "ADD-CART":
				addToCart(request,response);
				*/
			default:				
				listProducts(request, response);			
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}	

	private void addToWishList(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		String theId = request.getParameter("productId");
		
		request.setAttribute("PRODUCT_ID", theId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Wishlist.jsp");
		dispatcher.forward(request, response);
	}

	private void viewProduct(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		String theId = request.getParameter("productId");
		Product product = productDbUtil.getProductById(theId);
		
		request.setAttribute("PRODUCT_DETAIL", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Product-detail.jsp");
		dispatcher.forward(request, response);		
	}

	private void filterType(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String theType = request.getParameter("productType");		
		List<Product> products = productDbUtil.getProductsByType(theType);	
		
		request.setAttribute("PRODUCT_LIST", products);					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/List-products.jsp");
		dispatcher.forward(request, response);
		
	}

	private void filterGender(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		
		String theGender = request.getParameter("gender");
		
		List<Product> products = productDbUtil.getProductsByGender(theGender);
		
		request.setAttribute("PRODUCT_GENDER",theGender);
		request.setAttribute("PRODUCT_TYPE", "ALL");
		request.setAttribute("PRODUCT_LIST", products);					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/List-products.jsp");
		dispatcher.forward(request, response);	
	}

	
	private void listProducts(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		List<Product> products = productDbUtil.getAll();
		
		request.setAttribute("PRODUCT_LIST", products);					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/List-products.jsp");
		dispatcher.forward(request, response);		
	}
	
}
