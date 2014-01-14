package mag.joinus;

import mag.Application;
import mag.joinus.service.JoinusService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TestService {
	@Autowired
    private JoinusService joinusService;
	
	@Test
	public void testService(){
		
		System.out.println(joinusService.getPippo());
	}
}
