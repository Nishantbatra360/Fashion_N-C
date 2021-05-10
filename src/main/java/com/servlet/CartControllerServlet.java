package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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

import com.entity.*;

@WebServlet("/CartControllerServlet")
public class CartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CartDbUtil cartDbUtil;
	
	@Resource(name="jdbc/fashion_nc")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			cartDbUtil = new CartDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command=request.getParameter("command");
			int productId=Integer.parseInt(request.getParameter("productId"));
			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			
			if(command.equals("remove")) {
				cartDbUtil.removeItem(user.getEmail(),productId);
				List<Cart> carts=cartDbUtil.getCartItems(user.getEmail());
				session.setAttribute("CART_COUNT",carts.size());
			}
			else if(command.equals("move to wishlist")) {
				cartDbUtil.removeItem(user.getEmail(),productId);
				List<Cart> carts=cartDbUtil.getCartItems(user.getEmail());
				session.setAttribute("CART_COUNT",carts.size());
				Wishlist wishlist = new Wishlist(user.getEmail(),productId);
				ProductDbUtil productDbUtil=new ProductDbUtil(dataSource);
				productDbUtil.addWishlist(wishlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				listCartItems(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void listCartItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Cart> carts=new ArrayList<Cart>();
		List<Product> products=new ArrayList<Product>();
		List<Stock> stocks=new ArrayList<Stock>();
		
		if(user==null) {			
			//get cart list of user guest (userCookie)
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
			
			carts=cartDbUtil.getCartItems(guestEmail);					
		}
		else {
			carts=cartDbUtil.getCartItems(user.getEmail());			
		}
		
		ProductDbUtil productDbUtil=new ProductDbUtil(dataSource);
		products=productDbUtil.getAll();
		stocks=productDbUtil.getAllStocks();
		
		
		request.setAttribute("CART_ITEMS", carts);
		request.setAttribute("PRODUCTS", products);
		request.setAttribute("STOCKS", stocks);			
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/HTML-JSP/Cart.jsp");
		dispatcher.forward(request, response);
		
	}

}
