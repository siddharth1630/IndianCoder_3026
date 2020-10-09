// database user-->project 	password---> project	
// table register_student(roll,name)
// function Authentication(user,pass,result)
//---------------------- ABOVE QUERY IS USED IN THIS JAVA FILE --------------------------
// database table--> QUESTION (QNO,QUES,OP1,OP2,OP3,OP4,RES)		// NOT USE IN THIS FILE
// table test_result(qno,ans)			// NOT USED IN THIS FILE


package com.nt.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{
	static int roll;
	static String name;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		String result=null;
		CallableStatement cs=null;
		PreparedStatement ps=null;
		Connection con=null;
		PrintWriter pw=res.getWriter();
		
		try {
			// taking username and password from html page 
			roll=Integer.parseInt(req.getParameter("j_password"));
			name=req.getParameter("j_username");
			
			 // register jdbc driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // establish the connection
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			
			// create callableStatement object
            if(con!=null) {
            	cs=con.prepareCall("{ call authentication(?,?,?)}");
            }
            if(cs!=null) {
            	// register out param 
            	cs.registerOutParameter(3, Types.VARCHAR);	// 3 becz we get 3rd value in pl/sql function and varchar becz its types is varchar
            	// set in param
            	cs.setString(1, name);
            	cs.setInt(2, roll);
            	
            	// execute pl/sql procedure
            	cs.execute();
            	// gather result from out param
            	result=cs.getString(3);
            	
            	ps=con.prepareStatement("select count(*) from all_marks where roll="+roll);
    			ResultSet rs=ps.executeQuery();
    			rs.next();
    			
    			int elegible=rs.getInt(1);
            	
            	if(result.equals("valid credentials")) {
	            	pw.print(result+"<body bgcolor='#E6E6FA'></body>"); 
	            	if(elegible==0) {
		            	pw.print("<br><b>Welcom "+name+" u r eligible for test");
		            	pw.print("<br>The test duration is 10 min<br>There is 10 questions<br>click on START to start the test  ");
		            
		            	pw.print("<form method=\"post\" action=\"examurl\">");
		            	pw.print("<input type='submit' value='START'></form>");
	            	}
	            	else {
	            		pw.print("<br><b> YOU ARE ALREADY GIVEN A TEST");
	            	}
	            		
            	}
            	else
            		pw.println("<body bgcolor='#E6E6FA'<h2>plz enter correct credential </h2><br><br></body>");
            	
            	pw.print("<br><br><a href='index.html'>Return to home page..........</a>");
            }
		}
		 catch(SQLException se){
             se.printStackTrace();
	     }
	     catch(ClassNotFoundException cnf){
	         cnf.printStackTrace();;
	     }
		 catch(NumberFormatException nfe) {
			pw.print("<body bgcolor='#E6E6FA'<h2>plz enter number in password </h2><br><br>");
			pw.print("<a href='login.html'>Return to login page..........</a></body>");
		 }
	     catch(Exception e){
	         e.printStackTrace();
	     }
	     finally{
	         try{
	             if(cs!=null)
	                 cs.close();
	         }
	         catch(SQLException se){
	             se.printStackTrace();
	         }
	         try{
	             if(con!=null)
	                 con.close();
	         }
	         catch(SQLException se){
	             se.printStackTrace();
	         }
	     }
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}//class
