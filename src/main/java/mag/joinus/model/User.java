package mag.joinus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "phone")
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<UserLocation> locations;

	@JsonIgnore
	@ManyToMany(mappedBy = "guests")
	private List<Meeting> meetingsAsGuest;

	@JsonIgnore
	@OneToMany(mappedBy = "mc")
	private List<Meeting> meetingsAsMc;

	@JsonIgnore
	@ManyToMany(mappedBy = "participants")
	private List<Meeting> meetingsAsParticipant;


	@Column(name = "name")
	private String name;

	public User() {
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

	/* (non Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [phone=" + phone + ", name=" + name + "]";
	}

}
