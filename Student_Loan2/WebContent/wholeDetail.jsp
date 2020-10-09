<!--  come from Admin.java -->
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
<title>Display whole detail</title>
</head>
<body>
	<table border="1">
	<%
		Connection con=null;				
		PreparedStatement ps=null;
		ResultSet rs=null;
		PrintWriter pw=response.getWriter();
		int roll=0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
	//		ps=con.prepareStatement("select * from enterinfo");
			ps=con.prepareStatement("select e.roll, e.college_name,e.college_address,e.home_address,e.city,e.district,e.states,e.pincode,l.loan_amount,l.tenure,l.total_balance,l.counts,l.loan_date,l.repay_date,i.status from enterinfo e , loan_detail l,INSERT_LOAN i where e.roll=l.roll and l.roll=i.roll and i.roll=e.roll");
			rs=ps.executeQuery();
	%>
			<tr><td>Roll</td><td>College Name</td><td>College Address</td><td>Home Address</td><td>City</td><td>District</td><td>State</td><td>Pin-Code</td><td>LOAN_AMOUNT</td><td>TENURE</td><td>TOTAL_BALANCE</td><td>NO OF TIMES</td><td>LOAN_DATE</td><td>REPAY_DATE</td><td>STATUS</td>
	<%
			while(rs.next()){
	%>
				<tr>
					<td><input type="text" value="<%=rs.getInt(1)%>"></td>
					<td><input type="text" value="<%=rs.getString(2)%>"></td>
					<td><input type="text" value="<%=rs.getString(3)%>"></td>
					<td><input type="text" value="<%=rs.getString(4)%>"></td>
					<td><input type="text" value="<%=rs.getString(5)%>"></td>
					<td><input type="text" value="<%=rs.getString(6)%>"></td>
					<td><input type="text" value="<%=rs.getString(7)%>"></td>
					<td><input type="text" value="<%=rs.getInt(8)%>"></td>
					<td><input type="text" value="<%=rs.getDouble(9)%>"></td>	
					<td><input type="text" value="<%=rs.getInt(10)%>"></td>
					<td><input type="text" value="<%=rs.getDouble(11)%>"></td>
					<td><input type="text" value="<%=rs.getInt(12)%>"></td>
					<td><input type="text" value="<%=rs.getDate(13)%>"></td>
					<td><input type="text" value="<%=rs.getDate(14)%>"></td>
					<td><input type="text" value="<%=rs.getString(15)%>"></td>
				</tr>	
	<%		
			}
	
		}
		catch(Exception e) {
			pw.print("unable to display emage");
		}
	%>
	</table>	
	<br><br><a href="index.html">go to index page</a>
</body>
</html>

<!--  go to approve.jsp -->