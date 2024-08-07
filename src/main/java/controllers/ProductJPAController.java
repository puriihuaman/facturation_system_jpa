package controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Product;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductJPAController implements Serializable {
	private EntityManagerFactory em = null;

	public ProductJPAController(EntityManagerFactory _em) {
		em = _em;
	}

	public ProductJPAController() {
		em = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return em.createEntityManager();
	}

	public List<Product> getProducts() {
		List<Product> products = null;
		try (EntityManager em = getEntityManager()) {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
			products = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(ProductJPAController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}
}
