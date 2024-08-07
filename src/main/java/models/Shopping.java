package models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table (name = "shopping")
public class Shopping {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "id_shopping", columnDefinition = "uuid")
	private UUID idShopping;

	@Basic
	@Column (name = "shopping_date")
	private Calendar shoppingDate;
	@Column (name = "amount")
	private short amount;
	@Column (name = "total")
	private double total;
	@Column (name = "id_provider")
	private Provider provider;
	@Column (name = "id_product")
	private Product product;
	@Column (name = "id_user")
	private User user;

	public Shopping() {
	}

	public Shopping(final UUID _idShopping, final Calendar _shoppingDate, final short _amount, final double _total, final Provider _provider, final Product _product, final User _user) {
		this.idShopping = _idShopping;
		this.shoppingDate = _shoppingDate;
		this.amount = _amount;
		this.total = _total;
		this.provider = _provider;
		this.product = _product;
		this.user = _user;
	}

	public UUID getIdShopping() {
		return this.idShopping;
	}

	public void setIdShopping(final UUID idShopping) {
		this.idShopping = idShopping;
	}

	public Calendar getShoppingDate() {
		return this.shoppingDate;
	}

	public void setShoppingDate(final Calendar shoppingDate) {
		this.shoppingDate = shoppingDate;
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

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(final Provider provider) {
		this.provider = provider;
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
