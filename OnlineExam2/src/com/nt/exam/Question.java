//database user-->project 	password---> project
// table register_student(roll,name)
// function Authentication(user,pass,result)
// table test_result(qno,ans)
//------------------	ABOVE QUERY IS NOT USED IN THIS FILE ----------------------------
// database table--> QUESTION (QNO,QUES,OP1,OP2,OP3,OP4,RES)	// USED IN THIS FILE

package com.nt.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Question")
public class Question extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		// read form 1 data
		String sques,sop1,sop2,sop3,sop4;
		int sqno,result;
		
		// insert form1 data into db table
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            // establish the connection
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			
			PreparedStatement ps=con.prepareStatement("INSERT INTO QUESTION VALUES(?,?,?,?,?,?,?)");
			
			sqno =Integer.parseInt(req.getParameter("qno"));
			sques=req.getParameter("ques");
			sop1=req.getParameter("op1");
			sop2=req.getParameter("op2");
			sop3=req.getParameter("op3");
			sop4=req.getParameter("op4");
			result=Integer.parseInt(req.getParameter("res"));
			
			ps.setInt(1, sqno);
			ps.setString(2, sques);
			ps.setString(3, sop1);
			ps.setString(4, sop2);
			ps.setString(5, sop3);
			ps.setString(6, sop4);
			ps.setInt(7, result);
		
			
			int suc=ps.executeUpdate();
			if(suc==1) {
				pw.println("<br><b>U r successfully inserted the ques<br><br>");
				pw.print("<a href='ques.html'>click to insert more question</a></b>");
				pw.print("<br><br><a href='index.html'>click here to go home page</a>");
			}
			else
				pw.println("<br><b>Record not inserted</b>");
		}
		catch(SQLIntegrityConstraintViolationException sicv) {
			pw.println("<b>ques number is already inserted plz insert different question number</b><br><br>");
			pw.print("<b><a href='ques.html'> click to back</b>");
			pw.print("<br><br><a href='index.html'>click here to go home page</a>");
		}
		catch(NumberFormatException nfe) {
			pw.println("<br>plz insert only number not a word");
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
