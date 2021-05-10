<%@page import="com.entity.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order confirmation</title>
<style>
.form-design{
display:inline-flex;
flex-direction: column
}

.form-design input{
margin:10px;
}

.order-container{
display:flex;
justify-content: center;
margin-top:50px;
}
</style>
<script src="${pageContext.request.contextPath}/JAVASCRIPT/OrderConfirmation.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="order-container">
	<form id="orderForm" class="form-design" action="${pageContext.request.contextPath}/OrderControllerServlet">
		<input type="hidden" name="command" value="CREATE-ORDER"/>		
		<label for="streetAddress">Enter street address:</label> <input
			type="text" id="streetAddress" name="streetAddress" /> <label for="city">Enter
			city:</label> <input type="text" id="city" name="city" /> <label for="province">Enter
			province:</label> <input type="text" id="province" name="province" /> <label for="postal">Enter
			postal code:</label> <input type="text" id="postal" name="postal" />
		<c:set var="price" value="${0 }" />
		<c:forEach var="product" items="${PRODUCTS}">
			<c:forEach var="cart" items="${CART_ITEMS }">
				<c:if test="${cart.productId==product.productId }">
					<c:set var="price" value="${price+product.price }" />
				</c:if>
			</c:forEach>
		</c:forEach>
		<p>Order total: ${price}</p>
		<h3>Payment method:-</h3>
		<p>Cash on delivery</p>
		<input type="submit" name="command" onclick="validateInput(event)" value="Place order"/>
	</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>