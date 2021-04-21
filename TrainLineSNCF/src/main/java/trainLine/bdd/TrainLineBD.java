package trainLine.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import trainLine.utils.BaseTools;

public class TrainLineBD {
	
	protected static String LineTable = "tarifLine"; 
	
	public static JSONObject searchLine(String origin, String distination) throws Exception{

		JSONObject result = new JSONObject();
		String requete = "select origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde from "+LineTable+" where origin = '"+origin+"' and destination ='"+distination+"'";    
		
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

	public static boolean addNewLineBD(String origin, String destination, int pleinTarif1ere, int pleinTarif2nde, int prixdappel2nde) throws Exception 
	{
		JSONObject res = searchLine(origin, destination);
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();	
		
		if (!res.isEmpty()) {
			System.out.println("les données sont disponibles, mise a jours des données en cours");
			String req = "delete from "+LineTable+" where origin = " +origin+ " and destination = " +destination;
			statement.executeUpdate(req);

		}
			String requete = "insert into  "+LineTable+" values ('"+origin+"','"+pleinTarif1ere+"','"+destination+"','"+pleinTarif2nde+"','"+prixdappel2nde+"' )";
		
				
		
			int retour = statement.executeUpdate(requete);

			statement.close();
			connexion.close();
		
			return retour != 0;
		
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
