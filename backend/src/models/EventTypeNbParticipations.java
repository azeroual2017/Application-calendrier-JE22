package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventTypeNbParticipations implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	
	
	private List<String> listOfTypeNames = new ArrayList<String>();
	private List<Long> listOfNbParticipations = new ArrayList<Long>();
	
	
	
	public EventTypeNbParticipations() {
		super();
	}



	public List<String> getListOfTypeNames() {
		return listOfTypeNames;
	}



	public void setListOfTypeNames(List<String> listOfTypeNames) {
		this.listOfTypeNames = listOfTypeNames;
	}



	public List<Long> getListOfNbParticipations() {
		return listOfNbParticipations;
	}



	public void setListOfNbParticipations(List<Long> listOfNbParticipations) {
		this.listOfNbParticipations = listOfNbParticipations;
	}
	
	
	
	public void constructLists(List<EventTypeResult> list) {
		
		for(EventTypeResult e : list) {
			
			listOfTypeNames.add(e.getTypeName());
			listOfNbParticipations.add(e.getNb_participations());
		}
		
	}
	
	

}
