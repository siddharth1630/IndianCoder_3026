// come from Loan.jsp and loanCalulation.jsp

package com.nt.loan;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Loan {
	Connection con=null;
	PreparedStatement psTotalBalance=null;
	PreparedStatement psTakeLoan=null;
	ResultSet rs=null;
	public int count=0;
	public static int signal;
	
//	PrintWriter pw=null;
	
	public Loan(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","project","project");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public double totalBalance(int roll) {
		try {
			psTotalBalance=con.prepareStatement("SELECT TOTAL_BALANCE,COUNTS FROM LOAN_DETAIL WHERE ROLL="+roll+"order by counts desc");
			rs=psTotalBalance.executeQuery();
			rs.next();
			count=rs.getInt(2);
			return rs.getInt(1);
		}
		catch(SQLException se) {
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"-" +
                (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR);
    }

	public int takeLoan(int roll,double loanAmount,int tenure,double totalBalance,int count,double intrest) {
		try {
	//		System.out.println(roll+" "+loanAmount+" "+tenure+" "+totalBalance+" "+count);
			java.util.Date sdate= new java.util.Date();
			long sysdate=sdate.getTime();
			java.sql.Date sqlDate=new java.sql.Date(sysdate);
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, tenure);
			String repay=getDate(cal);;

			psTakeLoan=con.prepareStatement("INSERT INTO LOAN_DETAIL VALUES(?,?,?,?,?,?,TO_DATE(?,'dd mm yyyy'))");
			psTakeLoan.setInt(1, roll);
			psTakeLoan.setDouble(2, loanAmount);
			psTakeLoan.setInt(3, tenure); 
			psTakeLoan.setDouble(4, totalBalance+loanAmount);
			psTakeLoan.setInt(5, count+1);
			psTakeLoan.setDate(6, sqlDate);
			psTakeLoan.setString(7, repay);

			int counts = psTakeLoan.executeUpdate();
			if(counts>0)
			{
			//	System.out.println("you have to pay-->"+intrest+" within "+repay);
			}
			return 0;
		}
		catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public double intrestCalculator(double loanAmount,int tenure) {
		double intrest=loanAmount*0.04*tenure;
		intrest=loanAmount+intrest;
		return intrest;
	}
	/*
	public int bankDetail(int roll) {
		try {
			psTotalBalance=con.prepareStatement("SELECT ROLL FROM INSERT_LOAN WHERE ROLL="+roll);
			rs=psTotalBalance.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
		catch(SQLException se) {
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}*/
}
