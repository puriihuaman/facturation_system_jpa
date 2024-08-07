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
	private EntityManagerFactory emf = null;

	public ProductJPAController(EntityManagerFactory _emf) {
		emf = _emf;
	}

	public ProductJPAController() {
		emf = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public List<Product> getProducts() {
		List<Product> products = null;
		try (EntityManager em = getEntityManager()) {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
			products = query.getResultList();
		} catch (Exception ex) {
			Logger.getLogger(ProductJPAController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return products;
	}
}
