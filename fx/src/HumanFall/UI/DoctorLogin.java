package HumanFall.UI;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.LongToIntFunction;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorLogin extends Application {
	public String userDoctor;
	public String pwdDoctor;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage PrimaryStage) {

		PrimaryStage.setTitle("Doctor Application");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		// Username label
		Label nameLabel = new Label("Email Id");
		GridPane.setConstraints(nameLabel, 0, 0);

		// Name Input
		TextField nameInput = new TextField("Shashi");
		nameInput.setPromptText("Shashi");
		GridPane.setConstraints(nameInput, 1, 0);

		// Password label
		Label passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 1);

		// Password Input
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Your password");
		GridPane.setConstraints(passwordField, 1, 1);

		// Login Button
		Button loginButton = new Button("LogIn");
		GridPane.setConstraints(loginButton, 1, 2);

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				PatientInfo.patientDetails(PrimaryStage);
			}
		});

		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passwordField, loginButton);
		grid.setAlignment(Pos.TOP_LEFT);

		Scene sceneHome = new Scene(grid, 1300, 700);

		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/HumanFall/UI/newstyle.css");

		try {
			sceneHome.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrimaryStage.setScene(sceneHome);

		PrimaryStage.show();

	}

}
