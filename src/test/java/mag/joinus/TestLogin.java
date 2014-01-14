package mag.joinus;

import mag.Application;
import mag.joinus.repository.springdatajpa.UserRepository;
import mag.joinus.service.JoinusService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TestLogin {
	@Autowired
    private JoinusService joinusService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testLogin(){
		joinusService.authenticate("pippo");
		joinusService.authenticate("pippo");
		long i = userRepository.count();
		Assert.assertEquals(1,i);
	}
}
