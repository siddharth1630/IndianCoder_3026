package com.telusko.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.Dealer;

@Repository
public class DealerDaoImpl implements DealerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveDealer(Dealer dealer) {
		Session session=sessionFactory.getCurrentSession();
		session.save(dealer);
	}

	
	public String getDistributor(String panCard) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select distributorArea from Dealer where panCard=:panCard";
		Query query=session.createQuery(hql);
		query.setParameter("panCard", panCard);
		String distributor=(String) query.getSingleResult();
		
		return distributor;
	}

}
