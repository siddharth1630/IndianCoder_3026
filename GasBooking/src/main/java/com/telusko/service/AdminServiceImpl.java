package com.telusko.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.telusko.dao.AdminDao;
import com.telusko.entity.Dealer;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;

@Service
@EnableTransactionManagement
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	
	@Transactional
	public List<Dealer> getAllDealerDetail() {
		return adminDao.getAllDealerDetail();
	}

	@Transactional
	public void approveDealer(int id) {
		adminDao.approveDealer(id);
	}

	@Transactional
	public void deleteDealer(int id) {
		adminDao.deleteDealer(id);
	}

	@Transactional
	public List<GasBooking> getAllBookingDetail() {
		return adminDao.getAllBookingDetail();
	}

	@Transactional
	public List<GasConnection> getAllConnectionDetail() {
		return adminDao.getAllConnectionDetail();
	}

	@Transactional
	public void deleteConnection(int id) {
		adminDao.deleteConnection(id);
	}

}
