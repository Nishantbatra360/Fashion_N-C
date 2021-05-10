<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.entity.*" %>
<%@ page import="com.servlet.*" %>


<html>
<head><title>
<c:set var="product" value="${PRODUCT_DETAIL}"/>
${product.productName }

</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Product-detail.css"/>
</head>

<body>
<jsp:include page="/HTML-JSP/Header.jsp"/>


	<div class="image-container">	
		<img class="product-image" src="${product.image }" style="margin-left: 10px;" />			
	</div>

	<div class="product-info">			
		<h1>Roadster </h1><br/>	
		<h2>${product.productName } </h2><br/>	
			
		<div class="dividerthin"></div>
				
		<h1>&#36; ${product.price }" </h1>
		<p>inclusive of all taxes</p>
				
		<Form action="${pageContext.request.contextPath}/ProductControllerServlet" method="GET">
			<input type="hidden" name="productId" value="${product.productId }" />				
			<label for="select-size"><b>SELECT SIZE </b></label>			
				<select name="select-size">									
					<c:forEach var="tempStock" items="${product.stocks}">					
						<option>${tempStock.size} </option>	
					</c:forEach>
				</select>							
						
			<button type="submit" name="command" value="ADD-TO-CART" 
						class="add-cart-button" >ADD TO CART</button>						
			<button type="submit" name="command" value="ADD-WISHLIST" 
			    		class="wishlist-button-1" >WISHLIST</button>
		</Form>
			
		<div class="seller-name">
		<p>Seller: Truenet Commerce</p>	
		
		</div>
			 
		<div class="dividerthin"></div>
		
		<div class="product-details"> 
			<label><b>PRODUCT DETAILS </b></label><br/>
			<p>${product.description}</p>		
		</div>
		
		<div class="dividerthin"></div>
		
		<div class="delivery-options"> 
			<label><b>DELIVERY OPTIONS </b></label><br/>
			<p>Free Delivery on order above Rs.799
			Pay on delivery might be available
			Easy 30 days returns and exchanges
			Try & Buy might be available
			</p>		
		</div>		
				
	</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
