package trainLine.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import trainLine.bdd.TrainLineBD;



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
			recoverDataFromApi(result);
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
            
        
}
		
		
	
	


