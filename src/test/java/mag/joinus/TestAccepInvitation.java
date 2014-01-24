package mag.joinus;

import mag.Application;
import mag.joinus.model.Meeting;
import mag.joinus.model.User;
import mag.joinus.service.JoinusService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TestAccepInvitation {
	@Autowired
    private JoinusService joinusService;
	
	private User user;
	private Meeting meet;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAcceptInvitationTo() {
		user.setPhone("3733912057");
		meet = joinusService.acceptInvitationTo(1, user);
		
		Assert.assertTrue(meet.getGuests().isEmpty());
		Assert.assertEquals(3, meet.getParticipants().size());
	}

}
