<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.entity.*" %>
<%@ page import="com.servlet.*" %>

<html>
<head><title> Order number ${ORDER.orderId}
</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Product-detail.css"/>
</head>
<body>
<jsp:include page="/HTML-JSP/Header.jsp"/>

<div class="order-container">
<p>Order placed successfully.</p>
<p>Order number is ${ORDER.orderId}</p>
<p> email:- ${ORDER.email }
</div>







</body>
</html>