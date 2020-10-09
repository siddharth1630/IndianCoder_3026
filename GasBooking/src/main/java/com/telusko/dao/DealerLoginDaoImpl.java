package com.telusko.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.Customer;
import com.telusko.entity.DealerLogin;

@Repository
public class DealerLoginDaoImpl implements DealerLoginDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean validateDealer(DealerLogin dealerLogin) {
		Session session=sessionFactory.getCurrentSession();
		
		String hql=("select id from Dealer where panCard=:panCard and password=:password and status=:status");
		Query<Customer> query=session.createQuery(hql);
		query.setParameter("panCard", dealerLogin.getPanCard());
		query.setParameter("password", dealerLogin.getPassword());
		query.setParameter("status", "APPROVED");
		
		List<Customer> id=query.getResultList();
		return id.isEmpty();
	}

}
