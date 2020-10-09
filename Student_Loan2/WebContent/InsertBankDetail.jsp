<!--  come form bankDetail.jsp -->

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
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
	PrintWriter pw=response.getWriter();
	PreparedStatement ps=null;
	Connection con=null;
//	RequestDispatcher rd=null;
	int roll=Integer.parseInt(request.getParameter("roll"));
	int accountNumber=Integer.parseInt(request.getParameter("account"));
	int cifNumber=Integer.parseInt(request.getParameter("cif"));
	int count=Integer.parseInt(request.getParameter("count"));
	double loanAmount=Double.parseDouble(request.getParameter("loanAmount"));
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
		ps=con.prepareStatement("INSERT INTO Insert_loan(roll,account_no,cif_no,loan_amount,counts) VALUES(?,?,?,?,?)");
		ps.setInt(1, roll);
		ps.setInt(2, accountNumber);
		ps.setInt(3, cifNumber);
		ps.setDouble(4,loanAmount);
		ps.setInt(5,count);
		

		int counts = ps.executeUpdate();
		if(counts>0){
			pw.print("succfully go for loan approval");
			pw.print("<a href='index.html'>LOG OUT</a>");
		}
		else{
			pw.print("something get wrong");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	 %>
</body>
</html>