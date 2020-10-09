<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Total Booking</title>
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
	<center><h2>Total Booking</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="totalDealer">Total Dealer</a></td>
			<td><a href="totalConnection">Total Connection</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	
	<table>
		<tr>
			<td>Id</td>
			<td>Consumer No.</td>
			<td>Email</td>
			<td>Mobile No.</td>
			<td>Booking Date</td>
			<td>Delivery Date</td>
			<td>Address</td>
			<td>Status</td>
		</tr>
		<c:forEach var="detail" items="${bookingDetail}">
   			
 
			<tr>
				<td><c:out value="${detail.id}"/></td>
				<td><c:out value="${detail.consumerNo}"/></td>
				<td><c:out value="${detail.email}"/></td>
				<td><c:out value="${detail.mobile}"/></td>
				<td><c:out value="${detail.bDate}"/></td>
				<td><c:out value="${detail.deliveryDate}"/></td>
				<td><c:out value="${detail.address}"/></td>
				<td style="color: red"><c:out value="${detail.bookingStatus}"/></td>
				
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>