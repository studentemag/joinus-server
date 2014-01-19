package mag.joinus.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
@AttributeOverrides({
	@AttributeOverride(name="latitude", column=@Column(name = "latitude")),
	@AttributeOverride(name="longitude", column=@Column(name = "longitude"))
})
public class UserLocation extends Location{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "timestamp")
	private long timestamp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;

}
