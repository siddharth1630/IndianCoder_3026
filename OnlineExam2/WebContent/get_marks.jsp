<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Roll</td>
			<td>Name</td>
			<td>Marks</td>
		</tr>
		<%
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
				
				Statement statement = con.createStatement();
				String sql ="SELECT * FROM ALL_MARKS";
				ResultSet resultSet = statement.executeQuery(sql);
				
				while(resultSet.next()){
		%>
		<tr>
		<td><%=resultSet.getInt("roll") %></td>
		<td><%=resultSet.getString("name") %></td>
		<td><%=resultSet.getInt("marks") %></td>
		</tr>
		<%
				}
				con.close();
			}
			catch (Exception e) {
					e.printStackTrace();
			}	
		
		%>
	
	
	</table>
	
	
</body>
</html>