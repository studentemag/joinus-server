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

	@OneToMany(mappedBy = "mc")
	private List<Meeting> meetingsAsMc;

	@ManyToMany(mappedBy = "participants")
	private List<Meeting> meetingsAsParticipant;

	@Column(name = "phone")
	private String phone;

	@Column(name = "name")
	private String name;

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

	public List<Meeting> getMeetingsAsMc() {
		return meetingsAsMc;
	}

	public List<Meeting> getMeetingsAsParticipant() {
		return meetingsAsParticipant;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
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

	public void setMeetingsAsMc(List<Meeting> meetingsAsMc) {
		this.meetingsAsMc = meetingsAsMc;
	}

	public void setMeetingsAsParticipant(List<Meeting> meetingsAsParticipant) {
		this.meetingsAsParticipant = meetingsAsParticipant;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String username) {
		this.name = username;
	}

}
