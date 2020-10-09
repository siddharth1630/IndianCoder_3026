package com.telusko.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.Customer;

@Repository
public class CustomerValidationDaoImpl implements CustomerValidationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean existsByEmail(String email, Class<Customer> className) {
		Session session=sessionFactory.getCurrentSession();
		String hql="select id from Customer where email=:email";
		Query<Customer> query=session.createQuery(hql);
		System.out.println(email);
		query.setParameter("email", email);
		
		List<Customer> id=query.getResultList();
		System.out.println(id.isEmpty());
		if(id.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

}
