package HumanFall.UI;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AddUserDataToJson {
	public void addDataToJson(List UserData) {
		try {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader(
					"C:\\Users\\Chinmay\\eclipse-workspace\\SCSProject_UserModule\\src\\UserModule\\UserData.json"));
			JSONObject originalJson = (JSONObject) obj;
			JSONArray doctorEmail = new JSONArray();
			JSONArray dependentsEmail = new JSONArray();

			JSONObject newDataToAdd = new JSONObject();

			System.out.println("id" + UserData.get(1));

			newDataToAdd.put("ID", UserData.get(1));
			newDataToAdd.put("Name", UserData.get(2));
			newDataToAdd.put("Age", UserData.get(3));
			newDataToAdd.put("Height", UserData.get(4));
			newDataToAdd.put("Weight", UserData.get(5));
			newDataToAdd.put("Gender", UserData.get(6));

			doctorEmail.add(UserData.get(7));
			newDataToAdd.put("Doctor", doctorEmail);

			dependentsEmail.add(UserData.get(8));
			newDataToAdd.put("Dependents", dependentsEmail);

			originalJson.put(UserData.get(0), newDataToAdd);

			System.out.println(originalJson.toJSONString());

			FileWriter file = new FileWriter(
					"C:\\Users\\Chinmay\\eclipse-workspace\\SCSProject_UserModule\\src\\UserModule\\UserData.json");
			file.write(originalJson.toJSONString());
			file.close();
		} catch (Exception E) {
			E.printStackTrace();
			System.out.println("Found exception here");
		}

	}

}
