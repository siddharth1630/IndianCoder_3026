package com.telusko.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.telusko.customValidator.UniqueEmail;

@Table
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")	// auto increment of id by 1 and start with 1
	@SequenceGenerator(name="SEQUENCE1", sequenceName="CUSTOMER_REGISTER", allocationSize=1)	// it is important to create a seq. name
	private int id;
	@NotEmpty(message = "*required")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "*required")
	private String gender;
	@NotEmpty(message = "*required")
	@Email(message = "must be in email format")
	@Column(unique = true,name = "email")
	@UniqueEmail
	private String email;
	
	@Pattern(regexp = "^[0-9]{10}",message = "must be 10 digit")
	private String mobile;
	@NotEmpty(message = "*required")
	@Size(min = 6, message = "At least 6 character long")
	private String password;
	@NotEmpty(message = "*required")
	private String cPassword;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", mobile=" + mobile + ", password=" + password + ", cPassword=" + cPassword
				+ "]";
	}
	
	
}
