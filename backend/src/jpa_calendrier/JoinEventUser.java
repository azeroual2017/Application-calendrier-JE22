package jpa_calendrier;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the joineventuser database table.
 * 
 */
@Entity
@NamedQuery(name="JoinEventUser.findAll", query="SELECT j FROM JoinEventUser j")
public class JoinEventUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int idEvent;

	private int idUser;

	private String typeUser;

	public JoinEventUser() {
	}

	public JoinEventUser(int idEvent, int idUser, String typeUser) {
		
		this.idEvent = idEvent;
		this.idUser = idUser;
		this.typeUser = typeUser;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getTypeUser() {
		return this.typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

}