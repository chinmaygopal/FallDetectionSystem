package com.scs.analysis;

import java.util.List;

import UserModule.User;

public interface AnalysisOfAlgorithm {
	public void actualAlgorithm(List<Float> magnitudeDataAfterDifference1, List<Float> magnitudeDataAfterDifference2,
			User user); // This function will contain the actual algorithm to be used, such as for
						// walkingclass, runing class etc.
}
