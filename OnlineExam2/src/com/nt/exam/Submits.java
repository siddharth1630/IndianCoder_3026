// database user-->project 	password---> project	
// table test_result(ROLL,qno,ans)
// database table--> QUESTION (QNO,QUES,OP1,OP2,OP3,OP4,RES)
// TABLE ALL_MARKS(ROLL,NAME,MARKS)
// -------------------- ABOVE QUERY IS USED IN THIS JAVA FILE  ---------------------
// table register_student(roll,name)		// NOT USED IN THIS FILE
// function Authentication(user,pass,result)	// NOT USED IN THIS JAVA FILE

package com.nt.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Submits extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            // establish the connection
             con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			 ps=con.prepareStatement("insert into test_result values(?,?,?)");
			
			Test.ans.add(Integer.parseInt(req.getParameter("select")));
		}
		 catch(NumberFormatException nfe) {
			  Test.ans.add(Integer.parseInt(req.getParameter("default")));
		  }
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			for(int i=0;i<Test.fqno.size();i++) {
				ps.setInt(1, Login.roll);
				ps.setInt(2, Test.fqno.get(i));
				ps.setInt(3, Test.ans.get(i));
				ps.addBatch();
			}
			
			ps.executeBatch();
			int result=1;
			if(result==1) {
				pw.println("<br><b>U r successfully completed your test<br>");
				pw.print("<a href='index.html'>click to back</b></a>");
			}
			else
				pw.println("<br><b>Record not inserted</b>");
			
			// PREPARING THE RESULT
			PreparedStatement ps1=con.prepareStatement("select count(*) from test_result t,question q where q.qno=t.qno and t.ans=q.ans and t.roll="+Login.roll);
			ResultSet rs=ps1.executeQuery();
			rs.next();
			
			int marks=rs.getInt(1);
			pw.print("<br><br><b>Name--> "+Login.name);
			pw.print("<br><b>Roll-->"+Login.roll);
			pw.print("<br><br><b>you get "+marks+" out of "+Test.fqno.size());
			
			// INSERTING THE NAME ROLL AND MARKS OF THE STUDENT IN THE DB
			PreparedStatement ps2=con.prepareStatement("INSERT INTO ALL_MARKS VALUES(?,?,?)");
			ps2.setInt(1, Login.roll);
			ps2.setString(2,Login.name);
			ps2.setInt(3, marks);
			ps2.executeQuery();
			Test.counter=-1;
			Test.qno.clear();
			Test.ques.clear();
			Test.op1.clear();
			Test.op1.clear();
			Test.op1.clear();
			Test.op1.clear();
			Test.fqno.clear();
			Test.ans.clear();
			
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
