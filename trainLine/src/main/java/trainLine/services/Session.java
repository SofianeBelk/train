package trainLine.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import trainLine.bdd.SessionBD;
import trainLine.utils.SessionTools;
import trainLine.utils.UserTools;

public class Session {
	
	public static String startSession(String username, String password) throws SQLException 
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<String, Object > result = new HashMap<String, Object>();

		if(username != null) {
			if (!UserTools.UserExistenceTest(username) || !UserTools.verifyPassword(username, password)) {
				result.put("id", 0);
				result.put("error","pseudo/password is invalid or user not exist"); 
			}else{
				String key = SessionTools.generatKey();
				SessionBD.launchSession(username, key);
				
				result.put("id", 0);
				result.put("confirmation", "Welcome " +username);
				result.put("key", key);
			}
		}else {
			result.put("id", 0);
			result.put("error","argument invalid"); 
		}
		
			
		String json = gson.toJson(result);
		return json;
	}
	
	public static String endSession(String key) throws SQLException 
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
	    Map<String, Object > result = new HashMap<String, Object>();

		if(key != null) {
			if(SessionTools.isExistKey(key)) {
				
				if(SessionTools.isValidKey(key)) {
					
					SessionBD.closeSession(key) ; 
					result.put("id", 1);
					result.put("confirmation", "user disconnected succesefuly");
					
				}else {
					result.put("id", 0);
					result.put("error", "key expired");
				}
				
			}else {
				result.put("id", 0);
				result.put("error", "session not exist");
			}
		}else {
			result.put("id", 0);
			result.put("error","argument invalid"); 
		}
		
		String json = gson.toJson(result);
		return json;
	}
	
}
