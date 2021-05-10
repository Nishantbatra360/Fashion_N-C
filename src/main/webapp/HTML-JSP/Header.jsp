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
<script>
function searchProduct(){
	document.getElementById("search-form").submit();
}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Header.css"/>
</head>
<body>
<Div class="header-container">
<div class="header-container">
<a href="${pageContext.request.contextPath}/HomepageControllerServlet">
<img style="margin-right: 4vw;" src="${pageContext.request.contextPath}/assets/icons/logo.svg" alt="Fashion N & C" height=60pt width=60pt>
</a>

<form class="header-item" action="${pageContext.request.contextPath}/ProductControllerServlet" method="GET">
	<c:set var="data" value="MEN,WOMEN,BOYS,GIRLS" />
	<c:set var="genderArray" value="${fn:split(data,',')}" />
	<input type="hidden" name="command" value="FILTER-GENDER"/>
	<c:forEach var="tempGender" items="${genderArray}">			
		<input class="customize-font-color customize-font-size" type="submit" name="gender" value="${tempGender }"/>		
	</c:forEach>
</form>

</div>
<div class="search-bar-container">
<form id="search-form" action="${pageContext.request.contextPath}/ProductControllerServlet" method="get">
<input type="hidden" name="command" value="FILTER-SEARCH"/>
<input style="flex:1;" type="text" name="inputSearch"/>
<img src="${pageContext.request.contextPath}/assets/icons/search.svg" onclick="searchProduct()" height=15pt width=15pt style="cursor:pointer;align-self:center;"/>
</form>
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
<a href="http://localhost:8080/Fashion_N_C/HTML-JSP/LoginSignup.jsp" class="anchor-design customize-font-color">Login/ Signup</a>
</div>
</c:if>
<c:if test="${sessionScope.user!=null }">
<div class="profile-items-container">
<c:url var="logout" value="http://localhost:8080/Fashion_N_C/LogoutControllerServlet"/>
<c:url var="profile" value="http://localhost:8080/Fashion_N_C/HTML-JSP/Profile.jsp"/>

<a href="${profile }" class="anchor-design customize-font-color">View/Edit Profile</a>
<a href="${logout }" class="anchor-design customize-font-color">Logout</a>
</div>
</c:if>

</div>
</div>
</div>


<div class="header-item flex-vertical-center">
<img class="align-center" src="${pageContext.request.contextPath}/assets/icons/wishlist.svg" height=30px width=30px/>
<p class="customize-font-color align-center"><c:url var="displayWishlist" value="/WishlistControllerServlet"/>
<a href="${displayWishlist}">Wishlist</a>
</p>
</div>

<div class="header-item">
<span class="cart-items-count customize-font-color">
<c:if test="${CART_COUNT>0 && sessionScope.user!=null }">${CART_COUNT }</c:if>
</span>

<div class="flex-vertical-center">
<img class="align-center" src="${pageContext.request.contextPath}/assets/icons/cart.svg" height=30px width=30px/>
<p class="customize-font-color align-center">
<c:url var="displayCart" value="/CartControllerServlet"/>
<a href="${displayCart}">Cart</a>
</p>
</div>
</div>
</div>
</Div>
</body>
</html>