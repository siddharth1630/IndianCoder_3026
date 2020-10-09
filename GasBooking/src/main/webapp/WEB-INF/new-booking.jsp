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
<center><h1> APPLY FOR A GAS BOOKING</h1></center><br><br>
	
	<div class="table"><table>
		<tr >
			<td><a href="newConnection">New Connection</a></td>
			<td><a href="newBooking">Booking</a></td>
			<td><a href="allBookingStatus">Booking Status</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	<br><br>
	
	<form:form action="bookingPage" modelAttribute="gasBook" method="POST">
	<h1>${success}</h1>		<!-- HERE WE USE FLASH ATTRIBUTE  -->
	
		<input type="hidden" name="email" value="${session }"/>
		<table>
			
			<tr>
				<td>Consumer No:</td>
				<td><form:select path="consumerNo">
						<form:options items="${gasBook.consumerNos }"/>
					</form:select></td>
				<td><form:errors cssClass="error" path="consumerNo"></form:errors></td>
				
			</tr>
			
			<tr>
				<td>Mobile Number:</td>
				<td><form:input path="mobile"/></td>
				<td><form:errors cssClass="error" path="mobile"></form:errors></td>
			</tr>
			
			<tr>
				<td>Address:</td>
				<td><form:textarea path="address"/></td>
				<td><form:errors cssClass="error" path="address"></form:errors></td>
			</tr>
			
		
			<tr>
				<td colspan="2"><input type="submit" value="Apply"></td>
			</tr>
		</table>
	</form:form>
	
	

</body>
</html>