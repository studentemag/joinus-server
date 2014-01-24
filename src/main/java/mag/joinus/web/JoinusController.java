package mag.joinus.web;


import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.service.JoinusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinusController {
	
	@Autowired
	JoinusService joinusService;
	

	public JoinusController(){
		System.out.println("Inside EventController constructor");
	}
	   
    @RequestMapping(value="/events", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody Meeting createMeeting(@RequestBody Meeting m) {
    	System.out.println("JoinusController::createMeeting for meeting "+m.getTitle());
    	return joinusService.createMeeting(m);
    }


    @RequestMapping(value="/users", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody User getUser(@RequestBody User u) {
    	System.out.println("retrieve user " + u);
    	return joinusService.login(u);
	}
    
    @RequestMapping(value="/users/{phone}/events", method=RequestMethod.GET)
    public @ResponseBody List<Meeting> getUpcomingEvents(
            @PathVariable String phone) {
    	System.out.println("JoinusController::getUpcomingEvents for user "+phone);
    	List<Meeting> list = joinusService.getUpcomingEvents(phone);
    	return list;
    	
    }
}