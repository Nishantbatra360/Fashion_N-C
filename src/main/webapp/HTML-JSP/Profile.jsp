<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>
<c:if test="${sessionScope.user!=null }">
${sessionScope.user.name }'s Profile
</c:if>
</title>
<script src="${pageContext.request.contextPath}/JAVASCRIPT/Profile.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Profile.css"/>
</head>
<body>
<%
    String str=(String)request.getAttribute("INFO_SAVED");
    
    if(str!=null && str.equals("true")){
    	%>
    	<p class="success-design">Info Changed</p>
    	<%
    	request.removeAttribute("INFO_SAVED");
    }
    str=(String)request.getAttribute("CHANGE_PASSWORD_FAILED");
    if(str!=null && str.equals("true")){
    	%>
    	<p class="error-design">Incorrect Password</p>
    	<%
    	request.removeAttribute("CHANGE_PASSWORD_FAILED");
    }
    str=(String)request.getAttribute("CHANGE_PASSWORD_SUCCESS");
    if(str!=null && str.equals("true")){
    	%>
    	<p class="success-design">Password changed.</p>
    	<%
    	request.removeAttribute("CHANGE_PASSWORD_SUCCESS");
    }
    %>
<jsp:include page="Header.jsp"></jsp:include>
<div class="profile-parent-container">
<div>
<form id="save-form" action="${pageContext.request.contextPath}/ProfileControllerServlet" method="post">
<div>
<label for="email">Email:-</label>
<input name="email" type="text" disabled="disabled" value="${sessionScope.user.email }"/>
</div>
<div>
<label for="name">Name:-</label>
<input id="name" name="name" type="text" value="${sessionScope.user.name }"/>
</div>
<div>
<label for="number">Contact:-</label>
<input id="number" name="number" type="number" value="${sessionScope.user.contact }"/>
</div>
<input type="submit" name="command" value="Save changes" onclick="validateChanges(event)"/>
</form>
</div>
<div>
<form id="change-password-form" action="${pageContext.request.contextPath}/ProfileControllerServlet" method="post">
<div>
<label for="current-password">Current Password:-</label>
<input id="current-password" name="currentPassword" type="password"/>
</div>
<div>
<label for="new-password">New Password:-</label>
<input id="new-password" name="newPassword" type="password"/>
</div>
<div>
<label for="confirm-password">Confirm new password:-</label>
<input id="confirm-password" name="confirmPassword" type="password"/>
</div>
<input type="submit" name="command" value="Change password" onclick="validatePassword(event)"/>
</form>
</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>