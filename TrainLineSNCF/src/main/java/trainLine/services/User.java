package trainLine.services;

import java.sql.SQLException;
import java.util.jar.JarException;

import org.json.JSONObject;

import trainLine.utils.UserTools;
import trainLine.bdd.UserBD;

public class User {

	public static JSONObject addUser(String nom,String prenom, String pseudo ,String email,String pass,int age,String telephone) throws JarException, SQLException 
	{		
		
	    JSONObject result = new JSONObject();

		if ( nom == null || prenom == null || pseudo == null || email == null ||pass == null  || telephone == null) {	
			result.put("id", 0);
			result.put("error",1);			
		}else {
			if (UserTools.UserExistenceTest(pseudo)) {
				result.put("id", 0);
				result.put("erreur",2);
			}
			else {
				UserBD.addNewUser(nom, prenom, pseudo, email, pass, age, telephone) ; 

				result.put("id", 0);
				result.put("session", Session.startSession(pseudo, pass) ); 
			}
			
		}
		
		return result;
	}
	
	public static String removeUser(String key) throws JarException, SQLException
	{
		//Gson gson = new GsonBuilder().serializeNulls().create();
		
		//TODO
		return "" ; 
	}
}
