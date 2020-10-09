package com.telusko.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.Dealer;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Dealer> getAllDealerDetail() {
		Session session =sessionFactory.getCurrentSession();
		String hql=("from Dealer");
		Query query=session.createQuery(hql);
		List<Dealer> dealer=query.getResultList();
		return dealer;
	}

	public void approveDealer(int id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=("update Dealer set status=:status where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("status", "APPROVED");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	public void deleteDealer(int id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=("Delete from Dealer where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();	
	}

	public List<GasBooking> getAllBookingDetail() {
		Session session =sessionFactory.getCurrentSession();
		String hql=("from GasBooking");
		Query query=session.createQuery(hql);
		List<GasBooking> gasbooking=query.getResultList();
		return gasbooking;
	}

	public List<GasConnection> getAllConnectionDetail() {
		Session session =sessionFactory.getCurrentSession();
		String hql=("from GasConnection");
		Query query=session.createQuery(hql);
		List<GasConnection> gasConnection=query.getResultList();
		return gasConnection;
	}

	public void deleteConnection(int id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=("Delete from GasConnection where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();	
		
	}

}
