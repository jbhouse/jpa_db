package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import business.Product;

public class ProductDB {

	public static Product getProductById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Product product = em.find(Product.class, Id);
			return product;
		} finally {
			em.close();
		}
	}
	
	public static List<Product> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery("SELECT u FROM Product u",Product.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public static boolean addProduct(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(p);
			em.getTransaction().commit();
			return true;
		} finally {
			em.close();
		}
	}
	
	public static void deleteProduct(int id) {
		Product prod = getProductById(id);
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
