package mag.joinus.web;


import java.util.ArrayList;
import java.util.List;

import mag.joinus.model.Event;
import mag.joinus.service.JoinusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ComponentScan
@EnableAutoConfiguration
public class EventController {
	JoinusService joinusService;
	
	@Autowired
	public EventController(JoinusService joinusService){
		System.out.println("Inside EventController constructor");
		this.joinusService=joinusService;
		
		/*
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        EventRepository repository = context.getBean(EventRepository.class);
        
        Iterable<Event> events = repository.findAll();
        System.out.println("Events found with findAll():");
        System.out.println("-------------------------------");
        for (Event event : events) {
            System.out.println(event);
        }
        System.out.println();
        
        context.close();
		*/
	}
	
    @RequestMapping(value="/users/{userId}/events", method=RequestMethod.GET)
    public @ResponseBody List<Event> getUserEvents(
            @PathVariable String userId) {
    	Event e = new Event("il mio compleanno");
    	List<Event> l = new ArrayList<Event>(); 
    	l.add(e);
        return l;
    }
    
    @RequestMapping("/users")
    public @ResponseBody String greeting2(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new String("pippo");
    }

}