package db;

import javax.persistence.EntityManager;

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
	
}
