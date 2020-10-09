package com.nt.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		// read form 1 data
		String pname =req.getParameter("tname");
		int proll=0;
		try {
			 proll =Integer.parseInt(req.getParameter("troll"));
		}
		catch(NumberFormatException nfe) {
			pw.println("plz enter your roll no.");
		}
		String email =req.getParameter("temail");
		String password =req.getParameter("tpassword");
		String cpassword=req.getParameter("tcpassword");
		
		// insert form1 data into db table
		if(password.equals(cpassword)){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
	            // establish the connection
	            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
				
				PreparedStatement ps=con.prepareStatement("insert into sl_register_student values(?,?,?,?)");
				ps.setInt(2, proll);
				ps.setString(1, pname);
				ps.setString(3, email);
				ps.setString(4, password);
				
				int result=ps.executeUpdate();
				if(result==1) {
					pw.println("<br><b>U r successfully register<br>");
					pw.print("<a href='index.html'>click to back</b>");
				}
				else
					pw.println("<br><b>Record not inserted</b>");
			}
			catch(SQLIntegrityConstraintViolationException sicv) {
				pw.println("<b>roll number is already register</b><br><br>");
				pw.print("<b><a href='index.html'> click to back</b>");
			}
			catch(NumberFormatException nfe) {
				pw.println("plz enter your roll no");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			pw.print("password does'nt match or fill the whole field");
			pw.print("<b><a href='index.html'> click to back</b>");
		}
		
		pw.close();	
	}//doGet
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}
