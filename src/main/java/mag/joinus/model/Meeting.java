package mag.joinus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meetings")
public class Meeting {
	
	protected Meeting() {}
	
	public Meeting(String title) {
		super();
		this.title = title;
	}
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToMany
	@JoinTable(name="participants",
		     joinColumns={@JoinColumn(name="meetingId", referencedColumnName="id")},
		     inverseJoinColumns={@JoinColumn(name="userId", referencedColumnName="id")})
	private List<User> participants;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
