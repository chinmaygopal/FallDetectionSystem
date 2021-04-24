package UserModule;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONStakeholderData {
	@SuppressWarnings("finally")
	public static ArrayList<String> readJSON(String filePath, boolean notifyEmergencyServices) {
		JSONParser parser = new JSONParser();
		ArrayList<String> stakeholdersArray = new ArrayList<String>();

		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray stakeholders = new JSONArray();
			if (notifyEmergencyServices) {
				stakeholders = (JSONArray) jsonObject.get("EmergencyServices");
			} else {
				stakeholders = (JSONArray) jsonObject.get("Stakeholders");
			}

			for (Object stakeholder : stakeholders) {
				stakeholdersArray.add(stakeholder.toString());
			}
		}

		catch (NullPointerException ex) {
			NotifyStakeholders.notifyByEmail(false);
			System.exit(0);
		} catch (Exception ex) {
			NotifyStakeholders.notifyByEmail(false);
			ex.printStackTrace();
			System.exit(0);
		} finally {
			return stakeholdersArray;
		}
	}

}
