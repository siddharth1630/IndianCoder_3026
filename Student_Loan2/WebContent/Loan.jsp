<!--  come form enterInfo.jsp page -->
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.nt.loan.Loan" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan page</title>
</head>
<body>
			<%	
				int roll;
				PrintWriter pw = response.getWriter();
				if(com.nt.loan.Loan.signal==1){				// if it is come from request dispatcher then it will execute that block else else block
					roll=(int)request.getAttribute("roll");
					com.nt.loan.Loan.signal=0;
				}
				else
					roll=Integer.parseInt(request.getParameter("roll"));
				
				Loan loan=new Loan();
				double balance=loan.totalBalance(roll);
				int count=loan.count;
				int intrest=0;
				pw.print("<h1>you have to pay a 4% intrest * tenure</h1><br><br>");
				if(count==0){
					pw.print("you didn't take any loan");
					pw.print("<br>you are eligible  only for 1000 of loan");
					
			%> 
					<form name="loan" method="post" action="loanCalculation.jsp"><br>
									<input type="hidden" name="roll" value="<%=roll%>">
									<input type="hidden" name="totalBalance" value="<%=balance%>">
									<input type="hidden" name="count" value="<%=count%>">
					Enter loan amount:<input type="text" name="loanAmount" required><br>
					Enter Tenure:<br><input type="radio" name="tenure" value="2" required>2 months<br>
									 <input type="radio" name="tenure" value="4" required>4 months<br><br>
					 <input type="submit" value="submit">		</form>
			<%
				}
				else{
					pw.print("you can take a total loan is-->"+balance);
					if(count>0 && count <= 2)
						pw.print("<br>you are eligible  only for 1000 of loan");
					else if(count>2 && count <= 5)
						pw.print("<br>you are eligible  only for 3000 of loan");
					else if(count>5 && count <= 7)
						pw.print("<br>you are eligible  only for 5000 of loan");
					else
						pw.print("<br>you are eligible  only for 8000 of loan");
				
			%> 
					<form name="againloan" method="post" action="loanCalculation.jsp"><br>
									<input type="hidden" name="roll" value="<%=roll%>">
									<input type="hidden" name="totalBalance" value="<%=balance%>">
									<input type="hidden" name="count" value="<%=count%>">
					Enter loan amount:<input type="text" name="loanAmount" required><br>
					Enter Tenure:<br><input type="radio" name="tenure" value="2" required>2 months<br>
									 <input type="radio" name="tenure" value="4" required>4 months<br><br>
					 <input type="submit" value="submit">	</form>
			<%
				}
			%>
			
</body>
</html>

<!--  go to Loan.java page and come back by getting a result	then
	  go to loanCalculation.jsp updating the value


 -->
 
 