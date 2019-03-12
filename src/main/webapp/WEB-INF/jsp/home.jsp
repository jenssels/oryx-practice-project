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
<title>Home</title>
</head>
<body>
<div class="container">
	<div class="col-md-11 offset-1">
		<h1>Hello ${user.name}</h1>
	<h2>These are your current orders</h2>
	<a href="orderForm?id=0" style="display:inline-block;" class="padding-btm-25"><button type="button" class="btn btn-success">Add Order</button></a>
	<c:if test="${user.role == 'Admin'}"><a href="adminHome" style="display:inline-block;" class="padding-btm-25"><button type="button" class="btn btn-info">Admin Page</button></a></c:if>
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
				<td>${order.status}</td>
				<td>
					<c:if test="${order.status == 'Pending'}">
						<a href="payOrder?id=${order.id}"><button type="button" class="btn btn-info">Pay</button></a>
					</c:if>
					<c:if test="${order.status == 'Transit'}">
						<a href="receivedOrder?id=${order.id}"><button type="button" class="btn btn-info">Received</button></a>
					</c:if>
					<c:if test="${order.status == 'Pending'}">
						<a href="orderForm?id=${order.id}"><button type="button" class="btn btn-secondary">Edit</button></a>
					</c:if>
					<c:if test="${order.status == 'Pending'}">
						<a href="deleteOrder?id=${order.id}"><button type="button" class="btn btn-danger">Delete</button></a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form action="/logout" method="post">
           <input type="submit" class="btn btn-info" value="Sign Out"/>
    </form>
	</div>
</div>
</body>
</html>