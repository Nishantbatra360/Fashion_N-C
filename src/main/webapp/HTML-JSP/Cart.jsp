<%@page import="com.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/Cart.css" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<c:if test="${CART_COUNT<1 }">
	<p class="empty-cart">Cart is Empty</p>
	</c:if>
	<c:if test="${CART_COUNT>0 }">
		<div>
		<div class="parent-container">
			<div class="products-container">
				<div class="items-count-and-total-container">				
					<p class="count">My Shopping Bag (${CART_COUNT} Items)</p>
					<p>
						<c:set var="price" value="${0 }" />
						<c:forEach var="product" items="${PRODUCTS}">
							<c:forEach var="cart" items="${CART_ITEMS }">
								<c:if test="${cart.productId==product.productId }">
									<c:set var="price" value="${price+product.price }" />
								</c:if>
							</c:forEach>
						</c:forEach>
						<span>&#36;</span>${price }
					</p>
				</div>
				<c:forEach var="cart" items="${CART_ITEMS}">
					<div class="product-card">
						<div class="product-info">
							<c:forEach var="product" items="${PRODUCTS}">
								<c:if test="${cart.productId==product.productId }">
									<img class="product-image" style="margin-right: 10px;"
										src="${product.image }" />
									<div class="product-details">
										<p>Alovia Clothing</p>
										<div class="product-name-and-price">
											<p style="flex: 1;">${product.productName}</p>
											<p>
												<span>&#36;</span>${product.price }</p>
								</c:if>
							</c:forEach>
						</div>
						<p>Sold by: Cloud Ware</p>
						<div class="product-attributes">
							<Label for="size">size:</Label> <select class="margin-right"
								name="size">
								<c:forEach var="stock" items="${STOCKS }" >
									<c:if test="${cart.productId == stock.productId && cart.size == stock.size}" > 
										<option selected>${stock.size } </option>
										</c:if>
								</c:forEach>								
								
							</select> <Label for="qty">qty:</Label> <select name="qty">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
					</div>
			</div>
			<div class="product-options">
			<form action="CartControllerServlet">
			<input type="hidden" name="productId" value="${cart.productId}"/>
				<input class="input-button red-background" name="command" type="submit"
					value="remove" /> <input class="input-button green-background"
					name="command" type="submit" value="move to wishlist" />
					</form>
			</div>
		</div>
		</c:forEach>

	</div>
	<div class="order-total-container">
		<div>
			<p class="flex-one margin-right">Total MRP</p>
			<p><span>&#36;</span>${price }</p>
		</div>
		<div>
			<p class="flex-one margin-right">Discount on MRP</p>
			<p>$0</p>
		</div>
		<div>
			<p class="flex-one margin-right">Convenience fee</p>
			<p><span>&#36;</span>${price*10/100 }</p>
		</div>
		<div>
			<p class="flex-one margin-right">Total amount</p>
			<p><span>&#36;</span>${price+price*10/100 }</p>
		</div>
		<form action="OrderConfirmationServlet">
		<input class="input-button green-background" type="submit"
			value="Place order" />
			</form>
	</div>
	</c:if>
	</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>