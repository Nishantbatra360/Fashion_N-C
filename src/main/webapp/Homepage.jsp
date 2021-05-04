<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="Homepage.css" />
<title>Home</title>

</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<%
				List<Banner> banners = (ArrayList<Banner>) request.getAttribute("banners");
				boolean isFirst = true;
				for (Banner banner : banners) {
					if (banner.getType().equals("carousel")) {
						if (isFirst) {
				%><div class="item active">
					<%
					} else {
					%><div class="item">
						<%
						}
						isFirst = false;
						%>
						<img src="<%=banner.getPath()%>" alt="Los Angeles"
							style="width: 100%;">
						<div class="carousel-caption">
							<h3><%=banner.getHeading()%></h3>
							<p><%=banner.getDescription()%></p>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<%
	List<String> types=new ArrayList<String>();
	types=(ArrayList<String>) request.getAttribute("types");
	for(String type:types){
		if(!type.equals("carousel")){
		%><h2><%=type %></h2>
				<div class="items-container"><%
		for(Banner banner:banners){
			if(banner.getType().equals(type)){
			%>
			<a href class="image-card"> <img src="<%=banner.getPath() %>" /></a>
			<%
			}
		}
		%>
		</div><%
		}}%>

</body>
</html>