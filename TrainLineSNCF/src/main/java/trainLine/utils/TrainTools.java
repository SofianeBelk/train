package trainLine.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class TrainTools {
	
protected static String UserTable = "tarifLine"; 
	
	public static JSONObject TrainSearchFromBdd( String origine ) throws SQLException {

		String requete="select * from "+UserTable+" where origin = '"+origine+"';";
		JSONObject re = new JSONObject();
	
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		while (resultat.next()) {
			re.append("origin", resultat.getString("origin"));
			re.append("pleinTarif1ere", resultat.getString("pleinTarif1ere"));
			re.append("destination", resultat.getString("destination"));
			re.append("pleinTarif2nde", resultat.getString("pleinTarif2nde"));
			

		}
		
		resultat.close();
		statement.close();		
		connexion.close();
		
		return re ;
	
	}


}