package trainLine.services;

import org.json.JSONObject;

import trainLine.bdd.ReservationBD;
import trainLine.utils.SessionTools;

public class Reservation {

	public static JSONObject bookticket(String idTrain, String idsession) throws Exception {

		JSONObject result = new JSONObject();

		if (idTrain == null || idsession == null) {

			result.put("the cause", "arguments invalid ");

		} else {
			if (!SessionTools.isExistKey(idsession)) {

				result.put("the cause", " you are not connecting ");
			} else {
				if (!SessionTools.isValidKey(idsession)) {

					result.put("the cause", " non-activity time, please reconnect ");
				} else {

					String pseudo = SessionTools.getPseudoFromSession(idsession);

					result.put("Message", "successful reservation ");
					ReservationBD.addNewReservration(pseudo, idTrain);
				}
			}
		}

		return result;
	}
	
	public static JSONObject CodePromo(String login, String origin, String distination) throws Exception {
		int numb = (int) (Math.random() * ( 1000000 ));
		String code = login+"0"+origin+"0"+distination+numb;
		JSONObject resultat = new JSONObject();
		resultat.append("codePromo", code);
		return resultat;
	}

}
