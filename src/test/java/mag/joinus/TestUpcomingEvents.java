package mag.joinus;

import java.util.List;

import org.junit.Assert;
import mag.Application;
import mag.joinus.model.Meeting;
import mag.joinus.repository.springdatajpa.UserRepository;
import mag.joinus.service.JoinusService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TestUpcomingEvents {
	@Autowired
    private JoinusService joinusService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testGetUpcomingEvents01(){
		List<Meeting> eventsForMario = joinusService.getUpcomingEvents(2);
		Assert.assertEquals(eventsForMario.size(),1);
	}

}
