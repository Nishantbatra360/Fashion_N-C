package com.servlet;

import java.io.IOException;
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
import com.entity.Order;
import com.entity.OrderedItem;
import com.entity.Product;
import com.entity.Stock;
import com.entity.User;

/**
 * Servlet implementation class OrderControllerServlet
 */
@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderDbUtil orderDbUtil;
	String theCommand = null;
	
	@Resource(name="jdbc/fashion_nc")  
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			orderDbUtil = new OrderDbUtil(dataSource);
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
				theCommand = "LIST-ORDERS";
			}			
						
			switch (theCommand) {
			
			case "LIST-ORDERS":		//GET ALL ORDERS OF A USER	(EMAIL)
				listOrders(request, response);			
				break;			
			case "CREATE-ORDER":
				createOrder(request, response);
				break;
			case "VIEW-ORDER":  //VIEW ORDER BY ORDER ID
				viewOrder(request, response);
				break;		
				
			default:				
				listOrders(request, response);			
			}				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}	

	private void viewOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String orderId = request.getParameter("orderId");	
		
		Order order = orderDbUtil.getOrder(Integer.parseInt(orderId));
		List<OrderedItem> items = orderDbUtil.getOrderedItems(Integer.parseInt(orderId));
		request.setAttribute("ORDER", order);
		request.setAttribute("ORDERED_ITEMs", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Order-detail.jsp");
		dispatcher.forward(request, response);		
	}

	private void createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		String streetAddress = (String)request.getAttribute("streetAddress");
		String city = (String)request.getAttribute("city");
		String province = (String)request.getAttribute("province");
		String postalCode = (String)request.getAttribute("postal");		
		String address = streetAddress + "," + city + "," + province + "," + postalCode;
		String paymentMethod = "Cash On Delivery";	
		int returnedOrderId = 0;
		
		List<Cart> cartItems = new ArrayList<Cart>();
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		CartDbUtil cartDbUtil = new CartDbUtil(dataSource);
		
		if (user == null) {			
			response.sendRedirect("/HTML-JSP/LoginSignup.jsp");				
		} 
				
		cartItems = cartDbUtil.getCartItems(user.getEmail());
		
		if (cartItems !=null)
		{
			returnedOrderId = orderDbUtil.addOrder(user.getEmail(),address,paymentMethod);
			
			if (returnedOrderId == 0)
			{
				System.out.println("Order not created succesffully");
			}
			else {
				for(Cart cart : cartItems) {					
					orderDbUtil.addOrderedItem(returnedOrderId, cart);					
				}
				cartDbUtil.deleteCartItems(user.getEmail());
			}
		}
		
		Order order = orderDbUtil.getOrder(returnedOrderId);
				
		request.setAttribute("ORDER",order);					
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/Order-detail.jsp");
		dispatcher.forward(request, response);		
	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");		
		
		List<Order> orders= new ArrayList<Order>();
		
		if (user == null) {
						
		}
		else {
			orders= orderDbUtil.getOrders(user.getEmail());		
		}
		
		request.setAttribute("ORDERS", orders);	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML-JSP/List-orders.jsp");
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

}
