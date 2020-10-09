<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>New Connection Detail</title>
	<style>
			table {
		        border-collapse: separate;
		        border-spacing: 0 15px;
		      }
		      th {
		        background-color: #4287f5;
		        color: white;
		      }
			 h1 {
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
<body>
	<center><h1>ALL CONNECTION DETAILS</h1></center><br><br>

	<div class="table"><table>
		<tr >
			<td><a href="dealerBack"> New Booking</a></td>
			<td><a href="home">Sign Out</a></td>	
		</tr>
	</table>
	</div>
	
	<table>
		<tr>
			<td>Consumer No.</td>
			<td>Email</td>
			<td>Full Name</td>
			<td>State</td>
			<td>District</td>
			<td>Mobile No.</td>
			<td>Distributor</td>
			<td>Approved Date</td>
			<td>Status</td>
		</tr>
		
		<c:forEach var="gasDetail" items="${gasDetail}">
   			
 
			<tr>
				<td><c:out value="${gasDetail.consumerNo }"/></td>
				<td><c:out value="${gasDetail.email }"/></td>
				<td><c:out value="${gasDetail.fullName }"/></td>
				<td><c:out value="${gasDetail.state }"/></td>
				<td><c:out value="${gasDetail.district }"/></td>
				<td><c:out value="${gasDetail.mobile }"/></td>
				<td><c:out value="${gasDetail.distributor }"/></td>
				<td><c:out value="${gasDetail.approvedDate }"/></td>
				<td><c:out value="${gasDetail.status }"/></td>

			
				<c:set var="val" value="${gasDetail.status}"/>
				<c:choose> 
					  <c:when test="${val.equals('APPROVED')}">
					    	
					  </c:when>
					  <c:when test="${val.equals('REJECTED')}">
					    	
					  </c:when>
					  <c:otherwise>
						   	<!-- construct an "update" link with customer id -->
							<c:url var="approveStatus" value="approveGas">
								<c:param name="id" value="${gasDetail.id }" />
							</c:url>
							
							<td><a href="${approveStatus }"> <input type="button" value="Approve"></a></td>
					  </c:otherwise>
				</c:choose>
				
				<c:set var="val" value="${gasDetail.status}"/>
				<c:choose> 
					  <c:when test="${val.equals('REJECTED')}">
					    	
					  </c:when>
					  <c:when test="${val.equals('APPROVED')}">
					    	
					  </c:when>
					  <c:otherwise>
						   	<!-- construct an "update" link with customer id -->
							<c:url var="rejectStatus" value="rejectGas">
								<c:param name="id" value="${gasDetail.id }" />
							</c:url>
							
							<td><a href="${rejectStatus }"> <input type="button" value="Reject"></a></td>
					  </c:otherwise>
				</c:choose>
				
				
			</tr>
		</c:forEach>
	
	</table>
	

</body>
</html>