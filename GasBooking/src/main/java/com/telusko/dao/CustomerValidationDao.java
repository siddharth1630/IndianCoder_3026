package com.telusko.dao;

import com.telusko.entity.Customer;

public interface CustomerValidationDao {

	boolean existsByEmail(String email, Class<Customer> class1);

}
