package trainLine.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import trainLine.utils.BaseTools;

public class CitiesCoordinate {

	
	public static StringBuffer getCoordinates(String cities[]) throws Exception
	{		
		
	    JSONObject result = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    
	    String selector="";
	    for(int i = 0;i<cities.length;i++) {
	    	selector +="'"+ cities[i]+"'";
	    	if(i<cities.length-1) selector+=",";
	    }
	    
	    System.out.println(selector);
		
	    String requete="SELECT ville_nom, ville_latitude_deg ,ville_longitude_deg FROM villes_france_free WHERE ville_nom in ("+selector+")";
	
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
	
		StringBuffer sb = new StringBuffer();
		sb.append("[");		
		while(resultat.next()) {
			result.put("villenom",resultat.getString("ville_nom"));
			result.put("lattitude",resultat.getString("ville_latitude_deg"));
			result.put("longitude",resultat.getString("ville_longitude_deg"));
			sb.append(result) ;
			sb.append(",") ;
		}
		System.out.println(sb.charAt(sb.length()-1));
		if(sb.length() > 2) {
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append("]");
		
		resultat.close();
		statement.close();		
		connexion.close();
		
		return sb;
	}
	

}