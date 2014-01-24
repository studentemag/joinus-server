package mag.joinus.service;

import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;


public interface JoinusService {
	
	public User login(User user);
	public List<Meeting> getUpcomingEvents(String phone);
	public Meeting createMeeting(Meeting m);
	public Meeting acceptInvitationTo(int meetingId, User user);
	public Meeting denyInvitationTo(int meetingId, User user);

}
