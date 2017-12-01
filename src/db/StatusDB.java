package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import business.Status;

public class StatusDB {

	public static Status getStatusById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Status status = em.find(Status.class, Id);
			return status;
		} finally {
			em.close();
		}
	}
	
	public static boolean addStatus(Status s) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(s);
			em.getTransaction().commit();
			return true;
		} finally {
			em.close();
		}
	}
	
	public static void deleteStatus(int id) {
		Status prod = getStatusById(id);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(em.merge(prod));
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	
}
