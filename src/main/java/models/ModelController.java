package models;

import controllers.PersistenceController;

import java.util.ArrayList;

public class ModelController {
	private final PersistenceController PERSISTENCE = new PersistenceController();

	/**
	 * Authentication
	 */
//	public User authenticate(String _username, String _password) {
//		return PERSISTENCE.authenticate(_username, _password);
//	}

	public ArrayList<Product> getProducts() {
		return PERSISTENCE.getAllProducts();
	}
}
