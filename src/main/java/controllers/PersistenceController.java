package controllers;

import models.Product;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class PersistenceController {
	private final Authenticate AUTHENTICATE = new Authenticate();
	private final ProductJPAController productJPAController = new ProductJPAController();

	/**
	 * Authenticate
	 */
	public User authenticate(String _username, String _password) {
		return AUTHENTICATE.authenticate(_username, _password);
	}

	public ArrayList<Product> getAllProducts() {
		List<Product> products = productJPAController.getProducts();
		ArrayList<Product> productArrayList = new ArrayList<>(products);
		return productArrayList;
	}
}
