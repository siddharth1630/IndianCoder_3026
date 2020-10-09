<!--  come from enterinfor.jsp page -->
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.io.FileInputStream" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>enter info</title>
</head>
<body>
	
		<%
			int pincode,roll;
			String cname,caddrs,haddrs,city,district,state,file;
			String name=null;
			FileInputStream fis=null;
			PrintWriter pw=response.getWriter();
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");

				PreparedStatement ps=con.prepareStatement("INSERT INTO enterInfo VALUES(?,?,?,?,?,?,?,?,?)");
		%>
		
		
		<%		// this page come from enterinfor.jsp
				roll=Integer.parseInt(request.getParameter("roll"));
				cname=(String)request.getParameter("cname");
				caddrs=(String)request.getParameter("caddrs");
				haddrs=(String)request.getParameter("haddrs");
				city=(String)request.getParameter("city");
				district=(String)request.getParameter("district");
				state=(String)request.getParameter("state");
				pincode=Integer.parseInt(request.getParameter("pincode"));
				file=(String)request.getParameter("file");
				
				File image= new File(file);
				fis=new FileInputStream(image);
				
				ps.setInt(1, roll);
				ps.setString(2, cname);
				ps.setString(3, caddrs);
				ps.setString(4, haddrs);
				ps.setString(5, city);
				ps.setString(6, district);
				ps.setString(7,state);
				ps.setInt(8, pincode);
				ps.setBlob(9, fis);

				int count = ps.executeUpdate();
				if(count>0)
				{
			%>
					<h1> succfully inserted a record now u take a loan<br> click on continue</h1><br><br>
					<form name="info" method="post" action="Loan.jsp">
						<input type="hidden" name="roll" value="<%=roll%>">
						<input type="submit" value="CONTINUE">
					</form>
			<%
				}
				else
				{
					out.println("not successfully");
				}
				con.close();
		
			}
			catch(NumberFormatException nfe){
				pw.print("plz enter your pincode in digit");
			}
			catch(FileNotFoundException fnfe){
				pw.print("plz insert your addhar pic");
			}
			catch (Exception e) {
					e.printStackTrace();
			}	
		%>
	
	
</body>
</html>

<!--  go to Loan.jsp page -->