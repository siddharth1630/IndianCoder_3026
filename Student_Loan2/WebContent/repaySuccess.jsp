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
		int roll=Integer.parseInt(request.getParameter("roll"));
		double loan_amount=Double.parseDouble(request.getParameter("loan_amount"));
		double repay =Double.parseDouble(request.getParameter("repay"));
		PrintWriter pw=response.getWriter();
		
		if(repay>loan_amount){
			pw.print("<h1> you can insert greater value from loan amount<br><br>");
			pw.print("<a href='repay.jsp'> click to back</a>");
		}
		else if(repay<=0){
			pw.print("<h1> you can insert neglegible value from loan amount<br><br.");
			pw.print("<a href='repay.jsp'> click to back</a>");
		}
		else{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
				double setValue=loan_amount-repay;
				PreparedStatement ps=con.prepareStatement("UPDATE LOAN_DETAIL SET LOAN_AMOUNT=? WHERE ROLL="+roll);
				ps.setDouble(1,setValue);
				int count=ps.executeUpdate();
				
				if(count>0){
					pw.print("you can successfully pay the loan<br><br>");
					pw.print("you have left money to pay a loan is-->"+setValue);
					pw.print("<br><br><a href='login.html'> click to login again</a>");
					pw.print("<br><br><a href='index.jsp'> click to logout</a>");
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>

</body>
</html>