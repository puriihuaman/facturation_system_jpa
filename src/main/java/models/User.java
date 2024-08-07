package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

@Entity
@Table (name = "users")
@NamedStoredProcedureQuery (name = "User.authentication", procedureName = "authentication", parameters = {
	@StoredProcedureParameter (mode = ParameterMode.IN, type = String.class, name = "username"),
	@StoredProcedureParameter (mode = ParameterMode.IN, type = String.class, name = "password")
}, resultSetMappings = "User.authenticationResultMapping")
@SqlResultSetMapping (name = "User.authenticationResultMapping", entities = {@EntityResult (entityClass = User.class, fields = {
	@FieldResult (name = "userId", column = "user_id"),
	@FieldResult (name = "username", column = "username"),
	@FieldResult (name = "firstName", column = "first_name"),
	@FieldResult (name = "lastName", column = "last_name"),
	@FieldResult (name = "profile.profileId", column = "profile_id"),
	@FieldResult (name = "profile.profileName", column = "profile_name")
})
})
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "user_id", columnDefinition = "uuid")
	private UUID userId;
	@Basic
	@Column (name = "username", unique = true, length = 20)
	private String username;
	@Column (name = "first_name", length = 50)
	private String firstName;
	@Column (name = "last_name", length = 50)
	private String lastName;
	@Column (name = "password", length = 32)
	private String password;

	@ManyToOne // 1 a N
	@Column(name = "profile_id")
	private Profile profile;

	@OneToMany (mappedBy = "user")
	@Column(name = "product_id")
	private LinkedList<Product> products;

	@OneToMany(mappedBy = "user")
	@Column(name = "shopping_id")
	private LinkedList<Shopping> shoppings;

	@OneToMany(mappedBy = "user")
	@Column(name = "sale_id")
	private LinkedList<Sale> sales;

	public User() {
	}

	public User(final UUID _userId, final String _username, final String _firstName, final String _lastName, final String _password, final Profile _profile, final LinkedList<Product> _products) {
		this.userId = _userId;
		this.username = _username;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.password = _password;
		this.profile = _profile;
		this.products = _products;
	}

	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(final UUID _userId) {
		this.userId = _userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String _username) {
		this.username = _username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String _firstName) {
		this.firstName = _firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String _lastName) {
		this.lastName = _lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String _password) {
		this.password = _password;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(final Profile _profile) {
		this.profile = _profile;
	}

	public LinkedList<Product> getProducts() {
		return this.products;
	}

	public void setProducts(final LinkedList<Product> _products) {
		this.products = _products;
	}

	public LinkedList<Shopping> getShoppings() {
		return this.shoppings;
	}

	public void setShoppings(final LinkedList<Shopping> _shoppings) {
		this.shoppings = _shoppings;
	}

	public LinkedList<Sale> getSales() {
		return this.sales;
	}

	public void setSales(final LinkedList<Sale> _sales) {
		this.sales = _sales;
	}
}
