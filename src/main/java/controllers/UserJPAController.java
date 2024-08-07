package controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserJPAController {
	private EntityManagerFactory em;

	public UserJPAController(EntityManagerFactory _em) {
		em = _em;
	}

	public UserJPAController() {
		em = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return em.createEntityManager();
	}

	public void createUser(UserJPAController _user) {
		try (EntityManager em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(_user);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null) {
				em.close();
			}
			ex.printStackTrace();
		}
	}
}
