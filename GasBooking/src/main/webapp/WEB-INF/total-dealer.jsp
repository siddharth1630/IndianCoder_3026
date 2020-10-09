<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Total Dealer</title>
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
	<center><h2>Total Dealer</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="totalConnection">Total Connection</a></td>
			<td><a href="totalBooking">Total Booking</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	
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
			<td>Status</td>
		</tr>
		<c:forEach var="detail" items="${dealerDetail}">
   			
 
			<tr>
				<td><c:out value="${detail.id}"/></td>
				<td><c:out value="${detail.panCard}"/></td>
				<td><c:out value="${detail.email}"/></td>
				<td><c:out value="${detail.firstName}"/></td>
				<td><c:out value="${detail.lastName}"/></td>
				<td><c:out value="${detail.gender}"/></td>
				<td><c:out value="${detail.mobile}"/></td>
				<td><c:out value="${detail.distributorArea}"/></td>
				<td style="color: red"><c:out value="${detail.status}"/></td>
				
				<c:set var="val" value="${detail.status}"/>
				<c:choose> 
					  <c:when test="${val.equals('APPROVED')}">
					    	
					  </c:when>
					  <c:otherwise>
						   	<!-- construct an "update" link with customer id -->
							<c:url var="approveStatus" value="approveDealer">
								<c:param name="id" value="${detail.id }" />
							</c:url>
							
							<td><a href="${approveStatus }"> <input type="button" value="Approve"></a></td>
					  </c:otherwise>
				</c:choose>
				
				
				<c:url var="deleteStatus" value="deleteDealer">
					<c:param name="id" value="${detail.id }" />
				</c:url>
				<td><a href="${deleteStatus }"> <input type="button" value="Delete"></a></td>
				
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>