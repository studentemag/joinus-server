package mag.joinus.repository.springdatajpa;


import java.util.List;

import mag.joinus.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>  {
	
	//List<User> findByName(String Name);
	List<User> findByPhone(String Phone);
	List<User> findByPhoneAndName(String Phone, String Name);
}
