<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
		<!-- START Common -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<!-- END Common -->
		
		<title>Butang | Items</title>		
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
				    <a class="nav-link active" href="/cart">Cart</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="/logout">Logout</a>
				  </li>
				</ul>
			</nav>
			
		</header>
		<div class="container">
			<div class="row">
				<c:forEach items="${itemList}" var="item">
					<div class="col-4">
						<div class="card" style="width: 18rem;">
						  <div class="card-body">
						    <h5 class="card-title">${item.name}</h5>
						    <h6 class="card-subtitle mb-2 text-muted">Php ${item.price} </h6>
						    <p class="card-text">${item.description}</p>
						    <form action="/cart" method="POST">
						    	<input type="hidden" name ="itemId" value="${ item.itemId }"/>
						    	<button type="submit" class="btn btn-primary">Add to Cart</button>
						    </form>
						    
						  </div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<footer></footer>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	</body>

</html>