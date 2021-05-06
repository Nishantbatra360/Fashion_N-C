<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="Cart.css" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div>
		<div class="parent-container">
			<div class="products-container">
				<div class="items-count-and-total-container">
					<p class="count">My Shopping Bag (2 Items)</p>
					<p>Total:$200</p>
				</div>
				<div class="product-card">
					<div class="product-info">
						<img style="margin-right: 10px;" src="product.webp" />
						<div class="product-details">
							<p>Product Brand</p>
							<div class="product-name-and-price">
								<p style="flex: 1;">Product Description</p>
								<p>Price</p>
							</div>
							<p>Sold by: Seller name</p>
							<div class="product-attributes">
								<Label for="size">size:</Label> <select class="margin-right"
									name="size">
									<option>S</option>
									<option>M</option>
									<option>L</option>
									<option>XL</option>
								</select> <Label for="qty">qty:</Label> <select name="qty">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
								</select>
							</div>
						</div>
					</div>
					<div class="product-options">
						<input class="input-button red-background" type="button"
							value="remove" /> <input class="input-button green-background"
							type="button" value="move to wishlist" />
					</div>
				</div>
			</div>

			<div class="order-total-container">
				<div>
					<p class="flex-one margin-right">Total MRP</p>
					<p>Price</p>
				</div>
				<div>
					<p class="flex-one margin-right">Discount on MRP</p>
					<p>Amount</p>
				</div>
				<div>
					<p class="flex-one margin-right">Convenience fee</p>
					<p>FREE</p>
				</div>
				<div>
					<p class="flex-one margin-right">Total amount</p>
					<p>Price</p>
				</div>
				<input class="input-button green-background" type="button"
					value="Place order" />
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>