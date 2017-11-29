package db;

import javax.persistence.EntityManager;

import business.PurchaseRequest;
import business.Status;

public class StatusDB {

	public static PurchaseRequest getPurchaseRequestById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purchaserequest = em.find(PurchaseRequest.class, Id);
			return purchaserequest;
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
	
}
