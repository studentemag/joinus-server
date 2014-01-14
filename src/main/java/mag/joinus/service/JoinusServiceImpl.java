package mag.joinus.service;

import mag.joinus.model.Event;
import mag.joinus.repository.springdatajpa.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinusServiceImpl implements JoinusService{

	@Autowired
	private EventRepository eventRepository;
	

	public JoinusServiceImpl(){
		System.out.println("Inside JoinusServiceImpl constructor");
	}
	
    @Override
    @Transactional
    public void saveEvent(Event event) throws DataAccessException {
        eventRepository.save(event);
    }
    
    public String getPippo(){
    	return "pippo";
    }
	
}
