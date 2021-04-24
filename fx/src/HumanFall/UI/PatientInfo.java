package HumanFall.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientInfo {

	public static String emailIdDoctor;
	public static String usercheckPwd;

	public static void signupPage() {
		// On signUp the User data is Registered
		Stage window = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		Label nameLabelSignupfull = new Label("Patient Id");
		GridPane.setConstraints(nameLabelSignupfull, 0, 0);

		// Text field for name
		TextField nameTextfull = new TextField();
		GridPane.setConstraints(nameTextfull, 1, 0);

		// Label for name
		Label nameLabelSignup = new Label("Id");
		GridPane.setConstraints(nameLabelSignup, 0, 1);

		// Text field for name
		TextField nameText = new TextField();
		GridPane.setConstraints(nameText, 1, 1);

		// Label for name
		Label namePatientsign = new Label("Name");
		GridPane.setConstraints(namePatientsign, 0, 2);

		// Text field for name
		TextField nameTextsign = new TextField();
		GridPane.setConstraints(nameTextsign, 1, 2);

		Label agePatientsign = new Label("Age");
		GridPane.setConstraints(agePatientsign, 0, 3);

		// Text field for name
		TextField ageTextsign = new TextField();
		GridPane.setConstraints(ageTextsign, 1, 3);

		Label heightPatientsign = new Label("Height");
		GridPane.setConstraints(heightPatientsign, 0, 4);

		// Text field for name
		TextField heightTextsign = new TextField();
		GridPane.setConstraints(heightTextsign, 1, 4);

		Label weightPatientsign = new Label("Weight");
		GridPane.setConstraints(weightPatientsign, 0, 5);

		// Text field for name
		TextField weightTextsign = new TextField();
		GridPane.setConstraints(weightTextsign, 1, 5);

		Label genderPatientsign = new Label("Gender");
		GridPane.setConstraints(genderPatientsign, 0, 6);

		// Text field for name
		TextField genderTextsign = new TextField();
		GridPane.setConstraints(genderTextsign, 1, 6);

		Label doctorsign = new Label("Doctor");
		GridPane.setConstraints(doctorsign, 0, 7);

		// Text field for name
		TextField doctorTextsign = new TextField();
		GridPane.setConstraints(doctorTextsign, 1, 7);

		Label dependentsign = new Label("Dependent");
		GridPane.setConstraints(dependentsign, 0, 8);

		// Text field for name
		TextField dependentTextsign = new TextField();
		GridPane.setConstraints(dependentTextsign, 1, 8);

		Button save = new Button("Save");
		GridPane.setConstraints(save, 0, 9);

		grid.getChildren().addAll(nameLabelSignupfull, nameTextfull, nameLabelSignup, nameText, namePatientsign,
				nameTextsign, agePatientsign, ageTextsign, heightPatientsign, heightTextsign, weightPatientsign,
				weightTextsign, genderPatientsign, genderTextsign, doctorsign, doctorTextsign, dependentsign,
				dependentTextsign, save);

		grid.setAlignment(Pos.TOP_LEFT);
		Scene sceneHome = new Scene(grid, 1300, 700);

		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/HumanFall/UI/patientbox.css");

		try {
			sceneHome.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.setScene(sceneHome);
		window.show();

		// When clicked on save button the User data is saved
		save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				List userData = new ArrayList();

				userData.add(nameTextfull.getText().toString());
				userData.add(nameText.getText().toString());
				userData.add(nameTextsign.getText().toString());
				userData.add(ageTextsign.getText().toString());
				userData.add(heightTextsign.getText().toString());
				userData.add(weightTextsign.getText().toString());
				userData.add(genderTextsign.getText().toString());
				userData.add(doctorTextsign.getText().toString());
				userData.add(dependentTextsign.getText().toString());

				AddUserDataToJson addData = new AddUserDataToJson();
				addData.addDataToJson(userData);
			}
		});
	}

	public static void patientDetails(Stage PrimaryStage) {

		Node rootIcon = null;
		try {
			String localDir = System.getProperty("user.dir");
			FileInputStream inputstream = new FileInputStream(localDir + "/lib/icon/patient.png");
			rootIcon = new ImageView(new Image(inputstream));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		TreeItem<String> rootPatient, newBranchUser1, newBranchUser2, newBranchUser3, newBranchUser4, accnDataUser1,
				accnDataUser2, accnDataUser3, accnDataUser4, patientInfoUser1, patientInfoUser2, patientInfoUser3,
				patientInfoUser4;
		TreeView<String> tree;

		rootPatient = new TreeItem<String>("Patient Details", rootIcon);
		rootPatient.setExpanded(true);

		newBranchUser1 = makeBranch("SA01", rootPatient);
		patientInfoUser1 = makeBranch("Patient Info", newBranchUser1);

		newBranchUser2 = makeBranch("SA02", rootPatient);
		patientInfoUser2 = makeBranch("Patient Info", newBranchUser2);

		newBranchUser3 = makeBranch("SA03", rootPatient);
		patientInfoUser3 = makeBranch("Patient Info", newBranchUser3);

		newBranchUser4 = makeBranch("SA04", rootPatient);
		patientInfoUser4 = makeBranch("Patient Info", newBranchUser4);

		TreeItem<String> newAccData1 = makeBranch("Acceleration Data", rootPatient);

		TreeItem<String> newPatientRegister = makeBranch("Register Patient", rootPatient);

		// Create the tree
		tree = new TreeView<>(rootPatient);
		tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue.equals(patientInfoUser1)) {
				listPatient1();
			}

			if (newValue.equals(patientInfoUser2)) {
				listPatient2();
			}

			if (newValue.equals(patientInfoUser3)) {
				listPatient3();
			}

			if (newValue.equals(patientInfoUser4)) {
				listPatient4();
			}

			if (newValue.equals(newPatientRegister)) {
				signupPage();
			}

			if (newValue.equals(newAccData1)) {
				try {
					AccelerationGraph.display();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Layout
		StackPane treeLayout = new StackPane();
		treeLayout.getChildren().add(tree);
		Scene treeScene = new Scene(treeLayout, 1300, 700);

		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/HumanFall/UI/patient.css");

		try {
			treeScene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrimaryStage.setScene(treeScene);
		PrimaryStage.show();
	}

	public static TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(false);
		parent.getChildren().add(item);
		return item;
	}

	public static void listPatient1() {
		Stage windowPatient = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		// Read the UserData
		String localDir1 = System.getProperty("user.dir");
		File filePath = new File(localDir1 + "/src/HumanFall/UI/UserData.json");

		// String filePath =
		// "C:\\Users\\Chinmay\\eclipse-workspace\\fx\\src\\HumanFall\\UI\\UserData.json";
		String userID = "SA01";
		User user = new User();
		user = JSONOperationsUserData.readJSON(filePath.toString(), userID);

		// Name Input
		TextField nameInputId = new TextField();
		TextField nameInputAge = new TextField();
		TextField nameInputGender = new TextField();
		TextField nameInputHeight = new TextField();
		TextField nameInputName = new TextField();
		TextField nameInputWeight = new TextField();

		// Name Label
		Label nameLabelId = new Label("ID");
		Label nameLabelAge = new Label("Age");
		Label nameLabelGender = new Label("Gender");
		Label nameLabelHeight = new Label("Height");
		Label nameLabelName = new Label("Name");
		Label nameLabelWeight = new Label("Weight");

		GridPane.setConstraints(nameLabelId, 2, 0);
		GridPane.setConstraints(nameLabelAge, 2, 1);
		GridPane.setConstraints(nameLabelGender, 2, 2);
		GridPane.setConstraints(nameLabelHeight, 2, 3);
		GridPane.setConstraints(nameLabelName, 2, 4);
		GridPane.setConstraints(nameLabelWeight, 2, 5);

		nameInputId.appendText(user.getId());
		GridPane.setConstraints(nameInputId, 1, 0);

		nameInputAge.appendText(user.getAge());
		GridPane.setConstraints(nameInputAge, 1, 1);

		nameInputGender.appendText(user.getGender());
		GridPane.setConstraints(nameInputGender, 1, 2);

		nameInputHeight.appendText(user.getHeight());
		GridPane.setConstraints(nameInputHeight, 1, 3);

		nameInputName.appendText(user.getName());
		GridPane.setConstraints(nameInputName, 1, 4);

		nameInputWeight.appendText(user.getWeight());
		GridPane.setConstraints(nameInputWeight, 1, 5);

		grid.getChildren().addAll(nameInputId, nameInputName, nameInputAge, nameInputGender, nameInputHeight,
				nameInputWeight, nameLabelId, nameLabelName, nameLabelAge, nameLabelGender, nameLabelHeight,
				nameLabelWeight);
		grid.setAlignment(Pos.CENTER);

		Scene patientScene = new Scene(grid);
		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/patientbox.css");

		try {
			patientScene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		windowPatient.setScene(patientScene);
		windowPatient.showAndWait();
	}

	public static void listPatient2() {
		Stage windowPatient = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		// Read the UserData
		String localDir1 = System.getProperty("user.dir");
		File filePath = new File(localDir1 + "/src/HumanFall/UI/UserData.json");
		String userID = "SA02";
		User user = new User();
		user = JSONOperationsUserData.readJSON(filePath.toString(), userID);

		// Name Input
		TextField nameInputId = new TextField();
		TextField nameInputAge = new TextField();
		TextField nameInputGender = new TextField();
		TextField nameInputHeight = new TextField();
		TextField nameInputName = new TextField();
		TextField nameInputWeight = new TextField();

		// Name Label
		Label nameLabelId = new Label("ID");
		Label nameLabelAge = new Label("Age");
		Label nameLabelGender = new Label("Gender");
		Label nameLabelHeight = new Label("Height");
		Label nameLabelName = new Label("Name");
		Label nameLabelWeight = new Label("Weight");

		GridPane.setConstraints(nameLabelId, 2, 0);
		GridPane.setConstraints(nameLabelAge, 2, 1);
		GridPane.setConstraints(nameLabelGender, 2, 2);
		GridPane.setConstraints(nameLabelHeight, 2, 3);
		GridPane.setConstraints(nameLabelName, 2, 4);
		GridPane.setConstraints(nameLabelWeight, 2, 5);

		nameInputId.appendText(user.getId());
		GridPane.setConstraints(nameInputId, 1, 0);

		nameInputAge.appendText(user.getAge());
		GridPane.setConstraints(nameInputAge, 1, 1);

		nameInputGender.appendText(user.getGender());
		GridPane.setConstraints(nameInputGender, 1, 2);

		nameInputHeight.appendText(user.getHeight());
		GridPane.setConstraints(nameInputHeight, 1, 3);

		nameInputName.appendText(user.getName());
		GridPane.setConstraints(nameInputName, 1, 4);

		nameInputWeight.appendText(user.getWeight());
		GridPane.setConstraints(nameInputWeight, 1, 5);

		grid.getChildren().addAll(nameInputId, nameInputName, nameInputAge, nameInputGender, nameInputHeight,
				nameInputWeight, nameLabelId, nameLabelName, nameLabelAge, nameLabelGender, nameLabelHeight,
				nameLabelWeight);
		grid.setAlignment(Pos.CENTER);

		Scene patientScene = new Scene(grid);
		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/patientbox.css");

		try {
			patientScene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		windowPatient.setScene(patientScene);
		windowPatient.showAndWait();
	}

	public static void listPatient3() {
		Stage windowPatient = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		// Read the UserData
		String localDir1 = System.getProperty("user.dir");
		File filePath = new File(localDir1 + "/src/HumanFall/UI/UserData.json");
		String userID = "SA03";
		User user = new User();
		user = JSONOperationsUserData.readJSON(filePath.toString(), userID);

		// Name Input
		TextField nameInputId = new TextField();
		TextField nameInputAge = new TextField();
		TextField nameInputGender = new TextField();
		TextField nameInputHeight = new TextField();
		TextField nameInputName = new TextField();
		TextField nameInputWeight = new TextField();

		// Name Label
		Label nameLabelId = new Label("ID");
		Label nameLabelAge = new Label("Age");
		Label nameLabelGender = new Label("Gender");
		Label nameLabelHeight = new Label("Height");
		Label nameLabelName = new Label("Name");
		Label nameLabelWeight = new Label("Weight");

		GridPane.setConstraints(nameLabelId, 2, 0);
		GridPane.setConstraints(nameLabelAge, 2, 1);
		GridPane.setConstraints(nameLabelGender, 2, 2);
		GridPane.setConstraints(nameLabelHeight, 2, 3);
		GridPane.setConstraints(nameLabelName, 2, 4);
		GridPane.setConstraints(nameLabelWeight, 2, 5);

		nameInputId.appendText(user.getId());
		GridPane.setConstraints(nameInputId, 1, 0);

		nameInputAge.appendText(user.getAge());
		GridPane.setConstraints(nameInputAge, 1, 1);

		nameInputGender.appendText(user.getGender());
		GridPane.setConstraints(nameInputGender, 1, 2);

		nameInputHeight.appendText(user.getHeight());
		GridPane.setConstraints(nameInputHeight, 1, 3);

		nameInputName.appendText(user.getName());
		GridPane.setConstraints(nameInputName, 1, 4);

		nameInputWeight.appendText(user.getWeight());
		GridPane.setConstraints(nameInputWeight, 1, 5);

		grid.getChildren().addAll(nameInputId, nameInputName, nameInputAge, nameInputGender, nameInputHeight,
				nameInputWeight, nameLabelId, nameLabelName, nameLabelAge, nameLabelGender, nameLabelHeight,
				nameLabelWeight);
		grid.setAlignment(Pos.CENTER);

		Scene patientScene = new Scene(grid);
		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/patientbox.css");

		try {
			patientScene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		windowPatient.setScene(patientScene);
		windowPatient.showAndWait();
	}

	public static void listPatient4() {
		Stage windowPatient = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		// Read the UserData
		String localDir1 = System.getProperty("user.dir");
		File filePath = new File(localDir1 + "/src/HumanFall/UI/UserData.json");
		String userID = "SA04";
		User user = new User();
		user = JSONOperationsUserData.readJSON(filePath.toString(), userID);

		// Name Input
		TextField nameInputId = new TextField();
		TextField nameInputAge = new TextField();
		TextField nameInputGender = new TextField();
		TextField nameInputHeight = new TextField();
		TextField nameInputName = new TextField();
		TextField nameInputWeight = new TextField();

		// Name Label
		Label nameLabelId = new Label("ID");
		Label nameLabelAge = new Label("Age");
		Label nameLabelGender = new Label("Gender");
		Label nameLabelHeight = new Label("Height");
		Label nameLabelName = new Label("Name");
		Label nameLabelWeight = new Label("Weight");

		GridPane.setConstraints(nameLabelId, 2, 0);
		GridPane.setConstraints(nameLabelAge, 2, 1);
		GridPane.setConstraints(nameLabelGender, 2, 2);
		GridPane.setConstraints(nameLabelHeight, 2, 3);
		GridPane.setConstraints(nameLabelName, 2, 4);
		GridPane.setConstraints(nameLabelWeight, 2, 5);

		nameInputId.appendText(user.getId());
		GridPane.setConstraints(nameInputId, 1, 0);

		nameInputAge.appendText(user.getAge());
		GridPane.setConstraints(nameInputAge, 1, 1);

		nameInputGender.appendText(user.getGender());
		GridPane.setConstraints(nameInputGender, 1, 2);

		nameInputHeight.appendText(user.getHeight());
		GridPane.setConstraints(nameInputHeight, 1, 3);

		nameInputName.appendText(user.getName());
		GridPane.setConstraints(nameInputName, 1, 4);

		nameInputWeight.appendText(user.getWeight());
		GridPane.setConstraints(nameInputWeight, 1, 5);

		grid.getChildren().addAll(nameInputId, nameInputName, nameInputAge, nameInputGender, nameInputHeight,
				nameInputWeight, nameLabelId, nameLabelName, nameLabelAge, nameLabelGender, nameLabelHeight,
				nameLabelWeight);
		grid.setAlignment(Pos.CENTER);

		Scene patientScene = new Scene(grid);
		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/patientbox.css");

		try {
			patientScene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// patientScene.getStylesheets().add("/lib/HumanFall/patientbox.css");p

		windowPatient.setScene(patientScene);
		windowPatient.showAndWait();
	}

}
