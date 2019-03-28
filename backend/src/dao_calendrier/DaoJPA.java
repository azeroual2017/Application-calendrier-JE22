package dao_calendrier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import jpa_calendrier.Event;
import jpa_calendrier.JoinEventUser;
import models.EventResult;
import models.EventTypeNbParticipations;
import models.EventTypeResult;

@Stateless
public class DaoJPA implements  IDaoLocal {
	
	@PersistenceContext( unitName="backend_calendrier")
    private EntityManager em ;


	
	@Override
	public void AddEvent(Event e) {
		
		em.persist(e);
	}
	
	@Override
	public List<EventResult> getAllEvent() {
		
		Query req= em.createQuery("select new models.EventResult(e.idEvent, e.title, e.description, e.start, e.end, e.color, COUNT(e.idEvent)) "
								+ "from  Event e join JoinEventUser ju on e.idEvent = ju.idEvent "
								+ "group by e.idEvent");
		return (List<EventResult>)req.getResultList();
	}
	
	@Override
	public EventTypeNbParticipations getAllEventTypes() {
		
		Query req= em.createQuery("select new models.EventTypeResult(e.typeName, COUNT(e.typeName)) "
				+ "from  Event e join JoinEventUser ju on e.idEvent = ju.idEvent "
				+ "group by e.typeName");
		List<EventTypeResult> list = (List<EventTypeResult>)req.getResultList();
		EventTypeNbParticipations eventTypeNbParticipations = null;
		if(list != null) {
			eventTypeNbParticipations = new EventTypeNbParticipations();
			eventTypeNbParticipations.constructLists(list);
		}
		
		return eventTypeNbParticipations;
	}

	@Override
	public List<JoinEventUser> getAllJoinEventUser() {
		Query req= em.createQuery("select c from JoinEventUser c");
		return req.getResultList();
	}
	
	@Override
	public List<Event> getEventByUserId(int idUser) {
		Query req= em.createQuery("select e from JoinEventUser c join Event e on c.idEvent = e.idEvent  where c.idUser ="+idUser);
		return req.getResultList();
	}

	@Override
	public Event getEventById(int idEvent) {
		Event e = em.find(Event.class, idEvent);
		if (e==null) throw new RuntimeException("Event not found");
		return e;
	}

	@Override
	public void AddJoinEventUser(JoinEventUser jeu) {

		em.persist(jeu);
	}

	@Override
	public void deleteEvent(int id) {
		
		em.remove(getEventById(id));	
	}
	
	public int getMaxEventId() {
		Query req= em.createQuery("select Max(e.idEvent) from Event e");
		int result = ((Number)req.getSingleResult()).intValue();
		return result;
	}

	@Override
	public void deleteJoinEventUser(int id) {

		Query query = em.createQuery("DELETE FROM JoinEventUser c WHERE c.idEvent = :p");
		query.setParameter("p", id).executeUpdate();
	}

	@Override
	public int getNbOfParticByIdEvent(int idEvent) {
		Query req= em.createQuery("select count(j.idEvent) from JoinEventUser j WHERE j.idEvent = :p");
		req.setParameter("p", idEvent);
		int result = ((Number)req.getSingleResult()).intValue();
		return result;
	}

	


}

