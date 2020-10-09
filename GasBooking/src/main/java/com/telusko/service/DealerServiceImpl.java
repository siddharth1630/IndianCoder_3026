package com.telusko.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.telusko.dao.DealerDao;
import com.telusko.dao.DealerLoginDao;
import com.telusko.dao.GasBookingDao;
import com.telusko.dao.GasDao;
import com.telusko.entity.Dealer;
import com.telusko.entity.DealerLogin;

@EnableTransactionManagement
@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerDao dealerDao;
	
	@Autowired
	private DealerLoginDao dealerLoginDao;
	
	@Autowired
	private GasDao gasDao;
	
	@Autowired
	private GasBookingDao gasBookingDao;
	
	@Transactional
	public void saveDealer(Dealer dealer) {
		dealerDao.saveDealer(dealer);

	}

	@Transactional
	public boolean validateDealer(DealerLogin dealerLogin) {
		return dealerLoginDao.validateDealer(dealerLogin);
	}

	@Transactional
	public String getDistributor(String panCard) {
		return dealerDao.getDistributor(panCard);
	}

	@Transactional
	public void changeStatus(int id) {
		gasDao.changeStatus(id);
	}

	@Transactional
	public void RejectStatus(int id) {
		gasDao.rejectStatus(id);
		
	}
	
	@Transactional
	public List getPendingBookingRequest(String distributorArea) {
		return gasBookingDao.getPendingBookingRequest(distributorArea);
	}

	@Transactional
	public void changeBookingStatus(int id) {
		gasBookingDao.changeBookingStatus(id);
		
	}
	
	@Transactional
	public void RejectBookingStatus(int id) {
		gasBookingDao.rejectBookingStatus(id);		
	}

}
