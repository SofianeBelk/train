package trainLine.bdd;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import trainLine.utils.BaseTools;

public class TrainLineBD {
	
	protected static String LineTable = "tarifLine"; // � changer plustard
	
	public static String searchLine(String origin) throws Exception{

		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<Object, Object > result = new HashMap<Object, Object>();
		
		String requete="select origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde from "+LineTable+" where origin = '"+origin+"'";    
		
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

		String json = gson.toJson(result);
		return json;
	}

	public static boolean addNewLineBD(String origin, String destination, String pleinTarif1ere, String pleinTarif2nde, String prixdappel2nde) throws Exception 
	{
	    String requete = "insert into  "+LineTable+" values ('"+origin+"','"+pleinTarif1ere+"','"+destination+"','"+pleinTarif2nde+"','"+prixdappel2nde+"' )";
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		int retour = statement.executeUpdate(requete);

		statement.close();
		connexion.close();
		
		return retour != 0;
	}

		public static String getAllLine() throws SQLException {
		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<Object, Object > result = new HashMap<Object, Object>();
		
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

		String json = gson.toJson(result);
		return json;
	}

}
