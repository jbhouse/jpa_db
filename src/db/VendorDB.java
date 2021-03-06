package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import business.Vendor;

public class VendorDB {

	public static Vendor getVendorById(int vendorId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Vendor vendor = em.find(Vendor.class, vendorId);
			return vendor;
		} finally {
			em.close();
		}
	}
	
	public static List<Vendor> getAllVendors() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Vendor> query = em.createQuery("SELECT u FROM Vendor u",Vendor.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public static boolean addVendor(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(v);
			em.getTransaction().commit();
			return true;
		} finally {
			em.close();
		}
	}
	
	public static void deleteVendor(int id) {
		Vendor vendr = getVendorById(id);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(em.merge(vendr));
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	
}
