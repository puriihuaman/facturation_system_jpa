package models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table (name = "shopping")
public class Shopping {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "shopping_id", columnDefinition = "uuid")
	private UUID shoppingId;

	@Basic
	@Column (name = "shopping_date")
	private Calendar shoppingDate;
	@Column (name = "amount")
	private short amount;
	@Column (name = "total")
	private double total;

	@ManyToOne
	@Column (name = "provider_id")
	private Provider provider;

	@ManyToOne
	@Column (name = "product_id")
	private Product product;

	@ManyToOne
	@Column (name = "user_id")
	private User user;

	public Shopping() {
	}

	public Shopping(final UUID _shoppingId, final Calendar _shoppingDate, final short _amount,
		final double _total, final Provider _provider, final Product _product, final User _user) {
		this.shoppingId = _shoppingId;
		this.shoppingDate = _shoppingDate;
		this.amount = _amount;
		this.total = _total;
		this.provider = _provider;
		this.product = _product;
		this.user = _user;
	}

	public UUID getShoppingId() {
		return this.shoppingId;
	}

	public void setShoppingId(final UUID _shoppingId) {
		this.shoppingId = _shoppingId;
	}

	public Calendar getShoppingDate() {
		return this.shoppingDate;
	}

	public void setShoppingDate(final Calendar _shoppingDate) {
		this.shoppingDate = _shoppingDate;
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

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(final Provider _provider) {
		this.provider = _provider;
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
