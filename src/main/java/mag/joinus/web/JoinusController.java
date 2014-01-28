package mag.joinus.web;


import java.util.List;

import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.model.UserLocation;
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
	   
    @RequestMapping(value="/events", method=RequestMethod.POST, 
    		consumes = "application/json", produces = "application/json")
    public @ResponseBody Meeting createMeeting(@RequestBody Meeting m) {
    	System.out.println("JoinusController.createMeeting for meeting " + m.getTitle());
    	return joinusService.createMeeting(m);
    }

    @RequestMapping(value="/events/{meeting_id}/accept", method=RequestMethod.POST, 
    		consumes = "application/json", produces = "application/json")
    public @ResponseBody Meeting acceptInvitation(@PathVariable int meeting_id, @RequestBody User u) {
    	System.out.println("JoinusController.acceptInvitationTo for meeting " + meeting_id + "and user" + u.getPhone());
    	    	
    	return joinusService.acceptInvitationTo(meeting_id, u);
    }
    
    @RequestMapping(value="/events/{meeting_id}/deny", method=RequestMethod.POST, 
    		consumes = "application/json", produces = "application/json")
    public @ResponseBody Meeting denyInvitation(@PathVariable int meeting_id, @RequestBody User u) {
    	System.out.println("JoinusController.acceptInvitationTo for meeting " + meeting_id + "and user" + u.getPhone());
    	    	
    	return joinusService.denyInvitationTo(meeting_id, u);
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody User getUser(@RequestBody User u) {
    	System.out.println("retrieve user " + u);
    	return joinusService.login(u);
	}
    
    @RequestMapping(value="/users/{phone}/events", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Meeting> getUpcomingEvents(
            @PathVariable String phone) {
    	System.out.println("JoinusController.getUpcomingEvents for user " + phone);
    	List<Meeting> list = joinusService.getUpcomingEvents(phone);
    	return list;
    	
    }
    
    @RequestMapping(value="/events/{meeting_id}/locations", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserLocation> getLocations(
            @PathVariable int meeting_id) {
    	System.out.println("JoinusController.getLocations for event " + meeting_id);
    	List<UserLocation> list = joinusService.getLocations(meeting_id);
    	return list;
    	
    }
    
    @RequestMapping(value="/users/{phone}/locations", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody void shareLocation(@PathVariable String phone,
    		@RequestBody UserLocation uLoc) {
    	System.out.println("JoinusController.shareLocation for user " + uLoc.getUser().getName());
    	joinusService.shareLocation(phone, uLoc);
    }
}