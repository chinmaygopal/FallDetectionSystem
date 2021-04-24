package UserModule;

import java.util.ArrayList;
import java.util.List;

public class Accelerometer implements Sensor {
	int range;
	int resolution;
	private List<Float> accX, accY, accZ;

	public Accelerometer(int range, int resolution, List<Float> acc_x, List<Float> acc_y, List<Float> acc_z) {
		this.range = range;
		this.resolution = resolution;
		this.accX = acc_x;
		this.accY = acc_y;
		this.accZ = acc_z;
	}

	public int getRange() {
		return range;
	}

	public int getResolution() {
		return resolution;
	}

	public List<Float> calculateValuesOfSensors(List<Float> accelerationData) {
		float calculatedRange = (2 * getRange()); // the conversion parameters are given in readme
		float calculatedResolution = ((float) Math.pow(2, getResolution()));
		int i = 0;
		List<Float> calculatedAccelration = new ArrayList<Float>();

		while (i <= accelerationData.size() - 1) {
			calculatedAccelration.add((calculatedRange / calculatedResolution) * accelerationData.get(i).floatValue());

			i++;
		}
		// System.out.println(calculatedAccelration);
		return calculatedAccelration;
	}

	public void calculateValuesOfSensorsForEachAxis() {
		this.accX = calculateValuesOfSensors(this.accX);
		this.accY = calculateValuesOfSensors(this.accY);
		this.accZ = calculateValuesOfSensors(this.accZ);
	}

	public List<Float> getAccX() {
		return accX;
	}

	public List<Float> getAccY() {
		return accY;
	}

	public List<Float> getAccZ() {
		return accZ;
	}

}
