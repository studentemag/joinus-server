package mag.joinus.repository.springdatajpa;


import java.util.List;

import mag.joinus.model.Meeting;

import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Integer>  {
	
	List<Meeting> findByTitle(String title);

	/*@Transactional
	@Query("DELETE FROM guests WHERE meeting_id = ?1 AND user_id = ?2")
	void deleteFromGuests(int id, String phone);*/
}
