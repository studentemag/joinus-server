package mag.joinus.service;

import mag.joinus.model.Event;
import mag.joinus.repository.springdatajpa.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinusServiceImpl implements JoinusService{

	private EventRepository eventRepository;
	
	@Autowired
	public JoinusServiceImpl(EventRepository eventRepository){
		System.out.println("Inside JoinusServiceImpl constructor");
		this.eventRepository=eventRepository;
	}
	
    @Override
    @Transactional
    public void saveEvent(Event event) throws DataAccessException {
        eventRepository.save(event);
    }
	
}
