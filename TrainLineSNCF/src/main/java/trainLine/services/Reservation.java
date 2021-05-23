package trainLine.services;

import org.json.JSONObject;

import trainLine.bdd.ReservationBD;
import trainLine.utils.SessionTools;

public class Reservation {

	public static JSONObject bookticket(String codepromo, String idsession) throws Exception {

		JSONObject result = new JSONObject();

		if (codepromo == null || idsession == null) {
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
					ReservationBD.addNewReservration(pseudo, codepromo);
				}
			}
		}

		return result;
	}

}
