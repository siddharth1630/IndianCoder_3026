package com.telusko.service;

import java.util.List;

import com.telusko.entity.Dealer;
import com.telusko.entity.DealerLogin;

public interface DealerService {

	void saveDealer(Dealer dealer);

	boolean validateDealer(DealerLogin dealerLogin);

	String getDistributor(String panCard);

	void changeStatus(int id);

	void RejectStatus(int id);

	List getPendingBookingRequest(String distributor);

	void changeBookingStatus(int id);

	void RejectBookingStatus(int id);

}
