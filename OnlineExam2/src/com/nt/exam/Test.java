// database user-->project 	password---> project	
// table register_student(roll,name)
// function Authentication(user,pass,result)
// table test_result(qno,ans)
//--------------- ABOVE QUERY DOES'T USE IN THIS FILE-----------------
// database table--> QUESTION (QNO,QUES,OP1,OP2,OP3,OP4,RES)	// USED IN THIS JAVA FILE

package com.nt.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet{
	static ArrayList<Integer> qno=new ArrayList<Integer>();
	static ArrayList<String> ques=new ArrayList<String>();
	static ArrayList<String> op1=new ArrayList<String>();
	static ArrayList<String> op2=new ArrayList<String>();
	static ArrayList<String> op3=new ArrayList<String>();
	static ArrayList<String> op4=new ArrayList<String>();
	static ArrayList<Integer> fqno=new ArrayList<Integer>();
	static ArrayList<Integer> ans=new ArrayList<Integer>();
	static int counter=-1;
	
	public static void call() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		
		try {
			 // register jdbc driver
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        // establish the connection
	        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
	        
	        if(con!=null) {
	        	ps=con.prepareStatement("SELECT QNO,QUES,OP1,OP2,OP3,OP4 FROM QUESTION");
	        }
	        if(ps!=null)
	        	rs=ps.executeQuery();
	        
	        if(rs!=null) {
	        	while(rs.next()) {
	        		qno.add(rs.getInt(1));
	        		ques.add(rs.getString(2));
	        		op1.add(rs.getString(3));
	        		op2.add(rs.getString(4));
	        		op3.add(rs.getString(5));
	        		op4.add(rs.getString(6));
	        	}
	        }
		}
	    catch(SQLException se){
	        se.printStackTrace();
	     }
	     catch(ClassNotFoundException cnf){
	         cnf.printStackTrace();;
	     }
	     catch(Exception e){
	         e.printStackTrace();
	     }
	     finally{
	         try{
	             if(ps!=null)
	                 ps.close();
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
	         try{
	             if(rs!=null)
	                 rs.close();
	         }
	         catch(Exception se){
	             se.printStackTrace();
	         }
	     }
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=res.getWriter();	
		if(counter==-1)//&& qno.size()==0)
			call();
		try {
			counter++;
			fqno.add(qno.get(counter));
			if(counter!=qno.size()-1) {
        		pw.print("<form action='nxt' method='post'>");
			
        			
        			pw.print(qno.get(counter)+"> "+ques.get(counter)+"<br><br>");
        			pw.print("<input type='hidden' name='default' value='0'>");
	        		pw.print("<br><input type='radio' name='select' value='1'> "+op1.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='2'> "+op2.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='3'> "+op3.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='4'> "+op4.get(counter));
	   		
	        		pw.print("<br><br><input type='submit' value='NEXT' </form><br><br>");
			}
	        	
	        	if(counter==qno.size()-1) {
	        		pw.print("<form action='sub' method='post'>");
	        		
	        		pw.print(qno.get(counter)+"> "+ques.get(counter)+"<br><br>");
	        		pw.print("<input type='hidden' name='default' value='0'>");
	        		pw.print("<br><input type='radio' name='select' value='1'> "+op1.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='2'> "+op2.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='3'> "+op3.get(counter));
	        		pw.print("<br><br><input type='radio' name='select' value='4'> "+op4.get(counter));
	        	
	    			pw.print("<br><br><input type='submit' value='SUBMIT'</form>");
	    		}
	        	
	        	if(counter!=0 && counter!=qno.size()) {
	    			ans.add(Integer.parseInt(req.getParameter("select")));
	    		}
	        	
          }
		  catch(NumberFormatException nfe) {
			  ans.add(Integer.parseInt(req.getParameter("default")));
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}