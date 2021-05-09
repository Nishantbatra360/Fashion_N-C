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
	<div>
		<div class="parent-container">
			<div class="products-container">
				<c:forEach var="cart" items="${WISHLIST_ITEMS}">
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
												</div>
												</div>
								</c:if>
							</c:forEach>
						</div>
						<p>Sold by: Cloud Ware</p>
						<div class="product-attributes">
							<Label for="size">size:</Label> <select class="margin-right"
								name="size">
								<option>S</option>
								<option>M</option>
								<option>L</option>
								<option>XL</option>
							</select> <Label for="qty">qty:</Label> <select name="qty">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
							</select>
						</div>
						<div class="product-options">
						<form action="WishlistControllerServlet">
			<input type="hidden" name="productId" value="${cart.productId}"/>
				<input class="input-button red-background" name="command" type="submit"
					value="remove" /> <input class="input-button green-background"
					name="command" type="submit" value="Move to cart" />
					</form>
			</div>
					</div>
					</c:forEach>
			</div>
		</div>

	</div>

	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>