package com.telusko.dao;

import com.telusko.entity.Customer;
import com.telusko.entity.CustomerLogin;

public interface CustomerLoginDao {

	boolean validateCustomer(CustomerLogin customerLogin);

}
