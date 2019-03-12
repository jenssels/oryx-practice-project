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
<title>Welcome</title>
</head>
<body>
<div class="container">
	<div class="col-md-10 offset-1">
		<h1>Hello ${name}</h1>
		<p>Please follow the link below to login</p>
		<a href="/loginForm?name=${name}" class="btn btn-info">Please login</a>
	</div>
</div>
</body>
</html>