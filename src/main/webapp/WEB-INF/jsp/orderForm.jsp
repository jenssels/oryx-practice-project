<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<title>Order Form</title>
</head>
<body>
<div class="container">
	<div class="col-md-10 offset-1">
		<c:choose>
		    <c:when test="${order.id != 0}">
				<h1>Edit order ${order.id}</h1>
		    </c:when>    
		    <c:otherwise>
				<h1>Add new Order</h1>
    		</c:otherwise>
		</c:choose>
		<p><b>${message}</b></p>
		<form:form modelAttribute="order" action="saveOrder" method="POST">
				<form:input id="id" path="id" type="hidden"/>
		<table class="table">
			<tr>
				<td>
					<label>Please select a product</label>
				</td>
				<td>
					<form:select  path="product">
    					<form:options items="${products}" itemLabel="name"></form:options>
    				</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<label>Please select a amount above 0</label>
				</td>
				<td>
					<input type="number" name="amount" value="${order.amount}"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" class="btn btn-primary" value="Save"/>
				</td>
				<td>
					<a href="/home"><button type="button" class="btn btn-secondary">Cancel</button></a>
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</div>
</body>
</html>