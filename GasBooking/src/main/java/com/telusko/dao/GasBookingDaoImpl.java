package com.telusko.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.GasBooking;

@Repository
public class GasBookingDaoImpl implements GasBookingDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveGasBooking(GasBooking gBook) {
		Session session=sessionFactory.getCurrentSession();
		session.save(gBook);
	}

	public List<GasBooking> getAllBookingList(String consumerNo,String email) {
		Session session=sessionFactory.getCurrentSession();
//		String hql=("select g.consumerNo,g.bookingStatus,g.mobile,g.address,g.email,c.firstName,c.lastName from Customer c,GasBooking g where g.consumerNo=:consumerNo and c.email=:email");
//		String hql=("select g.consumerNo,g.bookingStatus,g.mobile,g.address,g.email,c.firstName,c.lastName from Customer c,GasBooking g where  c.email=:email");
		String hql=(" from GasBooking where email=:email");
		
		Query query=session.createQuery(hql);
//		query.setParameter("consumerNo", consumerNo);
		query.setParameter("email", email);	
		
		return  query.getResultList();
	}

	public List getPendingBookingRequest(String distributorArea) {
		Session session=sessionFactory.getCurrentSession();
		
//		String hql=("from GasBooking where consumerNo like :pattern and bookingStatus=:status");
		String hql=("from GasBooking where consumerNo like :pattern");
		Query query=session.createQuery(hql);
		query.setParameter("pattern", distributorArea+"%");
//		query.setParameter("status", "-");
		return query.getResultList();
	}
	

	public void changeBookingStatus(int id) {
		Session session=sessionFactory.getCurrentSession();
		
		String hql=("update GasBooking set bookingStatus=:status , deliveryDate=bDate+5 where id=:id");
//		String hql=("UPDATE GasConnection MAIN SET consumerNo = (SELECT SUB.distributor || '_1000' || SUB.id FROM GasConnection SUB WHERE SUB.id =:id) , status=:status where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("status", "APPROVED");
//		query.setParameter("date", "bDate+5");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}


	public void rejectBookingStatus(int id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=("update GasBooking set bookingStatus=:status where id=:id");
//		String hql=("UPDATE GasConnection MAIN SET consumerNo =:consumerNo , status=:status where id=:id");
		Query query=session.createQuery(hql);
//		query.setParameter("consumerNo", "-");
		query.setParameter("status", "REJECTED");
		query.setParameter("id", id);
		query.executeUpdate();	
		
	}


}
