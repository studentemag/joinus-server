package mag.joinus.service;

import mag.joinus.model.Meeting;

import org.springframework.dao.DataAccessException;


public interface JoinusService {
	
	public int authenticate(String username);
	
	
	public void saveMeeting(Meeting event) throws DataAccessException;
	public String getPippo();
}
