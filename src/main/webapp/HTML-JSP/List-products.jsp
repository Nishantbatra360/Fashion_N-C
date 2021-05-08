<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>
<c:set var="gen" value="${PRODUCT_GENDER }"/>
<c:set var="ptype" value="${PRODUCT_TYPE}" />
${gen }
</title>

<jsp:include page="/HTML-JSP/Header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/List-products.css"/>

</head>

<body>

	<h1>${gen} FASHION</h1>
	<br/><br/>
	<Div class="flex">	
	
	<c:forEach var="tempProduct" items="${PRODUCT_LIST}">
		<div class="box" >
				<Form action="${pageContext.request.contextPath}/ProductControllerServlet" method="GET" >		
				
				<input type="hidden" name="productId" value="${tempProduct.productId }" />				
				
				<button type="submit" name="command" value="VIEW-PRODUCT" 
						class="product-button" > 
						<img src="${tempProduct.image }" width="200px" height="240px" /> 
						</button>
			    <button type="submit" name="command" value="ADD-WISHLIST" 
			    		class="wishlist-button" >WISHLIST</button>
			    						
		        <div class="product-name">${tempProduct.productName }</div>
				<div class="price">$${tempProduct.price }</div>	
				</Form>				
				
		</div>				 				 
	</c:forEach>
	
	
	
	</Div>
	
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
