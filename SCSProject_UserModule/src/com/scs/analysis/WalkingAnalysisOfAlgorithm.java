package com.scs.analysis;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import UserModule.EmailNotification;
import UserModule.JSONStakeholderData;
import UserModule.NotifyStakeholders;
import UserModule.NotifyUsers;
import UserModule.User;

public class WalkingAnalysisOfAlgorithm implements AnalysisOfAlgorithm {

	public void writingToFile(List<Float> magnitudeData, String userID) {
		try {

			String localDir = System.getProperty("user.dir");

			File yourFile = new File(localDir + "\\src\\UserModule\\" + userID + ".txt");
			yourFile.createNewFile();
			FileWriter writer = new FileWriter(localDir + "\\src\\UserModule\\" + userID + ".txt");

			for (Float arr : magnitudeData) {
				writer.write(arr.toString());
				writer.write("\n");
				writer.flush();
			}
			writer.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void actualAlgorithm(List<Float> magnitudeDataAfterDifference1, List<Float> magnitudeDataAfterDifference2,
			User user) { // magnitude after successive difference calculation needs to be passed to this

		int i = 0;
		int flag = 0; // flag to help break out of main loop
		int temp = 0;
		try {
			while (i <= magnitudeDataAfterDifference1.size() - 1) {
				if (magnitudeDataAfterDifference1.size() - i < 200 && i >= 2999) {
					System.out.println("Insufficient data to proceed beyond 3000!!");
					break;
				}
				float[] ratioArray = ratioCalculator(magnitudeDataAfterDifference1, magnitudeDataAfterDifference2, i);
				if (ratioArray[0] > 6 && ratioArray[1] > 6) {
					flag = postFallChecker(magnitudeDataAfterDifference1, magnitudeDataAfterDifference2, i + 200);
					if (flag == 1) {
						float[] ratioArrayTemp = ratioCalculator(magnitudeDataAfterDifference1,
								magnitudeDataAfterDifference2, i + 200);
						if (ratioArrayTemp[0] >= ratioArray[0] && ratioArrayTemp[1] >= ratioArray[1]) {
							flag = 0;
						} else {
							writingToFile(magnitudeDataAfterDifference1.subList(i - 200, i + 200), user.getId());
							NotifyUsers notify = new NotifyUsers(user);
							notify.notifyByEmail();
							boolean notifyEmergencyServices = true;
							NotifyStakeholders.notifyByEmail(notifyEmergencyServices);
							temp = i;
							flag = 0;
							break;
						}
					}
				}
				i = i + 200;

			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("End of File was seen !! Couldnt detect a fall or no fall seen in data!!");
		}

	}

	// function to pick the max and min value in a list and evaluate their ratio

	private float[] ratioCalculator(List<Float> magnitudeDataAfterDifference1,
			List<Float> magnitudeDataAfterDifference2, int index) {
		int start_limit = index;
		int end_limit = 0;

		if (index < magnitudeDataAfterDifference1.size() && (index + 200) < magnitudeDataAfterDifference1.size()) {
			start_limit = index;
			end_limit = index + 200;
		}

		else if (index >= magnitudeDataAfterDifference1.size()) {
			start_limit = magnitudeDataAfterDifference1.size() - 3;
			end_limit = magnitudeDataAfterDifference1.size() - 2;
		}

		else {
			end_limit = magnitudeDataAfterDifference1.size() - 2;
		}

		List<Float> temp_list1 = magnitudeDataAfterDifference1.subList(start_limit, end_limit); // as per analysis 200
																								// lines of data is one
																								// second of data
		List<Float> temp_list2 = magnitudeDataAfterDifference2.subList(start_limit, end_limit); // as per analysis 200
																								// lines of data is one
																								// second of data
		float[] ratio = { (Collections.max(temp_list1)) / (Collections.min(temp_list1)),
				(Collections.max(temp_list2)) / (Collections.min(temp_list2)) };
		return ratio;
	}

	// function to pick the max and min value in a list and evaluate their ratio
	/*
	 * private float[] ratioCalculator(List<Float>
	 * magnitudeDataAfterDifference1,List<Float> magnitudeDataAfterDifference2, int
	 * index) { List<Float>
	 * temp_list1=magnitudeDataAfterDifference1.subList(index,index+200); //as per
	 * analysis 200 lines of data is one second of data List<Float>
	 * temp_list2=magnitudeDataAfterDifference2.subList(index,index+200); //as per
	 * analysis 200 lines of data is one second of data float[] ratio=
	 * {(Collections.max(temp_list1))/(Collections.min(temp_list1)),(Collections.max
	 * (temp_list2))/(Collections.min(temp_list2))}; return ratio; }
	 * 
	 */
	// function to analyse the ratio after fall has been detected, to see if the
	// graph is stable
	private int postFallChecker(List<Float> magnitudeDataAfterDifference1, List<Float> magnitudeDataAfterDifference2,
			int index) {
		float[] ratioArray = { 0.0f, 0.0f };

		int counter = 0;
		int temp = 0;
		try {
			while (index <= magnitudeDataAfterDifference1.size() && index < 3000) {
				ratioArray = ratioCalculator(magnitudeDataAfterDifference1, magnitudeDataAfterDifference2, index);
				if (ratioArray[0] < 2 && ratioArray[1] < 2) {
					counter++;
				}
				index = index + 200;
			}

			if (counter >= 2) {
				return 1;
			} else {
				return 0;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 1;
		}
	}

}
