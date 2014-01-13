package mag.joinus.service;

import mag.joinus.model.Event;

import org.springframework.dao.DataAccessException;


public interface JoinusService {
	
	public void saveEvent(Event event) throws DataAccessException;

}
