package models;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.UUID;

@Entity
@Table (name = "provider")
public class Provider {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "provider_id", columnDefinition = "uuid")
	private UUID providerId;
	@Basic
	@Column (name = "identification", unique = true, length = 20)
	private String identification;
	@Column (name = "full_name", length = 100)
	private String fullName;
	@Column (name = "address", length = 100)
	private String address;
	@Column (name = "phone", length = 20)
	private String phone;

	@OneToMany (mappedBy = "provider")
	private LinkedList<Shopping> shoppingList;

	public Provider() {
	}

	public Provider(final UUID _providerId, final String _identification, final String _fullName,
		final String _address, final String _phone, final LinkedList<Shopping> _shoppingList) {
		this.providerId = _providerId;
		this.identification = _identification;
		this.fullName = _fullName;
		this.address = _address;
		this.phone = _phone;
		this.shoppingList = _shoppingList;
	}

	public UUID getProviderId() {
		return this.providerId;
	}

	public void setProviderId(final UUID idProvider) {
		this.providerId = idProvider;
	}

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(final String identification) {
		this.identification = identification;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public LinkedList<Shopping> getShoppingList() {
		return this.shoppingList;
	}

	public void setShoppingList(final LinkedList<Shopping> _shoppingList) {
		this.shoppingList = _shoppingList;
	}
}
