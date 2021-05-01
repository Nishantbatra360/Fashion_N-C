<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Header.css"/>
</head>
<body>
<Div class="header-container">
	<div class="header-container">
	<img style="margin-right: 4vw;" src="logo.svg"/ alt="Fashion N & C" height=60pt width=60pt>
	<div class="item customize-font-color customize-font-size">Men</div>
	<div class="item customize-font-color customize-font-size">Women</div>
	<div class="item customize-font-color customize-font-size">Boys</div>
	<div class="item customize-font-color customize-font-size">Girls</div>
	</div>
	
	<div class="search-bar-container">
	<input style="flex:1;" type="text" name="inputSearch"/>
	<img src="search.svg" height=15pt width=15pt/>
	</div>
	
	<div class="header-container">
		<div class="item flex-vertical-center profile">
			<img class="align-center" src="profile.svg" height=30px width=30px/>
			<p class="customize-font-color align-center">Profile</p>
				<div style="position:relative;">
					<div class="profile-container">						
						<div class="profile-items-container">
						<a href class="anchor-design customize-font-color">Login</a>
						<a href class="anchor-design customize-font-color">Signup</a>
						</div>
						
						<div class="profile-items-container">
						<a href class="anchor-design customize-font-color">View/Edit Profile</a>
						<a href class="anchor-design customize-font-color">Logout</a>
						</div>						
					</div>
				</div>
		</div>
		<div class="item flex-vertical-center">
		<img class="align-center" src="wishlist.svg" height=30px width=30px/>
		<p class="customize-font-color align-center">Wishlist</p>
		</div>
		
		<div class="item">
			<span class="cart-items-count customize-font-color">1</span>
			<div class="flex-vertical-center">
				<img class="align-center" src="cart.svg" height=30px width=30px/>
				<p class="customize-font-color align-center">Cart</p>
			</div>
		</div>
	</div>
</Div>
</body>
</html>