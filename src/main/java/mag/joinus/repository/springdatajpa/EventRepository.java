package mag.joinus.repository.springdatajpa;


import java.util.List;

import mag.joinus.model.Event;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer>  {
	
	List<Event> findByTitle(String title);

}
