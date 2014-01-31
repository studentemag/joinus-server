package mag.joinus;

import java.util.ArrayList;
import java.util.List;

import mag.joinus.model.LatLng;
import mag.joinus.model.Meeting;
import mag.joinus.model.User;

public class TestUtil {
	
	public static User mario, giuseppe, luca, giovanni, alfredo;
	public static Meeting pizzaAtSorbillo;
	
	
	public static void populateAll(){
		populateUsers();
		populateMeetings();
	}
	
	/*
	 * Populates users
	 */
	public static void populateUsers(){
		giuseppe = new User("3391421288");
		mario = new User("3490838036");
		luca = new User("3201522887");
		giovanni = new User("3733912057");
		alfredo = new User("3201424344");
	}
	
	/*
	 * Populate meetings
	 */
	public static void populateMeetings(){
		pizzaAtSorbillo = new Meeting();
		pizzaAtSorbillo.setAddress("Via dei Tribunali");
		pizzaAtSorbillo.setDate(1392751842000l);
		pizzaAtSorbillo.setTitle("A pizza at Sorbillo");
		LatLng location = new LatLng(40.851501, 14.258927);
		pizzaAtSorbillo.setLatLng(location);
		
		List<User> guests = new ArrayList<User>();
		guests.add(giuseppe);
		guests.add(luca);
		pizzaAtSorbillo.setGuests(guests);
		
		List<User> participants = new ArrayList<User>();
		participants.add(giovanni);
		pizzaAtSorbillo.setParticipants(participants);
		
		pizzaAtSorbillo.setMc(mario);
	}
	
	

}
