package trainLine.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import trainLine.bdd.TrainLineBD;
import trainLine.utils.BaseTools;
import trainLine.utils.TrainTools;



public class TrainLine {

	public static void addNewLine(String origin, String destination, int pleinTarif1ere, int pleinTarif2nde, int prixdappel2nde) throws Exception 
	{
		if (origin == null || destination == null ) {
			throw new Exception("argument invalid");
		}
		
		TrainLineBD.addNewLineBD(origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde) ; 
	}
	
	
	
	public static JSONObject searchTrain(String origin) throws Exception {
		
	   JSONObject result = new JSONObject();
	   StringBuffer response =new StringBuffer();

	    
	    if (origin == null ) {
			result.put("id", 0);
			result.put("error", "arguments invalide");
	    }else {
	    	//on va d'abord questionner notre base de donnée
//	    	JSONObject r1 = TrainTools.TrainSearchFromBdd(origin);
//	    	if(!r1.isEmpty()) {
//	    		return r1;
//	    	}
	    	//obtenir un accées a l'API avec un filtre
	    	URL url = new URL("https://ressources.data.sncf.com/api/records/1.0/search/?dataset=tarifs-intercites-de-jour&q=&rows=100&sort=origine&facet=origine&facet=destination&refine.origine="+origin);
			HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
			
			connexion.setConnectTimeout(10000);
			connexion.setReadTimeout(10000);
					
			InputStreamReader in = new InputStreamReader(connexion.getInputStream());
			BufferedReader br = new BufferedReader(in);
			
			while(br.ready()) {
				response.append(br.readLine());
			}
			br.close();
			
			// on récupére le résultat 
			result= new JSONObject(response.toString());
			
			//ajouter cette information a la base de donnée
			//recoverDataFromApi(result);
			
			JSONObject r1 = TrainTools.TrainSearchFromBdd(origin);
            if(r1.isEmpty()) {
                recoverDataFromApi(result);
            }
			
	    }
	    
		return result;
	}

	
	public static void recoverDataFromApi(JSONObject j) throws Exception{
		JSONArray je = j.getJSONArray("records");
		for(int i=0 ;i<je.length();i++) {
			j= (JSONObject) je.get(i);
			JSONObject train = new JSONObject(j.get("fields").toString());
	
			String origin = train.getString("origine");
            int pleinTarif1ere = train.getInt("plein_tarif_1ere");
            String destination = train.getString("destination");
            int pleinTarif2nde = train.getInt("plein_tarif_2nde");
            int prixdappel2nde = 0;
            if (train.has("prix_d_appel_2nde")) {
            	prixdappel2nde = train.getInt("prix_d_appel_2nde");
            }
            addNewLine( origin,  destination,  pleinTarif1ere,  pleinTarif2nde,  prixdappel2nde); 
		
		}
     }
            
	
	public static JSONObject  MiseAJour() throws Exception{
		JSONObject r = new JSONObject();
		String TrainTable =  "tarifLine";
		HashSet<String> originName = new HashSet <String> ();
		String requete="select origin from "+TrainTable+";";
	
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		while (resultat.next()) {
			originName.add(resultat.getString("origin"));
			
		}
		r.append("a","Récupération des points d'origines ===> success");
		//formatage de la base de donnée
		
		requete="DELETE  from "+TrainTable+";";
		statement.executeUpdate(requete);
		resultat.close();
		statement.close();		
		connexion.close();
		
		r.append("b","Début de la mise a jour");
		for(String a : originName) {
			URL url = new URL("https://ressources.data.sncf.com/api/records/1.0/search/?dataset=tarifs-intercites-de-jour&q=&rows=100&sort=origine&facet=origine&facet=destination&refine.origine="+a);
			HttpURLConnection cn = (HttpURLConnection) url.openConnection();
			
			cn.setConnectTimeout(10000);
			cn.setReadTimeout(10000);
				
			StringBuffer response =new StringBuffer();
			
			InputStreamReader in = new InputStreamReader(cn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			
			while(br.ready()) {
				response.append(br.readLine());
			}
			br.close();
			
			// on récupére le résultat 
			JSONObject result= new JSONObject(response.toString());
			
			
			//ajouter cette information a la base de donnée
			recoverDataFromApi(result);
			
			r.append("c", "Mise a jour effectuer");
			
		}
		return r;
	}
        
}
		
		
	
	


