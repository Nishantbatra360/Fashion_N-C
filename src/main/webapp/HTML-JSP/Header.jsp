<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.entity.User"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=yes">
<title>Header</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Header.css"/>
</head>
<body>
<Div class="header-container">
<div class="header-container">
<a href="${pageContext.request.contextPath}/HomepageControllerServlet">
<img style="margin-right: 4vw;" src="${pageContext.request.contextPath}/assets/icons/logo.svg" alt="Fashion N & C" height=60pt width=60pt>
</a>

<div class="header-item customize-font-color customize-font-size">
	<c:set var="data" value="MEN,WOMEN,BOYS,GIRLS" />
	<c:set var="genderArray" value="${fn:split(data,',')}" />
	<c:forEach var="tempGender" items="${genderArray}">					
					
		<c:url var="tempLink" value="ProductControllerServlet">
			<c:param name="command" value="FILTER-GENDER" />
			<c:param name="gender" value="${tempGender}" />
		</c:url>
		<a href="${tempLink}">${tempGender} </a>		
																		
	</c:forEach>						
</div>		

</div>
<div class="search-bar-container">
<input style="flex:1;" type="text" name="inputSearch"/>
<img src="${pageContext.request.contextPath}/assets/icons/search.svg" height=15pt width=15pt/>
</div>
<div class="header-container">
<div class="header-item flex-vertical-center profile">
<img class="align-center" src="${pageContext.request.contextPath}/assets/icons/profile.svg" height=30px width=30px/>
<p class="customize-font-color align-center">

<c:if test="${sessionScope.user==null }">
Profile
</c:if>
<c:if test="${sessionScope.user!=null }">
${sessionScope.user.name }
</c:if>

</p>
<div style="position:relative;">
<div class="profile-container">

<c:if test="${sessionScope.user==null }">
<div class="profile-items-container">
<a href="${pageContext.request.contextPath}/HTML-JSP/LoginSignup.jsp" class="anchor-design customize-font-color">Login/ Signup</a>
</div>
</c:if>
<c:if test="${sessionScope.user!=null }">
<div class="profile-items-container">
<c:url var="logout" value="LogoutControllerServlet"/>

<a href class="anchor-design customize-font-color">View/Edit Profile</a>
<a href="${logout }" class="anchor-design customize-font-color">Logout</a>
</div>
</c:if>

</div>
</div>
</div>
<div class="header-item flex-vertical-center">
<img class="align-center" src="${pageContext.request.contextPath}/assets/icons/wishlist.svg" height=30px width=30px/>
<p class="customize-font-color align-center">Wishlist</p>
</div>
<div class="header-item">
<span class="cart-items-count customize-font-color">
<c:if test="${user!=null && CART_COUNT>0 }">${CART_COUNT }</c:if>
</span>
<div class="flex-vertical-center">
<img class="align-center" src="${pageContext.request.contextPath}/assets/icons/cart.svg" height=30px width=30px/>
<p class="customize-font-color align-center">
<c:url var="displayCart" value="CartControllerServlet"/>
<a href="${displayCart}">Cart</a>
</p>
</div>
</div>
</div>
</Div>
</body>
</html>