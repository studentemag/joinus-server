package mag.joinus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	protected User() {}
	
	public User(String userName) {
		super();
		this.userName = userName;
	}

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "phone")
	private String phone;
	
	@Column(name = "username")
	private String userName;
	
	@ManyToMany(mappedBy="participants")
	private List<Meeting> participating_to;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return userName;
	}


}
