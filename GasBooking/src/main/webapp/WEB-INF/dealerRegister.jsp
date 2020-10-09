<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Register page</title>
	<style>
			.table{
				padding: 0 155px;
			}
			.error{
				color: red;
			}
	</style>
</head>
<body>

	<center><h1> ONLINE GAS BOOKING SYSTEM</h1></center><br><br>
	
	<div class="table"><table>
		<tr >
			<td><a href="home">Home</a></td>
			<td><a href="dealerLogin">Dealer Login</a></td>
		</tr>
	</table>
	</div><br><br>
	
	<h3 style="color:red">${success}</h3>
	
	<form:form action="dealerRegistered" modelAttribute="dealer" method="POST">
		<table>
			<tr>
				<td>PAN Card No. :</td>
				<td><form:input path="panCard"/></td>
				<td><form:errors cssClass="error" path="panCard"></form:errors></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName"/></td>
				<td><form:errors cssClass="error" path="firstName"></form:errors></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName"/></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><form:radiobutton path="gender" value="Male"/>MALE</td>
				<td><form:radiobutton path="gender" value="Female"/>FEMALE</td>
				<td><form:errors cssClass="error" path="gender"></form:errors></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email"/></td>
				<td><form:errors cssClass="error" path="email"></form:errors></td>
			</tr>
			<tr>
				<td>Mobile Number:</td>
				<td><form:input path="mobile"/></td>
				<td><form:errors cssClass="error" path="mobile"></form:errors></td>
			</tr>
			<tr>
				<td>Distributing Area:</td>
				<td><form:input path="distributorArea"/></td>
				<td><form:errors cssClass="error" path="distributorArea"></form:errors></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password"/></td>
				<td><form:errors cssClass="error" path="password"></form:errors></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><form:password path="cPassword"/></td>
				<td><form:errors cssClass="error" path="cPassword"></form:errors></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="REGISTER"></td>
			</tr>
		</table>
	</form:form>
	
	
	
</body>
</html>