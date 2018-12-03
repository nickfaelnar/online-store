<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<!-- START Common -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<!-- END Common -->
		
		<title>Butang | Cart</title>		
	</head>
	<body>
		<header>
			<nav class="navbar navbar-light bg-light">
			  <a class="navbar-brand" href="#">
			    <img src="/docs/4.1/assets/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
			    Butang
			  </a>
			  <ul class="nav justify-content-end">
				  <li class="nav-item">
				    <a class="nav-link active" href="/items">List</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="/logout">Logout</a>
				  </li>
				</ul>
			</nav>
		</header>
		<c:if test="${not empty error}">
			<div class="container">
				<h2>${error}</h2>
			</div>
		</c:if>
		<c:if test="${empty error}">
		    <div class="container">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Name</th>
			      <th scope="col">Description</th>
			      <th scope="col">Price</th>
			      <th scope="col"></th>
			    </tr>
			    </thead>
			    <tbody>
			    	<c:forEach items="${items}" var="item">
			    	<tr>
				      <td>${item.name}</td>
				      <td>${item.description}</td>
				      <td>Php ${item.price}</td>
				      <td>
				      	<form action="/cart/delete" method="POST">
					    	<input type="hidden" name ="orderItemId" value="${ item.orderItemId }"/>
					    	<button type="submit" class="btn btn-primary">Delete</button>
					    </form>
					 </td>
				    </tr>			   </c:forEach> </tbody>
				  
				</table>
			</div>
			
			<div class="container">
				<h2>Credit Card</h2>
				<p>${creditCard.cardNum}</p>
			</div>
			<div class="container">
				<h2>Delivery Address</h2>
				<p>${address.houseBldgSt} ${address.brgy} ${address.city}  ${address.prov}</p>
			</div>
			
			<div class="container">
				<h1>Total: ${total}</h2>
				<form action="/checkout" method="POST">
					<input type="hidden" name ="orderId" value="${order.orderId}"/>
					<button type="submit" class="btn btn-primary">Checkout</button>
				</form>
			</div>
		</c:if>
		
		
		<footer></footer>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</body>

</html>