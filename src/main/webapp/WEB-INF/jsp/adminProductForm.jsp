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
<title>Product Form</title>
</head>
<body>
<div class="container">
	<div class="col-md-10 offset-1">
		<c:choose>
		    <c:when test="${product.id != 0}">
				<h1>Edit Product ${product.id}</h1>
		    </c:when>    
		    <c:otherwise>
				<h1>Add new Product</h1>
    		</c:otherwise>
		</c:choose>
		<p><b>${message}</b></p>
		<form:form modelAttribute="product" action="adminSaveProduct" method="POST">
				<form:input id="id" path="id" type="hidden"/>
		<table class="table">
			<tr>
				<td>
					<label>Please type a name</label>
				</td>
				<td>
					<input type="text" name="name" value="${product.name}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>Please describe the product</label>
				</td>
				<td>
					<input type="text" name="description" value="${product.description}"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>Please select a price above 0</label>
				</td>
				<td>
					<input type="number" step="0.01" min=0 name="price" value="${product.price}"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" class="btn btn-primary" value="Save"/>
				</td>
				<td>
					<a href="/adminHome"><button type="button" class="btn btn-secondary">Cancel</button></a>
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</div>
</body>
</html>