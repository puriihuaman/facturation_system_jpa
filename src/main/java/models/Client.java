package models;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.UUID;

@Entity
@Table (name = "client")
public class Client {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "client_id", columnDefinition = "uuid")
	private UUID clientId;
	@Basic
	@Column (name = "identification", unique = true, length = 20)
	private String identification;
	@Column (name = "full_name", length = 100)
	private String fullName;
	@Column (name = "address", length = 100)
	private String address;
	@Column (name = "phone", length = 20)
	private String phone;

	@OneToMany (mappedBy = "client")
	private LinkedList<Sale> saleList;

	public Client() {
	}

	public Client(final UUID _clientId, final String _identification, final String _fullName,
		final String _address, final String _phone, final LinkedList<Sale> _saleList) {
		this.clientId = _clientId;
		this.identification = _identification;
		this.fullName = _fullName;
		this.address = _address;
		this.phone = _phone;
		this.saleList = _saleList;
	}

	public UUID getClientId() {
		return this.clientId;
	}

	public void setClientId(final UUID _idClient) {
		this.clientId = _idClient;
	}

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(final String _identification) {
		this.identification = _identification;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String _fullName) {
		this.fullName = _fullName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String _address) {
		this.address = _address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String _phone) {
		this.phone = _phone;
	}

	public LinkedList<Sale> getSaleList() {
		return this.saleList;
	}

	public void setShoppingList(final LinkedList<Sale> _saleList) {
		this.saleList = _saleList;
	}
}
