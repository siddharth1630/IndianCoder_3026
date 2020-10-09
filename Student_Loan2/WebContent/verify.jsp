<!--  come from Admin.java -->
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
<title>Insert title here</title>
</head>
<body>
	<%
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter pw=response.getWriter();
		int roll=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			
			ps=con.prepareStatement("select roll,counts from insert_loan where status='PENDING'");
			rs=ps.executeQuery();
			while(rs.next()){
	%>
				<form name="veri" method="post" action="particularVerify.jsp">
					<input type='radio' name='roll' value="<%=rs.getInt(1)%>">
					<input type='radio' name='count' value="<%=rs.getInt(2)%>">
	<%			pw.print(rs.getInt(1));
		//		pw.print(rs.getInt(2));
				pw.print("<br>");
			}
	%>
					<br><br><input type="submit" value="CONTINUE">
				</form>
	<%
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	%>
</body>
</html>

<!--  go to paritcularVerify.jsp -->