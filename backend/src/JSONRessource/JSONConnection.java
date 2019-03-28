package JSONRessource;

import java.io.Serializable;

public class JSONConnection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3366524320580216778L;


	private String email;
	
	private String textPassword;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(String textPassword) {
		this.textPassword = textPassword;
	}
	
	

}
