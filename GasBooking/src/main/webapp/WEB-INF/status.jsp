<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Status</title>
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
<body>
	<center><h2> YOUR CURRENT BOOKING STATUS:</h2></center>
	<div class="table"><table>
		<tr >
			<td><a href="newConnection">New Connection</a></td>
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
			<td>District</td>
			<td>Mobile No.</td>
			<td>Distributor</td>
			<td>Status</td>
		</tr>
		<tr>
			<td>${gasDetail.consumerNo }</td>
			<td>${gasDetail.approvedDate }</td>
			<td>${gasDetail.email }</td>	
			<td>${gasDetail.fullName }</td>
			<td>${gasDetail.district }</td>
			<td>${gasDetail.mobile }</td>
			<td>${gasDetail.distributor }</td>
			<td style="color: red">${gasDetail.status }</td>
		</tr>
	
	
	</table>

</body>
</html>