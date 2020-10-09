// COME FROM ADMIN.HTML
package com.nt.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		String id;
		String pass;
		PrintWriter pw=res.getWriter();
		
		// taking username and password from html page 
		id=req.getParameter("username");
		pass=req.getParameter("password");
		
		if(id.equals("sid") && pass.contentEquals("gupta")) {
        	pw.print("<body bgcolor='#E6E6FA'></body>");  
        	pw.print("<br><b>Welcom to admin page");
        	pw.print("<br><br>click on verify to verify loan request  ");
        	pw.print("<a href='verify.jsp'><button style='width:80px; height:30px;' >VERIFY</button></a></h1>");
        	
        	// show all the student marks 
        	pw.print("<a href='wholeDetail.jsp'><button style='width:80px; height:50px;' >WHOLE DETAIL</button></a></h1>");
        	
        	pw.print("<br><br><a href='index.html'>Return to home page..........</a>");
        }
		else {
			pw.println("<font color=red size=7> your id and password is wrong</font>");	
			pw.print("<br><br><a href='admin.html'>click here to back..........</a>");
		}
		pw.close();	
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}//class

// go to verify.jsp
