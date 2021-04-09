package trainLine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class ApiTools {
	public static StringBuffer res=new StringBuffer();
	
	public static void getDataURI() throws IOException  {
		Gson gson = new GsonBuilder().serializeNulls().create();
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
			res.append(br.readLine());
		}
		br.close();
		
		String fileName = "src/main/java/resources/train.json";
        Path path = Paths.get(fileName);
        
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            
            JsonElement tree = gson.toJsonTree(res);
            gson.toJson(tree, writer);
        }
        
        System.out.println("JSon train written to file");
	
		
	} 

}
