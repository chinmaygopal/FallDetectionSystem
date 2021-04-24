package HumanFall.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class AccelerationGraph {

	public static void display() throws FileNotFoundException {
		Stage graphWindow = new Stage();

		graphWindow.initModality(Modality.APPLICATION_MODAL);

		// Setting x and y axis for Linechart
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Time(ms)");
		yAxis.setLabel("Linear Acceleration(g)");

		xAxis.setId("bold-label");
		yAxis.setId("bold-label");

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setId("bold-label");

		// Data is copied to the Linechart through XYChart
		XYChart.Series series = new XYChart.Series();

		series.setName("Fall Data");

		Stage dialogWindow = new Stage();

		// Choose the file
		FileChooser filechooser = new FileChooser();

		filechooser.setInitialDirectory(
				new File(System.getProperty("user.home") + System.getProperty("file.separator") + "\\Downloads"));

		// Set extension filter for only txt files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");

		filechooser.getExtensionFilters().add(extFilter);

		// open dialog window
		File newfile = filechooser.showOpenDialog(dialogWindow);
		Scanner sc = new Scanner(newfile);

		ArrayList<Float> dataList = new ArrayList<>();

		while (sc.hasNextFloat()) {
			// dataList.add(sc.nextInt());
			dataList.add(sc.nextFloat());
			// System.out.println(sc.nextFloat());
		}

		for (int i = 0, j = 1; i < dataList.size(); i++, j++) {
			series.getData().add(new XYChart.Data(j, dataList.get(i)));
			System.out.println(dataList.get(i));
		}

		Scene scene = new Scene(lineChart, 1300, 700);
		lineChart.getData().add(series);

		String localDir = System.getProperty("user.dir");
		File f = new File(localDir + "/lib/HumanFall/UI/graph.css");

		try {
			scene.getStylesheets().add(f.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		graphWindow.setScene(scene);
		graphWindow.showAndWait();

	}

}
