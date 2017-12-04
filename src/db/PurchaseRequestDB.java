package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import business.PurchaseRequest;

public class PurchaseRequestDB {

	public static PurchaseRequest getPurchaseRequestById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purchaserequest = em.find(PurchaseRequest.class, Id);
			return purchaserequest;
		} finally {
			em.close();
		}
	}
	
	public static boolean addPurchaseRequest(PurchaseRequest pr) {
		pr.setSubmittedDate();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(pr);
			em.getTransaction().commit();
			return true;
		} finally {
			em.close();
		}
	}
	
	public static void deletePurchaseRequest(int id) {
		PurchaseRequest pr = getPurchaseRequestById(id);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(em.merge(pr));
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

}
