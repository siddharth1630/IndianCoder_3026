<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Total Connection</title>
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
	<center><h2>Total Connection</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="totalDealer">Total Dealer</a></td>
			<td><a href="totalBooking">Total Booking</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	
	<h4 style="color:red">${success }</h4>
	
	<table>
		<tr>
			<td>Id</td>
			<td>Pan Card</td>
			<td>Email</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Gender</td>
			<td>Mobile No.</td>
			<td>Distributor Area</td>
			<td>Approved Date</td>
			<td>Status</td>
		</tr>
		<c:forEach var="detail" items="${connectionDetail}">
   			
 
			<tr>
				<td><c:out value="${detail.id}"/></td>
				<td><c:out value="${detail.consumerNo}"/></td>
				<td><c:out value="${detail.email}"/></td>
				<td><c:out value="${detail.fullName}"/></td>
				<td><c:out value="${detail.mobile}"/></td>
				<td><c:out value="${detail.state}"/></td>
				<td><c:out value="${detail.district}"/></td>
				<td><c:out value="${detail.distributor}"/></td>
				<td><c:out value="${detail.approvedDate}"/></td>
				<td style="color: red"><c:out value="${detail.status}"/></td>
				
				
				<c:url var="deleteConnection" value="deleteConnection">
					<c:param name="id" value="${detail.id }" />
				</c:url>
				<td><a href="${deleteConnection }"> <input type="button" value="Delete"></a></td>
				
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>