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
	<center><h2> YOUR All CONNECTION STATUS:</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="newConnection">New Connection</a></td>
			<td><a href="newBooking">Booking</a></td>
			<td><a href="allStatus">Connection Status</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	<table>
		<tr>
			<td>Consumer No.</td>
			<td>Approved Date</td>
			<td>Email</td>
			<td>Full Name</td>
			<td>State</td>
			<td>District</td>
			<td>Mobile No.</td>
			<td>Distributor</td>
			<td>Status</td>
		</tr>
		<c:forEach var="gasDetail" items="${allStatus}">
   			
 
			<tr>
				<td><c:out value="${gasDetail.consumerNo }"/></td>
				<td><c:out value="${gasDetail.approvedDate }"/></td>
				<td><c:out value="${gasDetail.email }"/></td>
				<td><c:out value="${gasDetail.fullName }"/></td>
				<td><c:out value="${gasDetail.state }"/></td>
				<td><c:out value="${gasDetail.district }"/></td>
				<td><c:out value="${gasDetail.mobile }"/></td>
				<td><c:out value="${gasDetail.distributor }"/></td>
				<td style="color: red"><c:out value="${gasDetail.status }"/></td>
			</tr>
		</c:forEach>
	
	
	</table>

</body>
</html>