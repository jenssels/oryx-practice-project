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
<title>Log in</title>
</head>
<body>
<div class="container">
	<div class="col-md-10 offset-1">
		<h2>Welcome ${name}. Please log in</h2>
		<p><b>${message}</b></p>
        <form action="/login" method="post">
        	<table>
        		<tr>
        			<td>
        				<label> Mail: </label> 
        			</td>
        			<td>
        				<input type="text" name="mail"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<label> Password: </label>
        			</td>
        			<td>
        				<input type="password" name="password"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<input type="submit" class="btn btn-primary" value="Log In"/>
        			</td>
        			<td>
        				<a href="/registerForm" class="btn btn-info">Sign Up</a>
        			</td>
        		</tr>
        	</table>
        </form>
	</div>
</div>
</body>
</html>