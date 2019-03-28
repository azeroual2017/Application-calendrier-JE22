package dao_calendrier;


import java.util.*;

import javax.ejb.Local;

import jpa_calendrier.*;
import models.EventResult;
import models.EventTypeNbParticipations;

@Local
public interface IDaoLocal {
	
	//Liste of Event
	 public List<EventResult> getAllEvent();
	 
	 public EventTypeNbParticipations getAllEventTypes();
	 
	//Liste of JoinEventUser
	 public List<JoinEventUser> getAllJoinEventUser();
	 
	//Find an Event by Id
	 public Event getEventById(int idEvent);
	 
	 public List<Event> getEventByUserId(int idEvent);
	
	//add an Event
	 public void AddEvent(Event e);
	 
	 //add an JoinEventUser
	 public void AddJoinEventUser(JoinEventUser jeu);

	public void deleteEvent(int id);

	public int getMaxEventId();

	public void deleteJoinEventUser(int id);

	public int getNbOfParticByIdEvent(int idEvent);

}
