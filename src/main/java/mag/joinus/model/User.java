package mag.joinus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany(mappedBy = "user")
	private List<UserLocation> locations;

	@ManyToMany(mappedBy = "guests")
	private List<Meeting> meetingsAsGuest;

	@OneToMany(mappedBy = "moc")
	private List<Meeting> meetingsAsMoc;

	@ManyToMany(mappedBy = "participants")
	private List<Meeting> meetingsAsParticipant;

	@Column(name = "phone")
	private String phone;

	@Column(name = "username")
	private String username;

	public User() {
	}

	public int getId() {
		return id;
	}

	public List<UserLocation> getLocations() {
		return locations;
	}

	public List<Meeting> getMeetingsAsGuest() {
		return meetingsAsGuest;
	}

	public List<Meeting> getMeetingsAsMoc() {
		return meetingsAsMoc;
	}

	public List<Meeting> getMeetingsAsParticipant() {
		return meetingsAsParticipant;
	}

	public String getPhone() {
		return phone;
	}

	public String getUsername() {
		return username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLocations(List<UserLocation> locations) {
		this.locations = locations;
	}

	public void setMeetingsAsGuest(List<Meeting> meetingsAsGuest) {
		this.meetingsAsGuest = meetingsAsGuest;
	}

	public void setMeetingsAsMoc(List<Meeting> meetingsAsMoc) {
		this.meetingsAsMoc = meetingsAsMoc;
	}

	public void setMeetingsAsParticipant(List<Meeting> meetingsAsParticipant) {
		this.meetingsAsParticipant = meetingsAsParticipant;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
