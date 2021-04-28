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
<div class="item">Men</div>
<div class="item">Women</div>
<div class="item">Boys</div>
<div class="item">Girls</div>
</div>
<div class="search-bar-container">
<input style="flex:1;" type="text" name="inputSearch"/>
<img src="search.svg" height=15pt width=15pt/>
</div>
<div class="header-container">
<div class="item flex-vertical-center">
<img class="align-center" src="profile.svg" height=30px width=30px/>
<p class="default-font align-center">Profile</p>
</div>
<div class="item flex-vertical-center">
<img class="align-center" src="wishlist.svg" height=30px width=30px/>
<p class="default-font align-center">Wishlist</p>
</div>
<div class="item">
<span class="cart-items-count default-font">1</span>
<div class="flex-vertical-center">
<img class="align-center" src="cart.svg" height=30px width=30px/>
<p class="default-font align-center">Cart</p>
</div>
</div>
</div>
</Div>
</body>
</html>