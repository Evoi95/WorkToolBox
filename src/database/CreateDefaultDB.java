package database;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import logger.Log;

public class CreateDefaultDB 
{

	public void createDefaultDB () throws  FileNotFoundException
	{
		Statement st ;
		String query ;
		boolean check ;
		try
		{
			check = ConnToDb.initailConnection() && !ConnToDb.connection() ;
			// status = 1 se c'e' workbench ma non il db 
			// status = 0 se c'e' tutto
			// Se non trovo il db chiamo questa funzione che lo crea
			if(check)
			{
				st = ConnToDb.conn.createStatement();
				query="CREATE DATABASE IF NOT EXISTS toolBox ";
				st.execute(query);
				query = "USE toolBox ";
				st.execute(query);
				Log.logger.log(Level.INFO,"Connesso a mysql workbench, ma non ho torvato il database 'ispw' "
						+ "Database creato "
						+ " Chiamo la Stored Procedure, per creare le tabelle");
				
				query="CREATE TABLE IF NOT EXISTS USERS" +
						"( " +
						" idUser INT primary key not null auto_increment," +
						" idRuolo VARCHAR(1) NOT NULL," +
						" Nome VARCHAR(40), Cognome VARCHAR(40)," +
						"Email VARCHAR(50), pwd VARCHAR(16)," +
						"pinCode text, gruppo VARCHAR(10)) ;";
				st.executeUpdate(query);
				Log.logger.log(Level.INFO,"Tabella Users Creata!");


				query="CREATE TABLE IF NOT EXISTS MAILINGLIST" +
						"(idList INT primary key not null auto_increment," +
						"managerList int not null," +
						"FOREIGN KEY (managerList) REFERENCES USERS(idUser) );";
				st.executeUpdate(query);
				Log.logger.log(Level.INFO,"Tabella Mailinglist Creata!");


				query = "CREATE table if not exists CALENDAR" +
						"(idAppointment int primary key not null," +
						" nome VARCHAR(100), orario datetime," +
						" creator int not null," +
						" foreign key (creator) references USERS(idUser)" +
						");";


				st.executeUpdate(query);
				Log.logger.log(Level.INFO,"Tabella Calendar Creata!");


				Log.logger.log(Level.INFO,"Tabelle create!");
				
				if (PopulateDefaultDb.populateDefaultDb()) {
					Log.logger.log(Level.INFO,"Tabella populata con valori di default");
					ConnToDb.conn.close();
					Log.logger.log(Level.INFO,"Trigger creati e connesione chiusa col db");
					Log.logger.log(Level.INFO,"Trovato database e connesso senza problemi! Buone madonne!");
					ConnToDb.conn.close();

				}
				else
				{
					Log.logger.log(Level.WARNING,"Ops..! qualcosa è andato storto nel populare il database!");
				}
			}
			
			// Se trovo tutto  chiudo la connesione e vado avanti con l'esecuzione del programma
			
			// Se qualcosa non va chiudo la connessione e vado nel cacth
			else 
			{
				Log.logger.log(Level.WARNING,"Ops..! qualcosa è andato storto nella connesione al database!");
				ConnToDb.conn.close();		

			}
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
			Log.logger.log(Level.WARNING,"ERRORE DI SQL ");
		}
		
		
	}

	private CreateDefaultDB() {
		Log.logger.log(Level.INFO,"Creo db di default");

		
	}
}
