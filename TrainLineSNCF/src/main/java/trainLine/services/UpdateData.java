package trainLine.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import trainLine.utils.BaseTools;

public class UpdateData implements org.quartz.Job{
	 
	protected static String LineTable = "tarifLine";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	BasicConfigurator.configure();
    	System.out.println("Job executed at : " + LocalDateTime.now().toString());
        System.out.println("do something...");
        
        try {
            // 1 - recupérer tout les origine de la base et de les mettre dans une arratlist
        	System.out.println("get all origins from database and api");
			ArrayList<String> originsDatabase = getAllOriginsFromDatabase();
			ArrayList<String> originsApi = getAllOriginsFromDatabase(); //a modifier
			
			for(int i = 0 ; i<originsDatabase .size() ; i++) {
				System.out.println("origin = "+originsDatabase.get(i));
			}
			
			// 2 - [origin1, origin2...] - [origin1, origin2...]
			for(int i = 0 ; i<originsDatabase.size() ; i++) {
				for(int j = 0 ; j<originsApi.size() ; j++) {
					if(originsDatabase.get(i) == null) {
						// 3 -
						
					}else {
						// 4 - sinon supprimer tout ce qui est en rapport avec cet origin
			        	System.out.println("delete database");
			        	Thread.sleep(100L);
						deleteDatabase();
						
						// 5 - contacte l'api et insert a nouveau dans la base
						Thread.sleep(100L);
			        	System.out.println("re-insert every origin from api to database");
			        	if(!originsDatabase .isEmpty()) {
			    			TrainLine.searchTrain(originsDatabase.get(i));
			        	}
					}
				}
			}

			System.out.println("end");
			
        
        } catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
	public ArrayList<String> getAllOriginsFromDatabase() throws SQLException {
		ArrayList<String> origins = new ArrayList<String>() ; 
		String requete="select origin from "+LineTable+" ";    
		
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();		
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next()) {			    
			origins.add(resultat.getString("origin"));
		}
		
		statement.close();
		connexion.close();

		return origins;	
	}
	
	//retourner les origin (celle de la base) depuis l'api
	public void getOriginsFromApi(JSONObject j, ArrayList<String> origins) throws Exception{
		JSONArray je = j.getJSONArray("records");
		ArrayList<String> os = new ArrayList<String>();
		
		for(int i=0 ;i<je.length();i++) {
			j= (JSONObject) je.get(i);
			JSONObject train = new JSONObject(j.get("fields").toString());
	
			String origin = train.getString("origine");
			os.add(origin) ; 
		}
		
		//TODO
     }
	
	public void deleteDatabase() throws SQLException{
		String requete="delete from "+LineTable+" ";
		Connection connexion = BaseTools.getMySQLConnection();
		Statement statement = connexion.createStatement();
	
		statement.executeUpdate(requete);
		statement.close();
		connexion.close();
	}
	
	
	
	

}
