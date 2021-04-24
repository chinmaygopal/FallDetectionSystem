package com.scs.analysis;

import java.util.ArrayList;
import java.util.List;
import UserModule.Accelerometer;
import UserModule.User;

import java.lang.*;

public class ComputationGeneratorParentClass {

	public List<Float> magnitudeCalculator(List<Float> accData_x, List<Float> accData_y, List<Float> accData_z) {

		List<Float> magnitudeList = new ArrayList<Float>();
		int i = 0;

		while (i <= accData_x.size() - 1) {
			magnitudeList.add((float) Math.sqrt(Math.pow(accData_x.get(i).floatValue(), 2)
					+ Math.pow(accData_y.get(i).floatValue(), 2) + Math.pow(accData_z.get(i).floatValue(), 2)));
			i++;
		}

		return magnitudeList;
	}

	// Part of old algorithm, no longer needed

//    public List<Float> differenceCalculator(List<Float> magnitudeData){
//
//        List<Float> differenceList=new ArrayList<Float>();
//        int i=0;
//        while(i<=magnitudeData.size()-1){
//            if(i!=magnitudeData.size()-1){  //to avoid arrayoutofbounds exception
//                differenceList.add(magnitudeData.get(i+1).floatValue()-magnitudeData.get(i).floatValue());
//            }
//            i++;
//        }
//        return differenceList;
//    }
//3,5 - 1
	public void analyseAlgorithm(List<Accelerometer> accelerometers, User user) {

		List<Float> magnitudeListAcc1 = new ArrayList<Float>(); // lists to store the magnitude of the 3 acc dimensions
		List<Float> magnitudeListAcc2 = new ArrayList<Float>();

		WalkingAnalysisOfAlgorithm walking = new WalkingAnalysisOfAlgorithm();

		Accelerometer acceleratorFirst = accelerometers.get(0);
		Accelerometer acceleratorSecond = accelerometers.get(1);

		List<Float> acc_1_x = acceleratorFirst.getAccX(); // the value needs to be converted for each accelerator
															// depending on the readme file given
		List<Float> acc_1_y = acceleratorFirst.getAccY();
		List<Float> acc_1_z = acceleratorFirst.getAccZ();
		System.out.println(acc_1_x);
		List<Float> acc_2_x = acceleratorSecond.getAccX(); // the conversion values are different for this from
															// accelerator 1(check readme.txt)
		List<Float> acc_2_y = acceleratorSecond.getAccY();
		List<Float> acc_2_z = acceleratorSecond.getAccZ();

		magnitudeListAcc1 = magnitudeCalculator(acc_1_x, acc_1_y, acc_1_z); // function call to calculate the magnitude
																			// of the acceleration
		magnitudeListAcc2 = magnitudeCalculator(acc_2_x, acc_2_y, acc_2_z);

		// Part of old algorithm, no longer needed
		// magnitudeListAcc1=differenceCalculator(magnitudeListAcc1); //to calculate the
		// difference of successive
		// magnitudeListAcc2=differenceCalculator(magnitudeListAcc2);

		walking.actualAlgorithm(magnitudeListAcc1, magnitudeListAcc2, user);
	}

}
