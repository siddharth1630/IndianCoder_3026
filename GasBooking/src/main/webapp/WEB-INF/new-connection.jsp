<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>New Gas Connection</title>
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
<center><h1> APPLYING FOR A NEW CONNECTION</h1></center><br><br>
	
	<div class="table"><table>
		<tr >
			<td><a href="newConnection">New Connection</a></td>
			<td><a href="newBooking">Booking</a></td>
			<td><a href="allStatus">Connection Status</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	<br><br>
	
	<form:form action="status" modelAttribute="gasCon" method="POST">
		<table>
			<tr>
				<td>Email:</td>
				 <!-- session for email -->
				<td><input type="text" value="${session}" name="email" readonly style="background-color:white; color:blue;"></td>	
			</tr>
			<tr>
				<td>State:</td>
				<td><form:select path="state">
					<form:options items="${gasCon.stateOptions }"/>
				</form:select></td>
				<td><form:errors cssClass="error" path="state"></form:errors></td>
				
			</tr>
			<tr>
				<td>District:</td>
				<td><form:input path="district" /></td>
				<td><form:errors cssClass="error" path="district"></form:errors></td>
			</tr>
			<tr>
				<td>Distributor:</td>
				<td><form:input path="distributor"/></td>
				<td><form:errors cssClass="error" path="distributor"></form:errors></td>
			</tr>
				<tr>
				<td>Full Name:</td>
				<td><form:input path="fullName"/></td>
				<td><form:errors cssClass="error" path="fullName"></form:errors></td>
			</tr>
			
			<tr>
				<td>Mobile Number:</td>
				<td><form:input path="mobile"/></td>
				<td><form:errors cssClass="error" path="mobile"></form:errors></td>
			</tr>
		
			<tr>
				<td colspan="2"><input type="submit" value="Apply"></td>
			</tr>
		</table>
	</form:form>
	
	

</body>
</html>