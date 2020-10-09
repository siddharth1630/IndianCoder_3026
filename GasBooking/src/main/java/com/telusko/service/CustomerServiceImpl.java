package com.telusko.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.telusko.dao.CustomerLoginDao;
import com.telusko.dao.CustomerValidationDao;
import com.telusko.dao.GasBookingDao;
import com.telusko.dao.GasDao;
import com.telusko.dao.customerDao;
import com.telusko.entity.Customer;
import com.telusko.entity.CustomerLogin;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;

@Service
@EnableTransactionManagement
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private customerDao customerDao;
	
	@Autowired
	private CustomerLoginDao customerLoginDao;
	
	@Autowired
	private CustomerValidationDao customerValidationDao;
	
	@Autowired
	private GasDao gasDao;
	
	@Autowired
	private GasBookingDao gasBookingDao;
	
	//================== CUSTOMER ==========================
	
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}

	@Transactional
	public boolean validateCustomer(CustomerLogin customerLogin) {
		return customerLoginDao.validateCustomer(customerLogin);
	}

	@Transactional
	public boolean existsByEmail(String email, Class<Customer> class1) {
		return customerValidationDao.existsByEmail(email,class1);
	}

	// ====================== GAS CONNECTION  ==================================
	@Transactional
	public void saveGasConnection(GasConnection gascon) {
		gasDao.saveGasConnection(gascon);		
	}

	@Transactional
	public List<GasConnection> getAllDetail(String distributor) {
		return gasDao.getAllDetail(distributor);
	}

	@Transactional
	public List<GasConnection> getAllStatus(String email) {
		return gasDao.getAllStatus(email);
	}

	//================ GAS BOOKING ==================================
	
	@Transactional
	public void saveGasBooking(GasBooking gBook) {
		gasBookingDao.saveGasBooking(gBook);
		
	}
	
	@Transactional
	public List<GasBooking> getAllBookingList(String consumerNo,String email) {
		return gasBookingDao.getAllBookingList(consumerNo,email);
	}

	

}
