package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.logging.Level;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.jdbc.ScriptRunner;
import logger.Log;

public class PopulateDefaultDb {

	
    private static Connection conn;
    private static ScriptRunner sr;
	private static boolean state=false;

 static boolean populateDefaultDb() throws FileNotFoundException
	{
		if(	 creaCalendario() && createUser())
		{
			 state=true;
		}
		return state;
	}

	private static boolean creaCalendario()
	{
		Log.logger.log(Level.INFO,"<----- STO ESEGUENDO L'OPERAZIONE DI INSERIMENTO DI EVENTI DI PROVA -----> ");
		try
		{
			conn=ConnToDb.generalConnection();
			sr = new ScriptRunner(conn);

			sr.setSendFullScript(true);

			Reader reader = new BufferedReader(new FileReader("File_SQL/InserimentoCalendario.sql"));
			//Running the script
			sr.runScript(reader);


			return true;
		}
		catch(FileNotFoundException | RuntimeSqlException e1)
		{
			Log.logger.log(Level.SEVERE, "Errore in mysql", e1);
		}

		return false;
	}

	private static boolean createUser()
	{
			Log.logger.log(Level.INFO,"---------Chiamo stored insUtenti---------\n\n");

			try {
				conn=ConnToDb.generalConnection();
				  sr = new ScriptRunner(conn);
				 
				 	
				 sr.setSendFullScript(true);

				 Reader reader;
				
					reader = new BufferedReader(new FileReader("FileSql/storedInsUtenti.sql"));
					sr.runScript(reader);

				

				 
					
				
			return true;
			 
			}catch (RuntimeSqlException |IOException e) {
				Log.logger.log(Level.INFO, "Error : \n"+ e);

			}
			

		
		
				return false;

		
	}
	
	private static boolean createNegozio()
	{
			Log.logger.log(Level.INFO,"---------Chiamo stored insNEgozi---------\n\n");

			try {
				conn=ConnToDb.generalConnection();
				sr = new ScriptRunner(conn);
				 
				 sr.setSendFullScript(true);
				 
			      Reader reader;
				
					reader = new BufferedReader(new FileReader("FileSql/storedInsNegozio.sql"));
					sr.runScript(reader);

				
			      //Running the script
				

			return true;
			} 
			catch (RuntimeSqlException | FileNotFoundException e) {
				Log.logger.log(Level.INFO, "Error : \n"+ e);
			}
			return false;

		
	}
	private PopulateDefaultDb()
	{
		
	}
}
