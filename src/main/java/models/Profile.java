package models;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.UUID;

@Entity
@Table (name = "profile")
public class Profile {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (name = "profile_id", columnDefinition = "uuid")
	private UUID profileId;
	@Column (name = "profile_name", unique = true, length = 30)
	private String profileName;

	@OneToMany (mappedBy = "profile") // 1 a N
	private LinkedList<User> users;

	public Profile() {
	}

	public Profile(final UUID _profileId, final String _profileName, final LinkedList<User> _users) {
		this.profileId = _profileId;
		this.profileName = _profileName;
		this.users = _users;
	}

	public UUID getProfileId() {
		return this.profileId;
	}

	public void setProfileId(final UUID idProfile) {
		this.profileId = idProfile;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public LinkedList<User> getUsers() {
		return this.users;
	}

	public void setUsers(final LinkedList<User> users) {
		this.users = users;
	}
}
