package com.telusko.service;

import java.util.List;

import com.telusko.entity.Dealer;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;

public interface AdminService {

	List<Dealer> getAllDealerDetail();

	void approveDealer(int id);

	void deleteDealer(int id);

	List<GasBooking> getAllBookingDetail();

	List<GasConnection> getAllConnectionDetail();

	void deleteConnection(int id);

}
