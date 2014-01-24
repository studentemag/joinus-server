package mag.joinus.service;

import java.util.ArrayList;
import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.repository.springdatajpa.MeetingRepository;
import mag.joinus.repository.springdatajpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
	 *** phone is empty -> Stop by client
	 *** phone exists in db, name is empty -> retrieve
	 *** phone exists in db, name is not empty -> update with inserted name
	 *** phone doesn't exist in db, name is empty -> create with "noname"
	 *** phone doesn't exist in db, name is not empty -> create with inserted name
	*/
	@Override
	@Transactional
	public User login(User user) {
		List<User> userList;
		String username = user.getName();
		String phone = user.getPhone();
		
		if (username.isEmpty()) {
			userList = userRepository.findByPhone(phone);
			if (! userList.isEmpty())
				user = userList.get(0);
			else {
				user = new User();
				user.setPhone(phone);
				user.setName("noname");
				userRepository.save(user);
				userList = userRepository.findByPhoneAndName(phone, "noname");
				user = userList.get(0);
			}
				
		} else {
			userList = userRepository.findByPhone(phone);
			if (! userList.isEmpty()) {
				user = userList.get(0);
				user.setName(username);
				userRepository.save(user);
			} else {
				user = new User();
				user.setPhone(phone);
				user.setName(username);
				userRepository.save(user);
			}
			userList = userRepository.findByPhoneAndName(phone, username);
			user = userList.get(0);
		}
		
		return user;
	}
	    
	@Override
	@Transactional
	public List<Meeting> getUpcomingEvents(int userId) {
		User u = userRepository.findOne(userId);
		List<Meeting> meetingsAsParticipant = u.getMeetingsAsParticipant();
		List<Meeting> meetingsAsGuest = u.getMeetingsAsGuest();
		List<Meeting> meetingsAsMc = u.getMeetingsAsMc();
		List<Meeting> meetings = new ArrayList<Meeting>();
		meetings.addAll(meetingsAsMc);
		meetings.addAll(meetingsAsGuest);
		meetings.addAll(meetingsAsParticipant);
		return meetings;
	}

	@Override
	@Transactional
	public Meeting createMeeting(Meeting m) {
		// TODO Auto-generated method stub
		return null;
		
	}



	
}
