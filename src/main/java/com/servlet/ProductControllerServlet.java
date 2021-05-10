package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.entity.Wishlist;
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
				getWishlistedItems(request, response);
				listProducts(request, response);	
				break;			
			case "FILTER-GENDER":
				getWishlistedItems(request, response);
				filterGender(request, response);
				break;
			case "FILTER-TYPE":
				getWishlistedItems(request, response);
				filterType(request, response);
				break;
			case "FILTER-SEARCH":
				getWishlistedItems(request, response);
				filterSearch(request,response);
				break;
			case "VIEW-PRODUCT":
				viewProduct(request,response);					
				break;
			case "ADD-WISHLIST":
				getWishlistedItems(request, response);
				addToWishList(request,response);
				listProducts(request,response);			
				break;
			case "ADD-TO-CART":
				addToCart(request,response);
				viewProduct(request,response);
				
				break;
				
			default:				
				listProducts(request, response);			
			}				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}	

	private void filterSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> products=new ArrayList<Product>();
		String searchFilter=request.getParameter("inputSearch");
		products=productDbUtil.getProductsBySearch(searchFilter);
		request.setAttribute("PRODUCT_LIST", products);		
		System.out.println(products.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/List-products.jsp");
		dispatcher.forward(request, response);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");		
		int theId = Integer.parseInt(request.getParameter("productId"));
		String theSize = request.getParameter("select-size");		
		
		UserDbUtil userDbUtil = new UserDbUtil(dataSource);
		CartDbUtil cartDbUtil=new CartDbUtil(dataSource);
		
		List<Cart> carts = new ArrayList<Cart>();	
		Cart cart = null;
		
		
		if(user==null) {			
			String guestEmail = null;
			Cookie[] theCookies = request.getCookies();		
			
			if(theCookies !=null){
				   for(Cookie tempCookie : theCookies){
					   if("userCookie".equals(tempCookie.getName())){
						   guestEmail=tempCookie.getValue();
						   break;
					   }
				   }
			   }
			
			if (guestEmail == null) {//create new user cookie for guest					
				do	{	
					int randomNb = (int)(Math.random()*100000 + 1);
					String name = "guest" + String.valueOf(randomNb);
					guestEmail = name + "@fashionnc.com";					
					
					if (!userDbUtil.userExists(guestEmail)) {						
						user = new User(guestEmail,name,"");
						userDbUtil.addUser(guestEmail,name,guestEmail,""); 						
						break;			
					}					
				} while (true);	
				
				Cookie theCookie = new Cookie("userCookie",guestEmail);
				theCookie.setMaxAge(60*60*24*7); 
				response.addCookie(theCookie);	
				
			} else {
				if (!userDbUtil.userExists(guestEmail)) {
					userDbUtil.addUser(guestEmail,guestEmail,guestEmail,"");					
				} 				
					user = userDbUtil.getUser(guestEmail);				
			}
		}	
		
		cart = new Cart(user.getEmail(),theId,theSize,1);
		if (!cartDbUtil.cartExists(cart))			
			cartDbUtil.addCart(cart);		
		
		carts=cartDbUtil.getCartItems(user.getEmail());	
		session.setAttribute("CART_COUNT", carts.size());		
		
		request.setAttribute("PRODUCT_ID", theId);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Product-View.jsp");
		//dispatcher.forward(request, response);	
			
	}
	
	private void addToWishList(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String theId = request.getParameter("productId");
		
		
		int productId = Integer.parseInt(theId);
		
		if(user==null) {		
			request.setAttribute("PRODUCT_ID",theId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/LoginSignup.jsp");
			dispatcher.forward(request, response);			
		}		
		else {
			System.out.println("In else");
			Wishlist wishlist = new Wishlist(user.getEmail(),productId);
				System.out.println("adding");
				productDbUtil.addWishlist(wishlist);
			System.out.println("Add to wishlist");
			request.setAttribute("PRODUCT_ID",theId);
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Product-detail.jsp");
			//dispatcher.forward(request, response);
		}		
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
	
	private void getWishlistedItems(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		WishlistDbUtil wishlistDbUtil=new WishlistDbUtil(dataSource);
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		List<Wishlist> wishlists=new ArrayList<Wishlist>();
		if(user!=null) {
			wishlists=wishlistDbUtil.getWishlistedItems(user.getEmail());
		}
		request.setAttribute("WISHLISTED_ITEMS", wishlists);
	}
	
}
