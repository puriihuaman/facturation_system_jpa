package controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.User;

public class Authenticate {
	EntityManagerFactory em = null;

	public Authenticate(EntityManagerFactory _em) {
		em = _em;
	}

	public Authenticate() {
		em = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return em.createEntityManager();
	}

	public User authenticate(String _username, String _password) {
		User user = null;
		// "SELECT id_user, firstname, surname, id_profile, profile FROM authentication(?, ?)";

		try (EntityManager em = getEntityManager()) {
			TypedQuery<User> query = em.createQuery("SELECT u.userId, u.firstName, u.lastName, u.profile FROM User u WHERE authentication(:username, :password)",
				User.class);
//			TypedQuery<User> query = em.createNamedQuery("User.authentication", User.class);
			query.setParameter("username", _username);
			query.setParameter("password", _password);
			user = query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
