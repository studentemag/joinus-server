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
	
	@Override
	@Transactional
	public int authenticate(String username) {
		User user;
		List<User> userList = userRepository.findByUserName(username);
		if (userList.isEmpty()) {
			user = new User();
			user.setUsername(username);
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
