//// this page come from Login.java
package com.nt.loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class Home extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email=(String)req.getAttribute("emails");
		String name=null;
		int roll=0;
		double loan_amount=0.0;
		String status=null;
		RequestDispatcher rd=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
			
			Statement statement = con.createStatement();
			String sql ="SELECT NAME,ROLL FROM  SL_REGISTER_STUDENT WHERE EMAIL='"+email+"' ";
			ResultSet resultSet = statement.executeQuery(sql);
			PrintWriter pw=res.getWriter();

			while(resultSet.next()){
				name=resultSet.getString("name");
				roll=Integer.parseInt(resultSet.getString("roll"));
			}
			PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM enterInfo WHERE ROLL="+roll);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			
			try {
				ps=con.prepareStatement("SELECT l.LOAN_AMOUNT,i.status FROM LOAN_DETAIL l,insert_loan i WHERE  l.roll=i.roll and l.roll="+roll);
				rs=ps.executeQuery();
				rs.next();
				loan_amount =rs.getDouble(1);
				status=rs.getString(2);
				
			}
			catch(SQLException se) {
				loan_amount=0.0;
			}
	    	
			if(count!=0) {
        		if(loan_amount!=0.0 && status.contentEquals("ACCEPTED")) {
        			req.setAttribute("roll", roll);
        			req.setAttribute("loan_amount", loan_amount);
        			rd=req.getRequestDispatcher("repay.jsp");
        			rd.forward(req, res);
        		}
        		else if(loan_amount!=0.0 && status.contentEquals("PENDING")) {
        			pw.print("loan is in pending state plz wait for 24 hr");
        		}
        		else {
	        		com.nt.loan.Loan.signal=1;		// it is used to show that the value is go from request dispatcher not a text box for understand go to Loan.jsp
	        		req.setAttribute("roll",roll);
	        		rd=req.getRequestDispatcher("Loan.jsp");
	            	rd.forward(req,res);
        		}
        	}
        	else {
        		req.setAttribute("roll",roll);
        		rd=req.getRequestDispatcher("enterinfor.jsp");
            	rd.forward(req,res);
        	}
//			pw.print("If Personal info is complete click on here<a href=com.jsp>yes</a></h1>");
//			pw.print("<h1>If Personal info is not complete click here--><a href=enterinfor.jsp>NO</a></h1>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

// goto enterinfor.jsp
// goto Loan.jsp
// goto repay.jsp
