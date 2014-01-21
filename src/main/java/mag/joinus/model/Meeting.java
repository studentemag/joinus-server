package mag.joinus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meetings")
public class Meeting {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name="date")
	private long date;
	
	@ManyToMany
	@JoinTable(name="guests",
		     joinColumns={@JoinColumn(name="meetingId", referencedColumnName="id")},
		     inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	private List<User> guests;
	
	//TODO potrebbe aver senso il tipo Location
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mc")
	private User mc;
	
	@ManyToMany
	@JoinTable(name="participants",
		     joinColumns={@JoinColumn(name="meetingId", referencedColumnName="id")},
		     inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	private List<User> participants;
	
	@Column(name = "title")
	private String title;
	
	public Meeting() {}
	
	public String getAddress() {
		return address;
	}
	public long getDate() {
		return date;
	}
	public List<User> getGuests() {
		return guests;
	}
	public int getId() {
		return id;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public User getMc() {
		return mc;
	}
	public List<User> getParticipants() {
		return participants;
	}
	public String getTitle() {
		return title;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public void setGuests(List<User> guests) {
		this.guests = guests;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLatitude(float latitude) {
		this.latitude=latitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public void setMc(User mc) {
		this.mc = mc;
	}
	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
