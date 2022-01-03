package user;


import java.time.LocalDate;
import java.util.logging.Level;

import logger.Log;

public class User {

	private int idU;
	private String idRuolo;
	private String nome;
	private String cognome; 
	private String email;
	private String password;
	private String pinCode;
	private String gruppo;

	
	private static User instance =new User();
	
	private  User()
	{
		
	}
	public String getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(String idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getGruppo() {	return gruppo;
	}

	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}


	public static User getInstance()
	{
		return instance;
	}
	public User login() {
		

	
		Log.logger.log(Level.INFO,"Sono loggato come User generico");
		return null;
		
	}
		
	public void setNull() {
		
		this.idRuolo = null; 
		this.nome = null;
		this.cognome = null;
		this.email = null;
		this.password = null;
		this.idU=0;
		
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}


	
}