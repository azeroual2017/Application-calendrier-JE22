package models;

import java.io.Serializable;
import java.util.Date;

public class EventResult implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String description;
	private Date start;
	private Date end;
	private String color;
	private Long nb_participants;
	
	
	
	
	
	public EventResult() {
		super();
	}

	public EventResult(int id, String title, String description, Date start, Date end, String color, Long nb_participants) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.start = start;
		this.end = end;
		this.color = color;
		this.nb_participants = nb_participants;
	}
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getNb_participants() {
		return nb_participants;
	}

	public void setNb_participants(Long nb_participants) {
		this.nb_participants = nb_participants;
	}
	


}
