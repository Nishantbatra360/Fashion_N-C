<%@ page import="com.entity.*" %>
<%@ page import="java.util.*" %>
<html>
<head><title>Men</title>
<jsp:include page="Header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Men.css"/>
<script src="ViewProduct.js"></script>
</head>

<body>
<h1>MEN FASHION</h1>
<br/><br/>
<Div class="flex">

<%
	
	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("data");		
	
	if (productList != null){
		for (Product p : productList) {
			out.println("<div class=\"box\" id=\"" + p.productName + "\">");
			out.println("<button class=\"product-button\" id=\"" + p.productName + 
					"\" name=\"" + p.productName + "\" onclick=\"ViewProduct(this.name)\"><img src=\"" + p.image + "\"  width=\"200px\" height=\"240px\" /></button>");
			out.println("<button class=\"wishlist-button\" id=\"" + p.productName + "\" name=\"" + p.productName + "\" onclick=\"AddToWishlist(this.name)\"> WISHLIST</button>");
	        out.println("<div class=\"product-name\">" + p.productName + "</div>"); 
			out.println("<div class=\"price\">$" + p.price + "</div>");	
			out.println("</div>");
		}
	}
	
%>	
</Div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>