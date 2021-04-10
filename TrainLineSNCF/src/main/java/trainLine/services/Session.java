package trainLine.services;

import java.sql.SQLException;
import org.json.JSONObject;

import trainLine.bdd.SessionBD;
import trainLine.utils.SessionTools;
import trainLine.utils.UserTools;

public class Session {
	
	public static JSONObject startSession(String username, String password) throws SQLException 
	{
		JSONObject result = new JSONObject();

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
		
		return result;
	}
	
	public static JSONObject endSession(String key) throws SQLException 
	{
		JSONObject result = new JSONObject();

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
		
		return result;
	}
	
}
