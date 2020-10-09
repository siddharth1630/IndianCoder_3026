<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sign in</title>
	<style>
		.table{
			padding: 0 155px;
		}
		.error{
			color:red;
		}
	</style>
</head>
<body>
	<center><h1> ONLINE GAS BOOKING SYSTEM</h1></center><br><br>
	
	<div class="table"><table>
		<tr >
			<td><a href="home">Home</a></td>
			<td><a href="dealerLogin">Dealer Login</a></td>
			<td>Contact</td>	
		</tr>
	</table>
	</div><br><br>
	
	<h4 style="color:red">${success}</h4>
	
	
 	<form:form action="loggedIn" modelAttribute="customer" method="POST">
 		<table>
 			<tr>
 				<td>Email:</td>
 				<td><form:input path="email"/></td>
 				<td><form:errors cssClass="error" path="email"></form:errors></td>
 			</tr>
 			<tr>
 				<td>Password:</td>
 				<td><form:password path="password"/></td>
 				<td><form:errors cssClass="error" path="password"></form:errors></td>
 			</tr>
			<tr>
				<td><input type="submit" value="SIGN IN"></td>
			</tr>
		</table>
	</form:form><br><br> 
	
	<a href="register">New user/Sign up here</a>
	
	
	

</body>
</html>