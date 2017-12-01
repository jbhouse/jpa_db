package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import business.PurchaseRequestLineItem;

public class PurchaseRequestLineItemDB {

	public static PurchaseRequestLineItem getPurchaseRequestLineItemById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequestLineItem purchaserequestlineitem = em.find(PurchaseRequestLineItem.class, Id);
			return purchaserequestlineitem;
		} finally {
			em.close();
		}
	}
	
	public static boolean addPurchaseRequestLineItem(PurchaseRequestLineItem prli) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(prli);
			em.getTransaction().commit();
			return true;
		} finally {
			em.close();
		}
	}
	
	public static void deletePurchaseRequestLineItem(int id) {
		PurchaseRequestLineItem prod = getPurchaseRequestLineItemById(id);
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
