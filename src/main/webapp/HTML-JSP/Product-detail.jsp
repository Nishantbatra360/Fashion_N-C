<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.entity.*" %>
<%@ page import="com.servlet.*" %>


<html>
<head><title>
<c:set var="product" value="${PRODUCT_DETAIL}"/>
${product.productName }

</title>

<jsp:include page="/HTML-JSP/Header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Product-view.css"/>

</head>


<body>
<Div class="product-detail-container">
	<div class="image-contain">	
	<img src="${product.image }" />	
	</div>
	
	<div class="product-info">
		
		<h3>${product.productName } </h3><br/>		
		<div class="dividerthin"></div>		
		<h4></h4>$CAD ${product.price }" />
		
		<Form action="${pageContext.request.contextPath}/ProductControllerServlet" method="GET">
			<input type="hidden" name="productId" value="${product.productId }" />				
			<label for="select-size"><b>SELECT SIZE </b></label>			
			<select name="size">
				<c:forEach var="tempStock" items="${product.stocks}">										
					<option>${tempStock.size}
					</option>			
				</c:forEach>		
			</select>
			
			<label for="select-size"><b>SELECT QUANTITY </b></label>			
			<c:set var="data" value="1,2,3,4,5,6,7,8,9,10" />
			<c:set var="quantityArray" value="${fn:split(data,',')}" />
			<select name="quantity">
				<c:forEach var="tempQuantity" items="${quantityArray}">										
					<option>${tempQuantity}	</option>			
				</c:forEach>		
			</select>									
			<br/>			
			<button type="submit" name="command" value="ADD-CART" 
						class="add-cart-button" >ADD TO CART</button>		 
						
			<button type="submit" name="command" value="ADD-WISHLIST" 
			    		class="wishlist-button-1" >WISHLIST</button>
		</Form>
			 
		<div class="dividerthin"></div>
		
		<label for="description"><b>DESCRIPTION </b></label><br/>
		<p>${product.description}</p>	
		
	</div>

</Div>
</body>
</html>
