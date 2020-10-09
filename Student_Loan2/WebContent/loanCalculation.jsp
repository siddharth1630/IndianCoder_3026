<!--  come form loan.jsp -->

<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.nt.loan.Loan" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	PrintWriter pw=response.getWriter();
	RequestDispatcher rd=null;
	int roll=Integer.parseInt(request.getParameter("roll"));
	double intrest=0;
	double loanAmount=Double.parseDouble(request.getParameter("loanAmount"));
	int tenure=Integer.parseInt(request.getParameter("tenure"));
	double totalBalance=Double.parseDouble(request.getParameter("totalBalance"));
	int count=Integer.parseInt(request.getParameter("count"));
	
	if(count<3 && loanAmount>1000){
		pw.print("<h1>plz insert value less than 1000");
	}
	else if(count>2 && count <= 5 && loanAmount>3000)
		pw.print("<h1>plz insert value less than 3000");
	else if(count>5 && count <= 7 && loanAmount>5000)
		pw.print("<h1>plz insert value less than 5000");
	else if(count>7 && loanAmount > 8000)
		pw.print("<h1>plz insert value less than 7000");
	else{
		Loan loan=new Loan();
		intrest=loan.intrestCalculator(loanAmount,tenure);
		int get=loan.takeLoan(roll,loanAmount,tenure,totalBalance,count,intrest);
		
		request.setAttribute("roll",roll);
		request.setAttribute("loanAmount",loanAmount);
		request.setAttribute("intrest",intrest);
		request.setAttribute("count",count);
		rd=request.getRequestDispatcher("bankDetail.jsp");
    	rd.forward(request,response);
	}
%>
</body>
</html>

<!--  go to Loan.java 
		go to bankDetail.jsp
-->
