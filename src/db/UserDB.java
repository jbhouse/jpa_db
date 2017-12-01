package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import business.User;

public class UserDB {

	public static User getUserById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, userId);
			return user;
		} finally {
			em.close();
		}
	}
	
	
	public static User addUser(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(u);
			em.getTransaction().commit();
			return u;
		} finally {
			em.close();
		}
	}
	
	public static List<User> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	// this is done via the @onetomany in the entity, however this code is worth keeping around
	// because it could be useful in the future somehow
	
//	public static List<PurchaseRequest> getPurchaseRequests(int id){
//		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		try {
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery<PurchaseRequest> query = cb.createQuery(PurchaseRequest.class);
//			Root<PurchaseRequest> prli = query.from(PurchaseRequest.class);
//			query.where(cb.equal(prli.get("user"), UserDB.getUserById(id)));
//			return em.createQuery(query).getResultList();
//		} finally {
//			em.close();
//		}
//	}
	
	public static void deleteUser(int id) {
	    User usr = getUserById(id);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(em.merge(usr));
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(u);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	
	public User authenticateUser(String userName, String password) {
		User usr = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String jpql = "Select u from User u where u.userName = :uname and u.passsword = :pword";
		TypedQuery<User> tq = em.createQuery(jpql, User.class);
		tq.setParameter("uname", userName);
		tq.setParameter("pword", password);
		try {
			usr = tq.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Either username or password is invalid.");
			e.printStackTrace();
		} finally {
			em.close();
		}
		return usr;
	}
	
}
