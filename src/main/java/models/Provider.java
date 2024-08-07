package models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "provider")
public class Provider {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "id_provider", columnDefinition = "uuid")
	private UUID idProvider;
	@Basic
	@Column (name = "identification", unique = true, length = 20)
	private String identification;
	@Column (name = "full_name", length = 100)
	private String fullName;
	@Column (name = "address", length = 100)
	private String address;
	@Column (name = "phone", length = 20)
	private String phone;

	public Provider() {
	}

	public Provider(final UUID _idProvider, final String _identification, final String _fullName, final String _address, final String _phone) {
		this.idProvider = _idProvider;
		this.identification = _identification;
		this.fullName = _fullName;
		this.address = _address;
		this.phone = _phone;
	}

	public UUID getIdProvider() {
		return this.idProvider;
	}

	public void setIdProvider(final UUID idProvider) {
		this.idProvider = idProvider;
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
}
