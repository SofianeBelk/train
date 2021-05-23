package trainLine.services;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

import org.json.JSONObject;

import trainLine.utils.BaseTools;

public class GeneratePromoCode {
	
	protected static String ReservationTable = "reservation";

	public static JSONObject generatePromoCode(String username, String dest) throws Exception
	{				
	    JSONObject resjson = new JSONObject();

	    // -----------
	    // générate promo code
	    // -----------
	    int r = new Random().nextInt(1000);
	    String promocode = username+""+dest+r;

	    
	    // ----------
	    // fill database
	    // ----------
	    		String requete="insert into  "+ReservationTable+" values ('"+username+"','"+promocode+"')";
		
	    		Connection connexion = BaseTools.getMySQLConnection();		
	    		Statement statement = connexion.createStatement();
	    		
	    		statement.executeUpdate(requete);		
	    		statement.close();
	    		connexion.close();
	    		
	    // ---------
	    // return result
	    // ---------
	    resjson.put("promo", promocode);
		return resjson;
	}
 
}
