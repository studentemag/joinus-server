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
public class UserLocation{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "timestamp")
	private long timestamp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "latlng_id", referencedColumnName = "id")
	private LatLng latLng;

}
