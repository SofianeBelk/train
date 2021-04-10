package trainLine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;




public class ApiTools {
	public static StringBuffer response =new StringBuffer();
	
	public static JSONObject getDataURI() throws IOException  {
		URL url = new URL("https://ressources.data.sncf.com/api/records/1.0/search/?dataset=tarifs-intercites-de-jour&q=&rows=100&sort=origine&facet=origine&facet=destination");
		HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
		
		connexion.setConnectTimeout(10000);
		connexion.setReadTimeout(10000);
				
		if (connexion.getResponseCode() == -1) {
			throw new RuntimeException("Failed : HTTP Error : " + connexion.getResponseCode());
		}
		InputStreamReader in = new InputStreamReader(connexion.getInputStream());
		BufferedReader br = new BufferedReader(in);
		
		while(br.ready()) {
			response.append(br.readLine());
		}
		br.close();
		
		JSONObject j= new JSONObject(response.toString());
		return j;
		
		
	} 

}
