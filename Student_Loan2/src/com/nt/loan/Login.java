//// this page come from login.html
package com.nt.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{
	static String password;
	static String email;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		String result=null;
		CallableStatement cs=null;
		Connection con=null;
		PrintWriter pw=res.getWriter();
		RequestDispatcher rd=null;
		
		try {
			// taking username and password from html page 
			email=req.getParameter("j_username");
			password=req.getParameter("j_password");
			
			 // register jdbc driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // establish the connection
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
            
			// create callableStatement object
            if(con!=null) {
            	cs=con.prepareCall("{ call sl_authentication(?,?,?)}");
            }
            
            if(cs!=null) {
            	// register out param 
            	cs.registerOutParameter(3, Types.VARCHAR);	// 3 becz we get 3rd value in pl/sql function and varchar becz its types is varchar
            	// set in param
            	cs.setString(1, email);
            	cs.setString(2, password);
            	
            	// execute pl/sql procedure
            	cs.execute();
            	// gather result from out param
            	result=cs.getString(3);
  
            	if(result.equals("valid credentials")) {
	       //     	pw.print(result+"<body bgcolor='#E6E6FA'></body>"); 
	        //    	pw.print("<br><b>Welcom u r successsfully logged in<br><br>");
	            	req.setAttribute("emails", email);
	            	rd=req.getRequestDispatcher("/home");
	            	rd.forward(req,res);
	          //  	pw.print(" plz continue to go to your home page--><a href='home.jsp'>CONTINUE</a></b>");
            	}
            	else {
            		pw.println("<body bgcolor='#E6E6FA'<h2>plz enter correct credential </h2><br><br></body>");
            		pw.print("<br><br><a href='index.html'>Return to home page..........</a>");
            	}
            }
		}
		 catch(SQLException se){
             se.printStackTrace();
	     }
	     catch(ClassNotFoundException cnf){
	         cnf.printStackTrace();;
	     }
		 catch(NumberFormatException nfe) {
			pw.print("<body bgcolor='#E6E6FA'<h2>plz fill all the field </h2><br><br>");
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

// go to Home.java page
