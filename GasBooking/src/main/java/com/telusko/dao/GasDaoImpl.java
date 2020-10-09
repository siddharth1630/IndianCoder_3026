package com.telusko.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.entity.GasConnection;

@Repository
public class GasDaoImpl implements GasDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void saveGasConnection(GasConnection gascon) {
		Session session=sessionFactory.getCurrentSession();
		session.save(gascon);
	}

	
	public List<GasConnection> getAllDetail(String distributor) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from GasConnection where distributor=:distributor";
		Query query=session.createQuery(hql);
		query.setParameter("distributor", distributor);
		List<GasConnection> gasCon= query.getResultList();
		
		return gasCon;
	}


	public List<GasConnection> getAllStatus(String email) {
		Session session=sessionFactory.getCurrentSession();
		String  hql=("from GasConnection where email=:email");
		Query query=session.createQuery(hql);
		query.setParameter("email", email);
		List<GasConnection> gasCon=query.getResultList();
		return gasCon;
	}


	public void changeStatus(int id) {
		Session session=sessionFactory.getCurrentSession();
//		String hql=("update GasConnection set status=:status where id=:id");
		String hql=("UPDATE GasConnection MAIN SET consumerNo = (SELECT SUB.distributor || '_1000' || SUB.id FROM GasConnection SUB WHERE SUB.id =:id) , status=:status , approvedDate=sysdate   where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("status", "APPROVED");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}


	public void rejectStatus(int id) {
		Session session=sessionFactory.getCurrentSession();
//		String hql=("update GasConnection set status=:status where id=:id");
		String hql=("UPDATE GasConnection MAIN SET consumerNo =:consumerNo , status=:status where id=:id");
		Query query=session.createQuery(hql);
		query.setParameter("consumerNo", "-");
		query.setParameter("status", "REJECTED");
		query.setParameter("id", id);
		query.executeUpdate();	
		
	}

}
