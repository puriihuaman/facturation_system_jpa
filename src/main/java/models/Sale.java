package models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table (name = "sale")
public class Sale {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "sale_id", columnDefinition = "uuid")
	private UUID saleId;
	@Basic
	@Column (name = "sale_date")
	private Calendar saleDate;
	@Column (name = "amount")
	private short amount;
	@Column (name = "total")
	private double total;

	@ManyToOne
	@Column (name = "client_id")
	private Client client;

	@ManyToOne
	@Column (name = "product_id")
	private Product product;

	@ManyToOne
	@Column (name = "user_id")
	private User user;

	public Sale() {
	}

	public Sale(final UUID _saleId, final Calendar _saleDate, final short _amount, final double _total, final Client _client, final Product _product, final User _user) {
		this.saleId = _saleId;
		this.saleDate = _saleDate;
		this.amount = _amount;
		this.total = _total;
		this.client = _client;
		this.product = _product;
		this.user = _user;
	}

	public UUID getSaleId() {
		return this.saleId;
	}

	public void setSaleId(final UUID _saleId) {
		this.saleId = _saleId;
	}

	public Calendar getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(final Calendar _saleDate) {
		this.saleDate = _saleDate;
	}

	public short getAmount() {
		return this.amount;
	}

	public void setAmount(final short _amount) {
		this.amount = _amount;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(final double _total) {
		this.total = _total;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client _client) {
		this.client = _client;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(final Product _product) {
		this.product = _product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User _user) {
		this.user = _user;
	}
}
