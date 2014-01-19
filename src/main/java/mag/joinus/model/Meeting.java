package mag.joinus.model;

import java.sql.Date;
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
	private Date date;
	
	@ManyToMany
	@JoinTable(name="guests",
		     joinColumns={@JoinColumn(name="meetingId", referencedColumnName="id")},
		     inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	private List<User> guests;
	
	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="moc")
	private User moc;
	
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
	public Date getDate() {
		return date;
	}
	public List<User> getGuests() {
		return guests;
	}
	public int getId() {
		return id;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public User getMoc() {
		return moc;
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
	public void setDate(Date date) {
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
	public void setMoc(User moc) {
		this.moc = moc;
	}
	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
