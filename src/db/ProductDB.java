package db;

import javax.persistence.EntityManager;

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
	
}
