package mag.joinus.service;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;

import org.springframework.dao.DataAccessException;


public interface JoinusService {
	
	public User login(User user);
	
	public void saveMeeting(Meeting event) throws DataAccessException;
	public String getPippo();
	

}
