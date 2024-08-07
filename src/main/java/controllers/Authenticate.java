package controllers;

import jakarta.persistence.*;
import models.User;

public class Authenticate {
	private EntityManagerFactory emf = null;

	public Authenticate(EntityManagerFactory _emf) {
		emf = _emf;
	}

	public Authenticate() {
		emf = Persistence.createEntityManagerFactory("facturation_system");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public User authenticate(String _username, String _password) {
		User user = null;
		// "SELECT id_user, firstname, surname, id_profile, profile FROM authentication(?, ?)";

		try (EntityManager em = getEntityManager()) {
			TypedQuery<User> query = em.createQuery("SELECT u.userId, u.firstName, u.lastName, u.profile FROM User u WHERE authentication(:username, :password)",
				User.class
			);
			//			TypedQuery<User> query = em.createNamedQuery("User.authentication", User.class);
			//			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("User.authentication");
			query.setParameter("username", _username);
			query.setParameter("password", _password);

			user = (User) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
