package com.telusko.service;

import java.util.List;

import com.telusko.entity.Customer;
import com.telusko.entity.CustomerLogin;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;

public interface CustomerService {

	void saveCustomer(Customer customer);

	boolean validateCustomer(CustomerLogin customerLogin);

	boolean existsByEmail(String email, Class<Customer> class1);

	void saveGasConnection(GasConnection gascon);

	List<GasConnection> getAllDetail(String distributor);

	List<GasConnection> getAllStatus(String email);

	void saveGasBooking(GasBooking gBook);

	List<GasBooking> getAllBookingList(String consumerNo, String email);


}
