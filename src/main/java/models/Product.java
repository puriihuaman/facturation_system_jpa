package models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "product")
public class Product {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "product_id", columnDefinition = "uuid")
	private UUID productId;
	@Basic
	@Column (name = "product_name", unique = true, length = 20)
	private String productName;
	@Column (name = "stock")
	private short stock;
	@Column (name = "price")
	private double price;
	@Column (name = "description", length = 100)
	private String description;
	@OneToOne
	@Column (name = "user_id")
	private User user;

	public Product() {
	}

	public Product(UUID _productId, String _productName, short _stock, double _price,
		String _description, User _user) {
		this.productId = _productId;
		this.productName = _productName;
		this.stock = _stock;
		this.price = _price;
		this.description = _description;
		this.user = _user;
	}

	public UUID getProductId() {
		return this.productId;
	}

	public void setProductId(final UUID _idProduct) {
		this.productId = _idProduct;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(final String _productName) {
		this.productName = _productName;
	}

	public short getStock() {
		return this.stock;
	}

	public void setStock(final short _stock) {
		this.stock = _stock;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(final double _price) {
		this.price = _price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String _description) {
		this.description = _description;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User _user) {
		this.user = _user;
	}
}
