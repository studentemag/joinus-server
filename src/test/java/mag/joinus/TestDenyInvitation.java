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
public class TestDenyInvitation {
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
	public final void testDenyInvitationTo1() {
		user.setPhone("3733912057");
		meet = joinusService.acceptInvitationTo(1, user);
		
		Assert.assertTrue(meet.getGuests().isEmpty());
	}
	
	@Test
	/*
	 * Testing deny by a guest
	 */
	public final void testDenyInvitationTo2(){
		
	}
	
	@Test
	/*
	 * Testing deny by a participant
	 */
	public final void testDenyInvitationTo3(){
		
	}
	
	@Test
	/*
	 * Testing deny by the mc
	 * The deny should be ineffective
	 */
	public final void testDenyInvitationTo4(){
		
	}
	
	@Test
	/*
	 * Testing two consecutive denies by the same user
	 * The second deny should be ineffective
	 */
	public final void testDenyInvitationTo5(){
		
	}
	
	@Test
	/*
	 * Testing deny by extraneous-to-meeting user
	 * The deny should be ineffective
	 */
	public final void testDenyInvitationTo6(){
		
	}
	
	@Test
	/*
	 * Testing deny by extraneous-to-joinus user
	 * The deny should be ineffective
	 */
	public final void testDenyInvitationTo7(){
		
	}
	

}
