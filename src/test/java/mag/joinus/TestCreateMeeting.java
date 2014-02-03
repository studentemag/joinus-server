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
	 * Test with unknown user (i.e. not present into the database), whose name is not specified.
	 * Expected behavior is that the user is saved to the database with "noname"
	 */
	public void createMeeting2(){
		pizzaAtSorbillo.getGuests().add(new User("0000000002"));
		
		joinusService.createMeeting(pizzaAtSorbillo);
		
		User u = userRepository.findOne("0000000002");
		Assert.assertNotNull(u);
		Assert.assertEquals("noname",u.getName());
	}
	
	@Test
	/* 
	 * Test with unknown user (i.e. not present into the database), whose name is specified.
	 * Expected behavior is that the user is saved to the database with the specified name
	 */
	public void createMeeting3(){
		User alfredo = new User("0000000003");
		alfredo.setName("alfredino");
		pizzaAtSorbillo.getGuests().add(alfredo);
		
		joinusService.createMeeting(pizzaAtSorbillo);
		
		User u = userRepository.findOne("0000000003");
		Assert.assertNotNull(u);
		Assert.assertEquals("alfredino",u.getName());
	}

}