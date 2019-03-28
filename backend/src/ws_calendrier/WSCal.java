package ws_calendrier;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JSONRessource.JSONConnection;
import dao_calendrier.IDaoLocal;
import jpa_calendrier.Event;
import jpa_calendrier.JoinEventUser;
import models.EventResult;
import models.EventTypeNbParticipations;
import utils_calendrier.HttpHelper;

@ApplicationPath("/")
@Path("/")
public class WSCal extends Application{
	
	@EJB
	IDaoLocal dao;

	@POST
	@Path("/user/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticateUser(JSONConnection JSON) {
		
		try {
			
			String response = HttpHelper.getUrlResponse("http://localhost:8080/backend_statistique/statistics/user/login", JSON);
			return response;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	@GET
	@Path("/eventUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventByUserId(@PathParam("id") int id) {
		
		List<Event> list = dao.getEventByUserId(id);
		return list;
	}
	
	@GET
	@Path("/eventUserV2/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventByUserIdV2(@PathParam("id") int id) {
		
		List<Event> list = dao.getEventByUserId(id);
		return list;
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String tesxt() {
		
		return "Test 1";
	}
	
	@GET
	@Path("/events")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventResult> getEvent(){
		List<EventResult> list = dao.getAllEvent();
	    /*JSONArray jArray = new JSONArray();
		try
		{
		    for (Event event : list)
		    {
		         JSONObject eventJSON = new JSONObject();
		         eventJSON.put("id", event.getIdEvent() );
		         eventJSON.put("title", event.getTitle() );
		         eventJSON.put("description", event.getDescription() );
		         
		         Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getStart().toString());
				 Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getEnd().toString());
		         eventJSON.put("start", start );
		         eventJSON.put("end", end );
		         
		         eventJSON.put("color", event.getColor() );
		         eventJSON.put("nb_participants", dao.getNbOfParticByIdEvent(event.getIdEvent()) );
		         jArray.put(eventJSON);
		    }
		    
		    
		} catch (Exception jse) {
		    jse.printStackTrace();
		}
		System.out.println("Test test  		:		"+jArray);*/
		return list;//jArray.toString();
	}
	
	@GET
	@Path("/eventTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public EventTypeNbParticipations getTypeNamesAndNbParticipations() {
		
		return dao.getAllEventTypes();
	}
	
	@POST
	@Path("/event")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addEvent(String json){
	
		JSONArray jsonArray;
		
		JSONObject eventJson;
		JSONObject joinJson;
		Event e;
		
		
		try {
		
			jsonArray = new JSONArray(json);
			eventJson = jsonArray.getJSONObject(0);
			joinJson = jsonArray.getJSONObject(1);
			
			Date start =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eventJson.getString("start"));
			Date end =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eventJson.getString("end"));
			
			
			e = new Event(eventJson.getString("title"), eventJson.getString("typeName"), 
						  eventJson.getString("color"), eventJson.getString("description"), start, end);
			dao.AddEvent(e);
			int idEvent = dao.getMaxEventId();
			JoinEventUser join = new JoinEventUser(idEvent, joinJson.getInt("idUser"), joinJson.getString("typeUser"));
			dao.AddJoinEventUser(join);
			return "{\r\"response\":\"ok\"\r}";
		} catch (Exception e1) {
			
			e1.printStackTrace();
			return "KO!!";
		}
		
		
	}
	
	@DELETE
	@Path("/event/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEvent(@PathParam("id") int id){
		
		try {
			dao.deleteEvent(id);
			dao.deleteJoinEventUser(id);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "Event not found !!";
		}
	}

}
