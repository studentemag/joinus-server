package mag.joinus;

import java.util.ArrayList;
import java.util.List;

import mag.Application;
import mag.joinus.model.LatLng;
import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.service.JoinusService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TestCreateMeeting {
	@Autowired
    private JoinusService joinusService;
	
	private Meeting meeting;
	
	@Before
	private void setUp(){
		meeting = new Meeting();
		meeting.setTitle("sorbillo");
		
		LatLng l = new LatLng();
		l.setLatitude(41.88120931224046);
		l.setLongitude(12.519075125455856);
		meeting.setLatLng(l);

		meeting.setDate(1390570980000l);
		meeting.setAddress("Via Casalmonferrato, 22, Roma, Italia");
		
		User mario = new User(); mario.setPhone("3490838036");
		List<User> guests = new ArrayList<User>();
		guests.add(mario);
		meeting.setGuests(guests);
		
		User giuseppe = new User(); giuseppe.setPhone("3391421288");
		meeting.setMc(giuseppe);
		
	}
	
	@Test
	private void CreateMeeting(){
		joinusService.createMeeting(meeting);
	}

}