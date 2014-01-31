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
/*
 * Test acceptInvitationTo method of JoinusService
 * Depends on createMeeting
 */
public class TestAccepInvitation {
	@Autowired
    private JoinusService joinusService;
	
	
	private Meeting pizzaAtSorbillo;
	private User luca,mario,giovanni;
	
	@Before
	public void setUp() throws Exception {
		TestUtil.populateAll();
		TestUtil.populateAll();
		this.luca = TestUtil.luca;
		this.giovanni=TestUtil.giovanni;
		this.mario=TestUtil.mario;
		this.pizzaAtSorbillo=TestUtil.pizzaAtSorbillo;
		
		pizzaAtSorbillo = joinusService.createMeeting(pizzaAtSorbillo);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	/*
	 * mario is mc
	 * giuseppe and luca are guests
	 * giovanni is participant
	 * @see mag.joinus.TestUtil#populateMeetings()
	 * 
	 * Testing accept by a guest. 
	 */
	public void testAcceptInvitationTo1() {
		Assert.assertEquals(2, pizzaAtSorbillo.getGuests().size());
		Assert.assertEquals(1, pizzaAtSorbillo.getParticipants().size());
		
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), luca);
		
		Assert.assertEquals(1, meet.getGuests().size());
		Assert.assertEquals(2, meet.getParticipants().size());
	}
	
	
	@Test
	/*
	 * Testing two consecutive accept by the same user
	 * The second accept should be ineffective
	 */
	public void testAcceptInvitationTo2() {	
		joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), luca);
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), luca);
		
		Assert.assertEquals(1, meet.getGuests().size());
		Assert.assertEquals(2, meet.getParticipants().size());
	}
	
	@Test
	/*
	 * Testing accept by the mc
	 * Should be ineffective
	 */
	public void testAcceptInvitationTo3() {
		Assert.assertEquals(2, pizzaAtSorbillo.getGuests().size());
		Assert.assertEquals(1, pizzaAtSorbillo.getParticipants().size());
		
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), mario);
		
		Assert.assertEquals(2, meet.getGuests().size());
		Assert.assertEquals(1, meet.getParticipants().size());
	}
	
	@Test
	/*
	 * Testing accept by a participant
	 * Should be ineffective
	 */
	public void testAcceptInvitationTo4() {
		Assert.assertEquals(2, pizzaAtSorbillo.getGuests().size());
		Assert.assertEquals(1, pizzaAtSorbillo.getParticipants().size());
		
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), giovanni);
		
		Assert.assertEquals(2, meet.getGuests().size());
		Assert.assertEquals(1, meet.getParticipants().size());
	}
	
	@Test
	/*
	 * Testing accept by an extraneous-to-meeting user
	 * Should be ineffective
	 */
	public void testAcceptInvitationTo5() {
		Assert.assertEquals(2, pizzaAtSorbillo.getGuests().size());
		Assert.assertEquals(1, pizzaAtSorbillo.getParticipants().size());
		
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), TestUtil.alfredo);
		
		Assert.assertEquals(2, meet.getGuests().size());
		Assert.assertEquals(1, meet.getParticipants().size());
	}
	
	@Test
	/*
	 * Testing accept by an extraneous-to-joinus user
	 * Should be ineffective
	 */
	public void testAcceptInvitationTo6() {
		Assert.assertEquals(2, pizzaAtSorbillo.getGuests().size());
		Assert.assertEquals(1, pizzaAtSorbillo.getParticipants().size());
		
		Meeting meet = joinusService.acceptInvitationTo(pizzaAtSorbillo.getId(), new User("0000000000"));
		
		Assert.assertEquals(2, meet.getGuests().size());
		Assert.assertEquals(1, meet.getParticipants().size());
	}

}
