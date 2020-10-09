package com.telusko.dao;

import java.util.List;

import com.telusko.entity.GasBooking;

public interface GasBookingDao {

	void saveGasBooking(GasBooking gBook);

	List<GasBooking> getAllBookingList(String consumerNo,String email);

	List getPendingBookingRequest(String distributorArea);

	void changeBookingStatus(int id);

	void rejectBookingStatus(int id);

}
