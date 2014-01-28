package mag.joinus.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mag.joinus.model.LatLng;
import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.model.UserLocation;
import mag.joinus.repository.springdatajpa.LatLngRepository;
import mag.joinus.repository.springdatajpa.MeetingRepository;
import mag.joinus.repository.springdatajpa.UserLocationRepository;
import mag.joinus.repository.springdatajpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JoinusServiceImpl implements JoinusService {

	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LatLngRepository latLngRepository;

	@Autowired
	private UserLocationRepository userLocationRepository;
	
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
		Collections.sort(meetings);
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

	@Override
	public List<UserLocation> getLocations(int meeting_id) {
		List<UserLocation> participantLocations = new ArrayList<UserLocation>();//Collections.emptyList();
		
		Meeting meet = meetingRepository.findOne(meeting_id);
		System.out.println(meet);
		
		if (meet != null) {
			List<User> participants = meet.getParticipants();
			
			List<User> phones = new ArrayList<User>();//Collections.emptyList();
			for (User user : participants) {
				User u = new User();
				u.setPhone(user.getPhone());
				phones.add(u);
			}
			
			for (User user : phones) {
				List<UserLocation> locations = userLocationRepository.findByUser(user);
				UserLocation last = new UserLocation();
				if (locations != null) {
					last = locations.get(locations.size() - 1);
				}
				participantLocations.add(last);
				System.out.println(last);
			}
		}
		
		return participantLocations;
	}

	@Override
	public void shareLocation(String phone, UserLocation uLoc) {
		User user = new User();
		user.setPhone(phone);
		//user.setPhone(uLoc.getUser().getPhone());
		List<UserLocation> locations = userLocationRepository.findByUser(user);
		
		LatLng l = uLoc.getLatLng();
		latLngRepository.save(l);
		
		if (locations.isEmpty()) {
			System.out.println("INSERT");
			userLocationRepository.save(uLoc);
		}
		else {
			UserLocation last = locations.get(locations.size() - 1);
			int id = last.getId();
			last = uLoc;
			last.setId(id);
			userLocationRepository.save(last);
			System.out.println("UPDATE");
		}
		
		
		List<UserLocation> locs = (List<UserLocation>) userLocationRepository.findAll();
		for (UserLocation userLocation : locs) {
			System.out.println(userLocation);
		}
		List<LatLng> lls = (List<LatLng>) latLngRepository.findAll();
		for (LatLng latLng : lls) {
			System.out.println(latLng);
		}
	}
}
