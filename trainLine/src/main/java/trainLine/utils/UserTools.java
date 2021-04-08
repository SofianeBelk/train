package trainLine.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTools {
	protected static String UserTable = "utilisateur"; 
	
	public static boolean UserExistenceTest( String pseudo ) throws SQLException {

		String requete="select pseudo from "+UserTable+" where pseudo = '"+pseudo+"';";
	
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		boolean retour = resultat.next();
		
		resultat.close();
		statement.close();		
		connexion.close();
		
		return retour ;
	
	}

	public static boolean verifyPassword(String username, String password) throws SQLException {
		boolean exist = false;
		String requete="select motDePasse from "+UserTable+" where pseudo = '"+username+"';";
		
		Connection connexion = BaseTools.getMySQLConnection();
		
		Statement statement = connexion.createStatement();
		ResultSet resultt = statement.executeQuery(requete);


		while (resultt.next()) {
			exist = resultt.getString("motDePasse").equals(password);
		}

		resultt.close();
		statement.close();
		connexion.close();

		return exist ;
	}
	
	

}
