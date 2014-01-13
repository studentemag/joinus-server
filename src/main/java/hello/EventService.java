package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
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
public class EventService {
	
	public EventService(){
		super();
		System.out.println("Costruttore chiamato");
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
    
    public static void main(String[] args) {
        SpringApplication.run(EventService.class, args);
    }
}
