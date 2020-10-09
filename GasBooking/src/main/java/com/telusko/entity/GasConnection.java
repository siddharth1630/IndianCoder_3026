package com.telusko.entity;

import java.sql.Date;
import java.util.LinkedHashMap;

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
public class GasConnection {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")	
	@SequenceGenerator(name="SEQUENCE1", sequenceName="GAS_REGISTRATION", allocationSize=1)
	private int id;
	private String email;
	private String state;
	@Transient
	private LinkedHashMap<String,String> stateOptions;
	@NotEmpty(message = "*required")
	private String district;
	@NotEmpty(message = "*required")
	private String distributor;
	@NotEmpty(message = "*required")
	private String fullName;
	@NotEmpty(message = "*required")
	@Pattern(regexp = "^[0-9]{10}" ,message = "must be 10 digit no.")
	private String mobile;
	private String status="PENDING";
	
	private String consumerNo="-";
	
	private Date approvedDate;
	
	
	public GasConnection() {
		stateOptions=new LinkedHashMap<String, String>();	
		stateOptions.put("jh", "Jharkhand");
		stateOptions.put("br", "Bihar");
		stateOptions.put("wb", "West Bengal");
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public LinkedHashMap<String, String> getStateOptions() {
		return stateOptions;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getConsumerNo() {
		return consumerNo;
	}


	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}


	public Date getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	
}
