package mag.joinus.service;

import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.repository.springdatajpa.MeetingRepository;
import mag.joinus.repository.springdatajpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JoinusServiceImpl implements JoinusService{

	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	public JoinusServiceImpl(){
		System.out.println("Inside JoinusServiceImpl constructor");
	}
	
	/*
	 * login
	 * input: phone, username
	 * phone is empty -> Stop by client
	 * phone exists in db, name is empty -> retrieve
	 * phone exists in db, name is not empty -> update with inserted name
	 * phone doesn't exist in db, name is empty -> create with "noname"
	 * phone doesn't exist in db, name is not empty -> create with inserted name
	*/
	public User login(User user){
		return null;
	}
	
	
	@Override
	@Transactional
	public int authenticate(String username) {
		User user;
		List<User> userList = userRepository.findByName(username);
		if (userList.isEmpty()) {
			user = new User();
			user.setName(username);
			userRepository.save(user);
		}
		else
			user = userList.get(0);
		return user.getId();
	}
	
    @Override
    @Transactional
    public void saveMeeting(Meeting event) throws DataAccessException {
        meetingRepository.save(event);
    }
    
    public String getPippo(){
    	return "pippo";
    }



	
}
