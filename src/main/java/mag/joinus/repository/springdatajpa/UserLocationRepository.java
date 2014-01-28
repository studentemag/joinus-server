package mag.joinus.repository.springdatajpa;

import java.util.List;

import mag.joinus.model.User;
import mag.joinus.model.UserLocation;

import org.springframework.data.repository.CrudRepository;

public interface UserLocationRepository extends CrudRepository<UserLocation, Integer> {
	List<UserLocation> findByUser(User user);
}
