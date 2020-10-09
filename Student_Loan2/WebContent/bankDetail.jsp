<!--  come from loanCalculation.jsp -->
<%@page import="java.io.PrintWriter"%>
<%@page import="com.nt.loan.Loan"%>
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
		int roll=(Integer)request.getAttribute("roll");
		int count=(Integer)request.getAttribute("count");
		double loanAmount=(Double)request.getAttribute("loanAmount");
		double intrest=(Double)request.getAttribute("intrest");
		PrintWriter pw=response.getWriter();
		Loan loan=new Loan();
//		int flag=loan.bankDetail(roll);
//		if(flag==0){
			pw.print("<h1> you have to pay with intrest is-->"+intrest+"</h1>");
			pw.print("your loan is in pending state");
			pw.print("<br>plz fill all the detail and wait for 24 hr for approval");
			
	%> 
			<form name="bankDetail" method="post" action="InsertBankDetail.jsp"><br>
							<input type="hidden" name="roll" value="<%=roll%>">
							<input type="hidden" name="loanAmount" value="<%=loanAmount%>">
							<input type="hidden" name="count" value="<%=count%>">
			Enter bank acount number:<input type="text" name="account" required><br><br>
			Enter cif number:<input type="text" name="cif" required><br>
							 
			 <input type="submit" value="submit"></form>
	<%
//		}
//		else{
//			System.out.println("you are already inserted bank detail");
//		}
	%>

	
	
</body>
</html>

<!--  go to insertBankDetail.jsp -->