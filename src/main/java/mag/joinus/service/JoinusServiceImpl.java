package mag.joinus.service;

import java.util.ArrayList;
import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.repository.springdatajpa.LatLngRepository;
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
	
	@Autowired
	private LatLngRepository latLngRepository;

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
		
		if (username == null || username.isEmpty()) {
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
	public List<Meeting> getUpcomingEvents(String phone) {
		User u = userRepository.findOne(phone);
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
		latLngRepository.save(m.getLatLng());
		
		for (User u : m.getGuests()){
			if ( !userRepository.exists(u.getPhone()) )
				userRepository.save(u);
		}
		
		User u =m.getMc();
		if ( !userRepository.exists(u.getPhone()) )
			userRepository.save(u);
		
		Meeting createdM = meetingRepository.save(m);
		return createdM;
	}

	@Override
	@Transactional
	public Meeting acceptInvitationTo(int meetingId, User user) {
		String phone = user.getPhone();
		
		Meeting m = meetingRepository.findOne(meetingId);
		User u = userRepository.findOne(phone);

		//DELETE GUEST
		m.getGuests().remove(u);
		u.getMeetingsAsGuest().remove(m);
		//DELETE PARTICIPANT
		m.getParticipants().remove(u);
		u.getMeetingsAsParticipant().remove(m);
		//INSERT
		m.getParticipants().add(u);
		u.getMeetingsAsParticipant().add(m);
		
		userRepository.save(u);
		meetingRepository.save(m);
		
		return meetingRepository.findOne(meetingId);
	}

	@Override
	public Meeting denyInvitationTo(int meetingId, User user) {
		String phone = user.getPhone();
		
		Meeting m = meetingRepository.findOne(meetingId);
		User u = userRepository.findOne(phone);

		//DELETE GUEST
		m.getGuests().remove(u);
		u.getMeetingsAsGuest().remove(m);
		//DELETE PARTICIPANT
		m.getParticipants().remove(u);
		u.getMeetingsAsParticipant().remove(m);
		
		userRepository.save(u);
		meetingRepository.save(m);
		
		return meetingRepository.findOne(meetingId);
	}



	
}
