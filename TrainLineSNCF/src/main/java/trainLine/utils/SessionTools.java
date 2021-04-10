package trainLine.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;



public class SessionTools {
	
	private static KeyGenerator sessionKey = new KeyGenerator();
	protected static String SessionTable = "session";

	public static String generatKey() {
		return sessionKey.nextKey() ;
	}

	public static boolean isExistKey(String key) throws SQLException{
		
		String requete="select * from "+SessionTable+" where clef = '"+key+"';";
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		boolean ret = resultat.next();
		
		resultat.close();
		statement.close();
		connexion.close();
		return ret;
		
	}

	public static boolean isValidKey(String key)  throws SQLException {
		
		boolean ret = false;
		long current_time = 0 ; 
		long key_time = 0;
		
		String requete="select temps from "+SessionTable+" where clef = '"+key+"';";
			
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
			
		resultat.next();

		current_time = Timestamp.valueOf(LocalDateTime.now()).getTime();
		key_time = resultat.getTimestamp("temps").getTime();
		ret = current_time - key_time < 18000000;

		resultat.close();
		statement.close();
		connexion.close();
		
		return ret;
	}
	
	
	
	public static String getPseudoFromSession(String key) throws SQLException {
		String pseudo = null ;
		
		String requete="select pseudo from "+SessionTable+" where clef = '"+key+"';";
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		if(resultat.next()) {
			pseudo =resultat.getString("pseudo");
		}
		
		resultat.close();
		statement.close();
		connexion.close();
		
		return pseudo ;
	}


}
