// database user-->project 	password---> project
// function Authentication(user,pass,result)
// table test_result(qno,ans)
// database table--> QUESTION (QNO,QUES,OP1,OP2,OP3,OP4,RES)
//---------------------- ABOVE QUERY IS NOT USED IN THIS FILE --------------------------
// table register_student(roll,name)		// USED IN THIS FILE

package com.nt.exam;

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
		String proll =req.getParameter("troll");
		
		// insert form1 data into db table
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            // establish the connection
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			
			PreparedStatement ps=con.prepareStatement("insert into register_student values(?,?)");
			ps.setString(1, proll);
			ps.setString(2, pname);
			
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
		catch(Exception e) {
			e.printStackTrace();
		}
		
		pw.close();	
	}//doGet
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}//class
