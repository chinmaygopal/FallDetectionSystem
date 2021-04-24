package HumanFall.UI;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONOperationsUserData {
	@SuppressWarnings("finally")
	public static User readJSON(String filePath, String userID) {
		JSONParser parser = new JSONParser();
		User returnUser = new User();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject person = (JSONObject) jsonObject.get(userID);

			returnUser.setId(person.get("ID").toString());
			returnUser.setAge(person.get("Age").toString());
			returnUser.setHeight(person.get("Height").toString());
			returnUser.setName(person.get("Name").toString());
			returnUser.setWeight(person.get("Weight").toString());
			returnUser.setGender(person.get("Gender").toString());

			// doctors
			JSONArray doctorsList = (JSONArray) person.get("Doctor");
			ArrayList<String> doctors = new ArrayList<String>();
			for (Object doctor : doctorsList) {
				// System.out.println(dependent.toString());
				doctors.add(doctor.toString());
			}

			// dependents
			JSONArray dependentList = (JSONArray) person.get("Dependents");
			ArrayList<String> dependents = new ArrayList<String>();
			for (Object dependent : dependentList) {
				// System.out.println(dependent.toString());
				dependents.add(dependent.toString());
			}
			returnUser.setDependents(dependents);

		} catch (NullPointerException ex) {
			System.out.println("User " + userID + " missing");
			// NotifyStakeholders.notifyByEmail();
			System.exit(0);
		} catch (Exception ex) {
			// NotifyStakeholders.notifyByEmail();
			ex.printStackTrace();
		} finally {
			return returnUser;
		}
	}

}
