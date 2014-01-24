package mag.joinus;

import mag.Application;
import mag.joinus.model.User;
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
	public void testLogin01(){
		//joinusService.authenticate("pippo");
		//joinusService.authenticate("pippo");
		
		//phone exists in db, name is empty -> retrieve
		User user = new User();
		user.setPhone("3490838036");
		user.setName("");
		user = joinusService.login(user);
		
		long i = userRepository.count();
		Assert.assertEquals(4, i);
		Assert.assertEquals("mario", user.getName());
		Assert.assertEquals("3490838036", user.getPhone());
	}
	
	@Test
	public void testLogin02(){
		//phone exists in db, name is not empty -> update with inserted name
		
		User user = new User();
		user.setPhone("3490838036");
		user.setName("m");
		user = joinusService.login(user);
		
		long i = userRepository.count();
		Assert.assertEquals(4, i);
		Assert.assertEquals("m", user.getName());
		Assert.assertEquals("3490838036", user.getPhone());
	}
	
	@Test
	public void testLogin03(){
		//phone doesn't exist in db, name is empty -> create with "noname"
		
		User user = new User();
		user.setPhone("3490000000");
		user.setName("");
		user = joinusService.login(user);
		
		long i = userRepository.count();
		Assert.assertEquals(5, i);
		Assert.assertEquals("noname", user.getName());
		Assert.assertEquals("3490000000", user.getPhone());
	}
	
	@Test
	public void testLogin04(){
		//phone doesn't exist in db, name is not empty -> create with inserted name
		
		User user = new User();
		user.setPhone("3490000000");
		user.setName("gino");
		user = joinusService.login(user);
		
		long i = userRepository.count();
		Assert.assertEquals(5, i);
		Assert.assertEquals("gino", user.getName());
		Assert.assertEquals("3490000000", user.getPhone());
	}
}
