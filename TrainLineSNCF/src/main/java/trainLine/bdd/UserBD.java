package trainLine.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import trainLine.utils.BaseTools;

public class UserBD {
	
	
	protected static String UserTable = "utilisateur";

	public static boolean addNewUser(String nom, String prenom, String pseudo, String email, String pass, int age, String telephone) throws SQLException{
		
		String requete="insert into  "+UserTable+" values ('"+nom+"','"+prenom+"','"+pseudo+"','"+email+"','"+pass+"',"+age+",'"+telephone+"' )";
		
		Connection connexion = BaseTools.getMySQLConnection();		
		Statement statement = connexion.createStatement();
		
		int result = statement.executeUpdate(requete);		
		statement.close();
		connexion.close();
		
		return result != 0;
	}
	
	
	public static boolean removeUser(String pseudo) throws SQLException {
		
		String requete= "DELETE FROM "+UserTable+" WHERE pseudo = '"+pseudo+"';";
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		int result = statement.executeUpdate(requete);
		
		statement.close();
		connexion.close();
		
		return result != 0;
	}

}
