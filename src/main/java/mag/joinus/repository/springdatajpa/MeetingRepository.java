package mag.joinus.repository.springdatajpa;


import java.util.List;

import mag.joinus.model.Meeting;

import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Integer>  {
	
	List<Meeting> findByTitle(String title);

}
