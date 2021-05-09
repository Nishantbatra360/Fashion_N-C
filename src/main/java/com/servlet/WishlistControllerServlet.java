package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class WishlistControllerServlet
 */
@WebServlet("/WishlistControllerServlet")
public class WishlistControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WishlistDbUtil wishlistDbUtil;
	
	@Resource(name="jdbc/fashion_nc")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			wishlistDbUtil = new WishlistDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user==null) {
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/HTML-JSP/LoginSignup.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			String command=request.getParameter("command");
			
			if(command!=null && command.equals("remove")) {
				try {
					int productId=Integer.parseInt(request.getParameter("productId"));
					wishlistDbUtil.removeItem(user.getEmail(), productId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(command!=null && command.equals("Move to cart")) {
				try {
					int productId=Integer.parseInt(request.getParameter("productId"));
					wishlistDbUtil.removeItem(user.getEmail(), productId);
					Cart cart=new Cart(user.getEmail(), productId, "L",1);
					CartDbUtil cartDbUtil=new CartDbUtil(dataSource);
					cartDbUtil.addCart(cart);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				listWishlistItems(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void listWishlistItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Wishlist> wishlists=new ArrayList<Wishlist>();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		wishlists=wishlistDbUtil.getWishlistedItems(user.getEmail());
		request.setAttribute("WISHLIST_ITEMS", wishlists);
		List<Product> products=new ArrayList<Product>();
		ProductDbUtil productDbUtil=new ProductDbUtil(dataSource);
		products=productDbUtil.getAll();
		request.setAttribute("PRODUCTS", products);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/HTML-JSP/Wishlist.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
