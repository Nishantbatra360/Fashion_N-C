<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>
<c:if test="${sessionScope.user!=null }">
${sessionScope.user.name }'s Profile
</c:if>
</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div>
<div>
<div>
<label for="email">Email:-</label>
<input name="email" type="text" disabled="disabled" value="${sessionScope.user.email }"/>
</div>
<div>
<label for="name">Name:-</label>
<input name="name" type="text" value="${sessionScope.user.name }"/>
</div>
<div>
<label for="number">Contact:-</label>
<input name="number" type="number" value="${sessionScope.user.contact }"/>
</div>
</div>
<div>
<div>
<label for="currentPassword">Current Password:-</label>
<input name="currentPassword" type="password"/>
</div>
<div>
<label for="newPassword">New Password:-</label>
<input name="newPassword" type="password"/>
</div>
<div>
<label for="currentPassword">Confirm new password:-</label>
<input name="newPassword" type="password"/>
</div>
</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>