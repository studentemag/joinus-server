package mag.joinus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "latlngs")
public class LatLng {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longitude")
	private double longitude;
	
}
