package mag.joinus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class UserLocation {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "timestamp")
	private long timestamp;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "latlng_id", referencedColumnName = "id")
	private LatLng latLng;

	public UserLocation() {
		
	}
	
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id id da impostare
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp timestamp da impostare
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user user da impostare
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return latLng
	 */
	public LatLng getLatLng() {
		return latLng;
	}

	/**
	 * @param latLng latLng da impostare
	 */
	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	/* (non Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLocation [id=" + id + ", timestamp=" + timestamp
				+ ", user=" + user + ", latLng=" + latLng + "]";
	}

}
