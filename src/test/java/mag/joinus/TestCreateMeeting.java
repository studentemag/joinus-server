package mag.joinus;

import mag.Application;
import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.repository.springdatajpa.UserRepository;
import mag.joinus.service.JoinusService;

import org.junit.Assert;
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
	
	@Autowired
	private UserRepository userRepository;
	
	private Meeting pizzaAtSorbillo;
	private User luca,mario,giuseppe,giovanni;
	
	@Before
	public void setUp(){
		TestUtil.populateAll();
		this.luca = TestUtil.luca;
		this.giovanni=TestUtil.giovanni;
		this.mario=TestUtil.mario;
		this.giuseppe=TestUtil.giuseppe;
		this.pizzaAtSorbillo=TestUtil.pizzaAtSorbillo;
	}
	
	@Test
	public void createMeeting(){
		joinusService.login(luca);
		joinusService.login(giovanni);
		joinusService.login(mario);
		joinusService.login(giuseppe);
		
		joinusService.createMeeting(pizzaAtSorbillo);
	}
	
	@Test
	/* 
	 * Test with unknown user: Alfredo is an unknown user, i.e. it's not present into the database.
	 * Expected behavior is that Alfredo is saved to the database 
	 */
	public void createMeeting2(){
		pizzaAtSorbillo.getGuests().add(TestUtil.alfredo);
		
		joinusService.createMeeting(pizzaAtSorbillo);
		
		User u = userRepository.findOne("3201424344");
		Assert.assertNotNull(u);
	}

}