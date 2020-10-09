<!--  come from verify.jsp -->
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display student detail</title>
</head>
<body>
	<table border="1">
	<%
		Connection con=null;				
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter pw=response.getWriter();
		int roll=0,count=0;
		
		Blob img;
		byte[] imgData=null;
		
		try {
			roll=Integer.parseInt(request.getParameter("roll"));
			count=Integer.parseInt(request.getParameter("count"));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			ps=con.prepareStatement("select * from enterinfo where roll="+roll);
			rs=ps.executeQuery();
			if(rs.next()){
				img=rs.getBlob(9);
				imgData =img.getBytes(9,(int)img.length());
	%>
				<tr>
				<tr><td>Roll</td><td><input type="text" value="<%=rs.getInt(1)%>"></td></tr>
				<tr><td>College Name</td><td><input type="text" value="<%=rs.getString(2)%>"></td></tr>
				<tr><td>College Address</td><td><input type="text" value="<%=rs.getString(3)%>"></td></tr>
				<tr><td>Home Address</td><td><input type="text" value="<%=rs.getString(4)%>"></td></tr>
				<tr><td>City</td><td><input type="text" value="<%=rs.getString(5)%>"></td></tr>
				<tr><td>District</td><td><input type="text" value="<%=rs.getString(6)%>"></td></tr>
				<tr><td>State</td><td><input type="text" value="<%=rs.getString(7)%>"></td></tr>
				<tr><td>Pin-Code</td><td><input type="text" value="<%=rs.getInt(8)%>"></td></tr>
			
				
	<%		
			}
	
		}
		catch(Exception e) {
			pw.print("unable to display image");
		}
	
	%>
	</table>
	<image src="imageServlet?id=<%=rs.getBlob(9) %>"/>
	<form name="approve" method="post" action="approve.jsp">
		<input type="hidden" name="roll" value="<%=rs.getInt(1)%>">
		<input type="hidden" name="flag" value="1">
		<input type="hidden" name="count" value="<%=count%>">
	<br><input type="submit" value="APPROVE"></form>
	<form name="reject" method="post" action="approve.jsp">
		<input type="hidden" name="flag" value="0">
		<input type="hidden" name="roll" value="<%=rs.getInt(1)%>">
		<input type="hidden" name="count" value="<%=count%>">
	<br><input type="submit" value="REJECT"></form>
	
</body>
</html>

<!--  go to approve.jsp -->