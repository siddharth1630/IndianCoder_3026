package com.telusko.dao;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.Customer;
import com.telusko.entity.CustomerLogin;

@Repository
public class CustomerLoginDaoImpl implements CustomerLoginDao {

	@Autowired
	SessionFactory factory;
	
	
	public boolean validateCustomer(CustomerLogin customerLogin) {
		Session session=factory.getCurrentSession();
		
		String hql="select id from Customer where email=:email and password=:password";
		Query<Customer> query=session.createQuery(hql);
		query.setParameter("email", customerLogin.getEmail());
		query.setParameter("password", customerLogin.getPassword());
		
		List<Customer> id=query.getResultList();
		System.out.println(id.isEmpty());
		return id.isEmpty();
	}

}
