package controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Profile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileJPAController {
	private EntityManagerFactory em = null;

	public ProfileJPAController(EntityManagerFactory _emf) {
		em = _emf;
	}

	public ProfileJPAController() {
		em = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return em.createEntityManager();
	}

	public List<Profile> getProfiles() {
		List<Profile> profiles = null;
		try (EntityManager em = getEntityManager()) {
			TypedQuery<Profile> query = em.createQuery(
				"SELECT profile FROM Profile profile",
				Profile.class
			);
			profiles = query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(ProfileJPAController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return profiles;
	}
}
