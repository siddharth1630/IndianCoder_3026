<!--  come form Home.java page -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>enter information</title>
</head>
<body>
<%
			int roll=(int)request.getAttribute("roll");
%>
	<h1> you have to fill the box for taking a loan</h1><br>
	<form name="info" method="post" action="enterInfo.jsp">
			<input type="hidden" name="roll" value="<%=roll%>">
			name:<input type='text' name='cname' required><br><br>
			college address:<input type='text' name='caddrs' required><br><br>
			home address:<input type='text' name='haddrs' required><br><br>
			city:<input type='text' name='city' required><br><br>
			district:<input type='text' name='district' required><br><br>
			state:<input type='text' name='state' required><br><br>
			pincode:<input type='text' name='pincode' required><br><br>
			front adhar pic:<input type='file' name='file' required><br><br>
			<input type="submit" value="SUBMIT">
		</form>
</body>
</html>

<!--  go to enterInfo.jsp page -->