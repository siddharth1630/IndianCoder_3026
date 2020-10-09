<!--  come from Home.java -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
		int roll=(int)request.getAttribute("roll");
		double loan_amount=(double)request.getAttribute("loan_amount");
	%>
	<h1>you didn't pay a previous loan first pay</h1><br>
	<h1>you have to pay--><%=loan_amount %> </h1>
	<form name="repay" method="post" action="repaySuccess.jsp">
		<input type="hidden" name="roll" value="<%=roll%>">
		<input type="hidden" name="loan_amount" value="<%=loan_amount %>">
		<input type="text" name="repay" required >
		<input type="submit" value="Repay">
	</form>
	
</body>
</html>

<!--  go to repaySuccess.jsp -->