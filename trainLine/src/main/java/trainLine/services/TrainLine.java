package trainLine.services;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import trainLine.bdd.TrainLineBD;
import trainLine.utils.ApiToold;


public class TrainLine {

	public static void addNewLine(String origin, String destination, String pleinTarif1ere, String pleinTarif2nde, String prixdappel2nde) throws Exception 
	{
		if (origin == null || destination == null ) {
			throw new Exception("argument invalid");
		}
		
		TrainLineBD.addNewLineBD(origin, destination, pleinTarif1ere, pleinTarif2nde, prixdappel2nde) ; 
	}
	
	
	
	public static String searchTrain(String origin) throws Exception {
		
		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<String, Object > result = new HashMap<String, Object>();
	    
	    if (origin == null ) {
			result.put("id", 0);
			result.put("error", "arguments invalide");
	    }else {
	    	// pas besoin de se connecter pour faire la recherche
	    	result.put("searchResult", TrainLineBD.searchLine(origin)) ; 
	    }
	    
		String json = gson.toJson(result);
		return json;
	}

	public static String getAllTrainLine() throws SQLException {
		
		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<String, Object > result = new HashMap<String, Object>();
	    
	    result.put("Lines", TrainLineBD.getAllLine()) ; 
	    
		String json = gson.toJson(result);
		return json;
	}
	
	public static void  recoverDataFromApi() throws Exception{
		ApiToold.getDataURI();		
		//
		String fileName = "src/main/java/resources/train.json";
        Path path = Paths.get(fileName);

        try (Reader reader = Files.newBufferedReader(path, 
                StandardCharsets.UTF_8)) {

            @SuppressWarnings("deprecation")
			JsonParser parser = new JsonParser();
            @SuppressWarnings("deprecation")
			JsonElement tree = parser.parse(reader);

            JsonArray array = tree.getAsJsonArray();

            for (JsonElement element : array) {

                if (element.isJsonObject()) {

                    JsonObject train = element.getAsJsonObject();
                    String origin = train.get("origin").getAsString();
                    String pleinTarif1ere = train.get("plein_tarif_1ere").getAsString();
                    String destination = train.get("destination").getAsString();
                    String pleinTarif2nde = train.get("plein_tarif_2nde").getAsString();
                    String prixdappel2nde = train.get("prix_d_appel_2nde").getAsString();
                    
                    
                    addNewLine( origin,  destination,  pleinTarif1ere,  pleinTarif2nde,  prixdappel2nde); 
                }
            }
        }
    }
		
		
	
	

}
