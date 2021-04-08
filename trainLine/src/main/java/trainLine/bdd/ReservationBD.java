package trainLine.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import trainLine.utils.BaseTools;


public class ReservationBD {

	protected static String ReservationTable ="reservation";
	 
		public static boolean addNewReservration(String pseudo,String idTrain) throws SQLException{
			
			String requete="insert into  "+ReservationTable+" values ('"+pseudo+"','"+idTrain+"')";
			Connection connexion = BaseTools.getMySQLConnection();
			Statement statement = connexion.createStatement();
			int result = statement.executeUpdate(requete);
			statement.close();
			connexion.close();
			
			return result != 0;
		}
		
}
