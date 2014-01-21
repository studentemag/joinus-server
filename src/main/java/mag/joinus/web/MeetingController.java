package mag.joinus.web;


import java.util.ArrayList;
import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.service.JoinusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeetingController {
	
	@Autowired
	JoinusService joinusService;
	

	public MeetingController(){
		System.out.println("Inside EventController constructor");
	}
	
    @RequestMapping(value="/users/{userId}/events", method=RequestMethod.GET)
    public @ResponseBody List<Meeting> getUserEvents(
            @PathVariable String userId) {
    	List<Meeting> l = new ArrayList<Meeting>();
    	
    	Meeting m = new Meeting();
    	m.setTitle("il mio compleanno");
    	m.setId(0);
    	m.setLatitude(112);
    	m.setLongitude(345);
    	l.add(m);
    	
    	Meeting m2 = new Meeting();
    	m2.setTitle("pizza tra amici");
    	m2.setId(1);
    	m2.setLatitude(343);
    	m2.setLongitude(442);
    	l.add(m2);
    	
    	Meeting m3 = new Meeting();
    	m3.setTitle("tombolata da gino");
    	m3.setId(2);
    	m3.setLatitude(17);
    	m3.setLongitude(20);
    	l.add(m3);
    	
    	Meeting m4 = new Meeting();
    	m4.setTitle("pranzo della domenica");
    	m4.setId(3);
    	m4.setLatitude(999);
    	m4.setLongitude(666);
    	l.add(m4);
    	
    	System.out.println("get meetinglist for user " + userId);
        return l;
    }
    
    @RequestMapping(value="/events", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody Meeting createMeeting(
    		@RequestBody Meeting m
    		){
    	System.out.println("create meeting "+m);
    	return m;
    }
    
    @RequestMapping("/users")
    public @ResponseBody String greeting2(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new String("pippo");
    }

}