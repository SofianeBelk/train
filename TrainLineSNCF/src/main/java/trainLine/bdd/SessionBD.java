package trainLine.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import trainLine.utils.BaseTools;

public class SessionBD {

	protected static String SessionTable = "session"; // � changer plustard

	
	public static boolean launchSession(String pseudo , String  key) throws SQLException {
		String requete="insert into  "+SessionTable+"(pseudo,clef) values ('"+pseudo+"','"+key+"');";
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		
		int result = statement.executeUpdate(requete);
		statement.close();
		connexion.close();
		
		return result != 0;
	}
	
	// lorsque la deconnexion
	public static boolean closeSession(String key) throws SQLException {
		String requete="delete from "+SessionTable+" where clef = '"+key+"';";
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
	
		int result = statement.executeUpdate(requete);
		statement.close();
		connexion.close();

		return result != 0;
	}
	
}
