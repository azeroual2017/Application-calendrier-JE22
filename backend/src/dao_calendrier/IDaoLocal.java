package dao_calendrier;


import java.util.*;

import javax.ejb.Local;

import jpa_calendrier.*; 

@Local
public interface IDaoLocal {
	
	//Liste of Event
	 public List<Event> getAllEvent();
	 
	//Liste of JoinEventUser
	 public List<JoinEventUser> getAllJoinEventUser();
	 
	//Find an Event by Id
	 public Event getEventById(int idEvent);
	
	//add an Event
	 public void AddEvent(Event e);
	 
	 //add an JoinEventUser
	 public void AddJoinEventUser(JoinEventUser jeu);
}
