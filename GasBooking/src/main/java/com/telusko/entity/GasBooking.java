package com.telusko.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table
public class GasBooking{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")	
	@SequenceGenerator(name="SEQUENCE1", sequenceName="GAS_BOOKING", allocationSize=1)
	private int id;
	@Transient
	private ArrayList<String> consumerNos;
	private String consumerNo;
	
	@NotEmpty(message = "*required")
	@Pattern(regexp = "^[0-9]{10}" ,message = "must be 10 digit no.")
	private String mobile;
	
	@NotEmpty(message = "*required")
	private String address;
	
	private String bookingStatus="-";
	private String email;
	
	private Date bDate;
	
	private Date deliveryDate;
	
	
	public GasBooking() {
		super();
	//	  Date date = Calendar.getInstance().getTime();  
     //     DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");  
      //    bDate = dateFormat.format(date);  
          
          long millis=System.currentTimeMillis();  
          bDate=new Date(millis);  
          System.out.println(bDate);
	}


	public GasBooking(List<GasConnection> gasConnection) {
		 consumerNos=new ArrayList<String>();	

		 for(GasConnection emails:gasConnection) {
			 consumerNos.add(emails.getConsumerNo());
		 }
	}
	
	
	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConsumerNo() {
		return consumerNo;
	}


	public ArrayList<String> getConsumerNos() {
		return consumerNos;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getbDate() {
		return bDate;
	}


	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}


	public Date getdeliveryDate() {
		return deliveryDate;
	}


	public void setdeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	
	

	

	
	
	
	
}