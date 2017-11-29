package db;

import javax.persistence.EntityManager;

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

}