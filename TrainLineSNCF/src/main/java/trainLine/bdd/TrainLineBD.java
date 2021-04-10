package trainLine.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import trainLine.utils.BaseTools;

public class TrainLineBD {
	
	protected static String LineTable = "tarifLine"; // � changer plustard
	
	public static JSONObject searchLine(String origin) throws Exception{

		JSONObject result = new JSONObject();
		String requete = "select origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde from "+LineTable+" where origin = '"+origin+"'";    
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();		
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next()) {			    
			result.put("origin", resultat.getString("origin"));
			result.put("destination", resultat.getString("destination"));
			result.put("pleinTarif1ere", resultat.getString("pleinTarif1ere"));
			result.put("pleinTarif2nde", resultat.getString("pleinTarif2nde"));
			result.put("prixdappel2nde", resultat.getString("prixdappel2nde"));
		}
		
		statement.close();
		connexion.close();

		return result;
	}

	public static boolean addNewLineBD(String origin, String destination, String pleinTarif1ere, String pleinTarif2nde, String prixdappel2nde) throws Exception 
	{
		JSONObject res = searchLine(origin);
		if (res.isEmpty()) {
			System.out.println("l'information est déja présente dans la base de donnée");
			return true;
		}else {
			String requete = "insert into  "+LineTable+" values ('"+origin+"','"+pleinTarif1ere+"','"+destination+"','"+pleinTarif2nde+"','"+prixdappel2nde+"' )";
		
			Connection connexion = BaseTools.getMySQLConnection();
			Statement statement = connexion.createStatement();		
		
			int retour = statement.executeUpdate(requete);

			statement.close();
			connexion.close();
		
			return retour != 0;
		}
	}

	public static JSONObject getAllLine() throws SQLException {
		
		JSONObject result = new JSONObject();
		String requete="select origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde from "+LineTable+" ";    
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();		
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next()) {			    
			result.put("origin", resultat.getString("origin"));
			result.put("destination", resultat.getString("destination"));
			result.put("pleinTarif1ere", resultat.getString("pleinTarif1ere"));
			result.put("pleinTarif2nde", resultat.getString("pleinTarif2nde"));
			result.put("prixdappel2nde", resultat.getString("prixdappel2nde"));
		}
		
		statement.close();
		connexion.close();

		return result;
	}

}
