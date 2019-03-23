package dao_calendrier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import jpa_calendrier.Event;
import jpa_calendrier.JoinEventUser;

@Stateless
public class DaoJPA implements  IDaoLocal {
	
	@PersistenceContext( unitName="backend_calendrier")
    private EntityManager em ;




	
	@Override
	public void AddEvent(Event e) {

		em.persist(e);

	}
	
	@Override
	public List<Event> getAllEvent() {
		
		Query req= em.createQuery("select c from Event c");
		return (List<Event>)req.getResultList();
	}

	@Override
	public List<JoinEventUser> getAllJoinEventUser() {
		Query req= em.createQuery("select c from JoinEventUser c");
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


}

