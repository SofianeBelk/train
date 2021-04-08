package trainLine.services;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import trainLine.bdd.ReservationBD;
import trainLine.utils.SessionTools;

public class Reservation {

	public static String bookticket(String idTrain, String idsession) throws Exception {

		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> result = new HashMap<String, Object>();

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

		String json = gson.toJson(result);
		return json;
	}

}
