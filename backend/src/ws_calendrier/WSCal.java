package ws_calendrier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import dao_calendrier.IDaoLocal;
import jpa_calendrier.Event;

@ApplicationPath("/cal")
@Path("/calendrier")
public class WSCal extends Application{
	
	@EJB
	IDaoLocal dao;
	
	@GET
	@Path("/eventUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEventByUserId(@PathParam("id") int id) {
		
		return "TestUserById 1";
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String tesxt() {
		
		return "Test 1";
	}
	
	@GET
	@Path("/conversion/{m}")
	@Produces(MediaType.APPLICATION_JSON)
	public double conversion(@PathParam("m")double md) {
		
		return 11*md;
	}
	@GET
	@Path("/infos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEvent(){
		List<Event> list = dao.getAllEvent();
		return list;
	}

}
