package com.telusko.dao;

import com.telusko.entity.Dealer;

public interface DealerDao {

	void saveDealer(Dealer dealer);

	String getDistributor(String panCard);

}
