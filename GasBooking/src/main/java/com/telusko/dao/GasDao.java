package com.telusko.dao;

import java.util.List;

import com.telusko.entity.GasConnection;

public interface GasDao {

	void saveGasConnection(GasConnection gascon);

	List<GasConnection> getAllDetail(String distributor);

	List<GasConnection> getAllStatus(String email);

	void changeStatus(int id);

	void rejectStatus(int id);

}
