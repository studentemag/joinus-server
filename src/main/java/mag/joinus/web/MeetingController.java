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
    	Meeting e = new Meeting();
    	e.setTitle("il mio compleanno");
    	List<Meeting> l = new ArrayList<Meeting>(); 
    	l.add(e);
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