package com.telusko.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.dao.customerDao;
import com.telusko.entity.Customer;

@Repository
public class CustomerDaoImpl implements customerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveCustomer(Customer customer) {
		
		Session session=sessionFactory.getCurrentSession();
		session.save(customer);
	}

	
}
