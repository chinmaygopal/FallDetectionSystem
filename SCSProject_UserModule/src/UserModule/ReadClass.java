package UserModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadClass {

	private String delimiter;

	private List<Float> acc1_x, acc1_y, acc1_z;
	private List<Float> acc2_x, acc2_y, acc2_z;

	ReadClass() {
		this.delimiter = ",";
		this.acc1_x = new ArrayList<Float>();
		this.acc1_y = new ArrayList<Float>();
		this.acc1_z = new ArrayList<Float>();
		this.acc2_x = new ArrayList<Float>();
		this.acc2_y = new ArrayList<Float>();
		this.acc2_z = new ArrayList<Float>();

	}

	// List<List<float>>
	public void ReadData(Path pathName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathName.toString()));
			for (String line; (line = br.readLine()) != null;) {
				line = line.replace(" ", "");
				String[] lines = line.split(delimiter);
				int indexOfLastElement = (lines.length) - 1;
				String lastElement = lines[indexOfLastElement];
				lines[indexOfLastElement] = lastElement.substring(0, lastElement.length() - 1);
				int i = 0;
				// System.out.print(Integer.parseInt(lines[i])+"
				// "+Integer.parseInt(lines[i+1])+" "+Integer.parseInt(lines[i+2])+" "
				// +Integer.parseInt(lines[i+6]) + " "+Integer.parseInt(lines[i+7])+"
				// "+Integer.parseInt(lines[i+8])+" ");
				this.acc1_x.add(Float.parseFloat(lines[i]));
				this.acc1_y.add(Float.parseFloat(lines[i + 1]));
				this.acc1_z.add(Float.parseFloat(lines[i + 2]));
				this.acc2_x.add(Float.parseFloat(lines[i + 6]));
				this.acc2_y.add(Float.parseFloat(lines[i + 7]));
				this.acc2_z.add(Float.parseFloat(lines[i + 8]));
			}

		}
		// line is not visible here.
		catch (Exception e) {
			NotifyStakeholders.notifyByEmail(false);
			e.printStackTrace();
		}
	}

	public List<Float> getAcc1_x() {
		return acc1_x;
	}

	public void setAcc1_x(List<Float> acc1_x) {
		this.acc1_x = acc1_x;
	}

	public List<Float> getAcc1_y() {
		return acc1_y;
	}

	public void setAcc1_y(List<Float> acc1_y) {
		this.acc1_y = acc1_y;
	}

	public List<Float> getAcc1_z() {
		return acc1_z;
	}

	public void setAcc1_z(List<Float> acc1_z) {
		this.acc1_z = acc1_z;
	}

	public List<Float> getAcc2_x() {
		return acc2_x;
	}

	public void setAcc2_x(List<Float> acc2_x) {
		this.acc2_x = acc2_x;
	}

	public List<Float> getAcc2_y() {
		return acc2_y;
	}

	public void setAcc2_y(List<Float> acc2_y) {
		this.acc2_y = acc2_y;
	}

	public List<Float> getAcc2_z() {
		return acc2_z;
	}

	public void setAcc2_z(List<Float> acc2_z) {
		this.acc2_z = acc2_z;
	}

}
