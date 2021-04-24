package UserModule;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scs.analysis.*;

public class MainApp {

	public static String fileChooser() {
		JFileChooser chooser = new JFileChooser();
		String returnFile = "";

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			returnFile = chooser.getSelectedFile().getPath();
		}
		return returnFile;
	}

	public static String getUserID(Path pathName) {
		System.out.println(pathName.toString());
		String fileName = pathName.getFileName().toString();
		String userID = fileName.split("_")[1];
		return userID;
	}

	public static void main(String[] args) {

		List<Accelerometer> accelerometers = new ArrayList<Accelerometer>();
		ReadClass readData = new ReadClass();
		ComputationGeneratorParentClass computor = new ComputationGeneratorParentClass();
		Path pathName = Paths.get(MainApp.fileChooser());
		String userID = getUserID(pathName);
		User user = new User();
		String localDir = System.getProperty("user.dir");
		String filePath = localDir + "\\src\\UserModule\\UserData.json";
		user = JSONOperationsUserData.readJSON(filePath, userID);

		readData.ReadData(pathName);

		Accelerometer accelerometerFirst = new Accelerometer(16, 13, readData.getAcc1_x(), readData.getAcc1_y(),
				readData.getAcc1_z());
		Accelerometer accelerometerSecond = new Accelerometer(8, 14, readData.getAcc2_x(), readData.getAcc2_y(),
				readData.getAcc2_z());
		accelerometerFirst.calculateValuesOfSensorsForEachAxis();
		accelerometerSecond.calculateValuesOfSensorsForEachAxis();

		accelerometers.add(accelerometerFirst);
		accelerometers.add(accelerometerSecond);

		computor.analyseAlgorithm(accelerometers, user);

	}

}
