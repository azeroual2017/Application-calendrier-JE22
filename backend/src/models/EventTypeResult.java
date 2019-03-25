package models;

import java.io.Serializable;

public class EventTypeResult implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String typeName;
	private Long nb_participations;
	
	
	
	
	
	
	
	public EventTypeResult() {
		super();
	}
	
	public EventTypeResult(String typeName, Long nb_participations) {
		super();
		this.typeName = typeName;
		this.nb_participations = nb_participations;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getNb_participations() {
		return nb_participations;
	}

	public void setNb_participations(Long nb_participations) {
		this.nb_participations = nb_participations;
	}
	
	

}
