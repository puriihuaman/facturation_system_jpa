package models;

import jakarta.persistence.*;

//@NamedQuery(name = "User.authentication", query = "SELECT id_user, firstname, surname, id_profile, profile FROM authentication(?, ?)")
import java.util.UUID;

@Entity
@Table (name = "users")
@NamedEntityGraph (name = "User.authentication", attributeNodes = {@NamedAttributeNode ("user_id"),
	@NamedAttributeNode ("first_name"),
	@NamedAttributeNode ("last_name"),
	@NamedAttributeNode ("profile_id"),
	@NamedAttributeNode ("profile_name")
})
@NamedQuery (name = "User.authentication", query = "SELECT u.user_id, u.firstname, u.surname, u" +
	".id_profile, u.profile FROM User u WHERE (u.id_user, u.firstname, u.surname, u.id_profile, u.profile) IN (SELECT * FROM authentication(:username, :password))")
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

	@OneToOne
	@Column (name = "profile_id")
	private Profile profile;

	public User() {
	}

	public User(final UUID _userId, final String _username, final String _firstName, final String _lastName, final String _password, final Profile _profile) {
		this.userId = _userId;
		this.username = _username;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.password = _password;
		this.profile = _profile;
	}

	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(final UUID idUser) {
		this.userId = idUser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(final Profile profile) {
		this.profile = profile;
	}
}
