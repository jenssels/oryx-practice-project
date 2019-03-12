<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<title>Admin Home</title>
</head>
<body>
<div class="container">
<div class="col-md-11 offset-1">
	<h1>Hello ${user.name}</h1>
	<a href="home"><button type="button" class="btn btn-primary">Return to home</button></a>
	<h2>These are all the users</h2>
	<table class="table table-striped">
		<tr>
			<th align="left">
				User Id
			</th>
			<th align="left">
				User Name
			</th>
			<th align="left">
				User Mail
			</th>
			<th align="left">
				Actions
			</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.mail}</td>
				<td>
					<a href="deleteAdminUser?id=${user.id}"><button type="button" class="btn btn-danger">Delete User</button></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h2>These are all the orders of all users</h2>
	<table class="table table-striped">
		<tr>
			<th align="left">
				Order Number
			</th>
			<th align="left">
				Product
			</th>
			<th align="left">
				Product description
			</th>
			<th align="left">
				Price
			</th>
			<th align="left">
				Amount
			</th>
			<th align="left">
				Total Price
			</th>
			<th align="left">
				User
			</th>
			<th align="left">
				Status
			</th>
			<th align="left">
				Actions
			</th>
		</tr>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${order.id}</td>
				<td>${order.product.name}</td>
				<td>${order.product.description}</td>
				<td>${order.product.price} lv</td>
				<td>${order.amount}</td>
				<td>${order.getTotalPrice()} lv</td>
				<td>${order.user.name}</td>
				<td>${order.status}</td>
				<td>
					<c:if test="${order.status == 'Paid'}">
						<a href="sendAdminOrder?id=${order.id}"><button type="button" class="btn btn-info">Send Order</button></a>
					</c:if>
					<a href="deleteAdminOrder?id=${order.id}"><button type="button" class="btn btn-danger">Delete Order</button></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h2>These are all the products</h2>
	<a href="adminProductForm?id=0" style="display:inline-block;" class="padding-btm-25"><button type="button" class="btn btn-success">Add Product</button></a>
	<table class="table table-striped">
		<tr>
			<th align="left">
				Products Number
			</th>
			<th align="left">
				Product Name
			</th>
			<th align="left">
				Product description
			</th>
			<th align="left">
				Price
			</th>
			<th align="left">
				Total amount in all orders
			</th>
			<th align="left">
				Actions
			</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.price} lv</td>
				<td>${product.getTotalOrdered()}</td>
				<td>
					<a href="adminProductForm?id=${product.id}"><button type="button" class="btn btn-secondary">Edit Product</button></a>
					<c:if test="${product.getTotalOrdered() == 0}">
						<a href="deleteAdminProduct?id=${product.id}"><button type="button" class="btn btn-danger">Delete Product</button></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	</div>
</body>
</html>