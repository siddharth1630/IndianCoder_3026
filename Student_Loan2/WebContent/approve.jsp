<!--  particularVerify.jsp -->
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
		Connection con=null;				
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter pw=response.getWriter();
		int roll=Integer.parseInt(request.getParameter("roll"));
		int flag=Integer.parseInt(request.getParameter("flag"));
		int count=Integer.parseInt(request.getParameter("count"));
		
		if(flag>0){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
				
				ps=con.prepareStatement("UPDATE INSERT_LOAN SET STATUS=? WHERE ROLL=? and counts=?");
				ps.setString(1,"ACCEPTED");
				ps.setInt(2,roll);
				ps.setInt(3,count);
				ps.executeUpdate();
				
				pw.print("<h1> succefully approve now msg will send to the borrower");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
				
				ps=con.prepareStatement("UPDATE INSERT_LOAN SET STATUS=? WHERE ROLL=? and counts=?");
				ps.setString(1,"REJECTED");
				ps.setInt(2,roll);
				ps.setInt(3,count);
				ps.executeUpdate();
				
				pw.print("<h1> Application rejected now msg will send to the borrower");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
%>
	<a href="index.html">go to index page</a>
</body>
</html>