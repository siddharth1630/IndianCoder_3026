<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>All Pending booking req.</title>
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
	<center><h2> YOUR All PENDING BOOKING STATUS:</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="bookingBack">New Connection</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	
	<h3>${success }</h3>
	
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
		<c:forEach var="bStatus" items="${pendingReq}">
   			
 
			<tr>
				<td><c:out value="${bStatus.consumerNo}"/></td>
				<td><c:out value="${bStatus.email}"/></td>
				 <td><c:out value="${bStatus.bDate}"/></td>
				<td><c:out value="${bStatus.deliveryDate}"/></td>
				<td><c:out value="${bStatus.mobile}"/></td>
				<td><c:out value="${bStatus.address}"/></td>
				<td style="color: red"><c:out value="${bStatus.bookingStatus}"/></td>
				
				<c:set var="val" value="${bStatus.bookingStatus}"/>
				<c:choose> 
					  <c:when test="${val.equals('APPROVED')}">
					    	
					  </c:when>
					  
					  <c:otherwise>
						   	<!-- construct an "update" link with customer id -->
							<c:url var="approveStatus" value="approveBookingGas">
								<c:param name="id" value="${bStatus.id }" />
							</c:url>
							
							<td><a href="${approveStatus }"> <input type="button" value="Approve"></a></td>
					  </c:otherwise>
				</c:choose>
				
			<c:set var="val" value="${bStatus.bookingStatus}"/>
				<c:choose> 
					  <c:when test="${val.equals('REJECTED')}">
					    	
					  </c:when>
					  <c:otherwise>
						   	<!-- construct an "update" link with customer id -->
							<c:url var="rejectStatus" value="rejectBookingGas">
								<c:param name="id" value="${bStatus.id }" />
							</c:url>
							
							<td><a href="${rejectStatus }"> <input type="button" value="Reject"></a></td>
					  </c:otherwise>
				</c:choose>
				
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>