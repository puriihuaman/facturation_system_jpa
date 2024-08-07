package models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table (name = "sale")
public class Sale {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "id_sale", columnDefinition = "uuid")
	private UUID idSale;
	@Basic
	@Column (name = "sale_date")
	private Calendar saleDate;
	@Column (name = "amount")
	private short amount;
	@Column (name = "total")
	private double total;

	@Column (name = "id_client")
	private Client client;

	@Column (name = "id_product")
	private Product product;

	@Column (name = "id_user")
	private User user;

	public Sale() {
	}

	public UUID getIdSale() {
		return this.idSale;
	}

	public void setIdSale(final UUID idSale) {
		this.idSale = idSale;
	}

	public Calendar getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(final Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public short getAmount() {
		return this.amount;
	}

	public void setAmount(final short amount) {
		this.amount = amount;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(final double total) {
		this.total = total;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}
}
