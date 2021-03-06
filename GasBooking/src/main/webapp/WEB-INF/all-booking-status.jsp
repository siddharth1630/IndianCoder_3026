<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>All Status</title>
	<style>
			table {
		        border-collapse: separate;
		        border-spacing: 0 15px;
		      }
		      th {
		        background-color: #4287f5;
		        color: white;
		      }
			 h2 {
		        color: #4287f5;
		      }
			.table{
				padding: 0 155px;			
			}
			 th, td {
				        width: 150px;
				        text-align: center;
				        border: 1px solid black;
				        padding: 5px;
			      }

	</style>
</head>
</head>
<body>
	<center><h2> YOUR All BOOKING STATUS:</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="newConnection">New Connection</a></td>
			<td><a href="newBooking">Booking</a></td>
			<td><a href="allBookingStatus">Booking Status</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	<table>
		<tr>
			<td>Consumer No.</td>
			<td>Email</td>
			<td>Booking Date</td>
			<td>Delivery Date</td>
			<td>Mobile No.</td>
			<td>Address</td>
			<td>Booking Status</td>
		</tr>
		<c:forEach var="bStatus" items="${allBookingStatus}">
   			
 
			<tr>
				<td><c:out value="${bStatus.consumerNo}"/></td>
				<td><c:out value="${bStatus.email}"/></td>
				<td><c:out value="${bStatus.bDate}"/></td> 
				<td><c:out value="${bStatus.deliveryDate}"/></td> 
				<td><c:out value="${bStatus.mobile}"/></td>
				<td><c:out value="${bStatus.address}"/></td>
				<td style="color: red"><c:out value="${bStatus.bookingStatus}"/></td>
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>