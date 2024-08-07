package models;

import jakarta.persistence.*;

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

	public Profile() {
	}

	public Profile(UUID _profileId, String _profileName) {
		this.profileId = _profileId;
		this.profileName = _profileName;
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
}
