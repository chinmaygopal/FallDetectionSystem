package UserModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

public class NotifyStakeholders {
	private static final Logger logger = Logger.getLogger(NotifyUsers.class.getName());
	User user;
	static ArrayList<String> emailNotSentList;
	static boolean emailNotSentFlag;
	static long thresholdTime = 1200000;//20 minutes timeout

	public static void notifyByEmail(boolean notifyEmergencyServices) {
		String localDir = System.getProperty("user.dir");
		
		String filePath = localDir+"\\src\\UserModule\\StakeholderData.json";
		
		ArrayList<String> responders = new ArrayList<String>();
		JSONStakeholderData.readJSON(filePath, notifyEmergencyServices);

		emailNotSentFlag = false;
		emailNotSentList = new ArrayList<String>();

		long startTime = System.currentTimeMillis();

		// set subject
		String msgSubject = "Temporary system unavailabilty";
		EmailNotification.setMsgSubject(msgSubject);

		// set body
		String msgBody = "The system is temporarily unavailable. We will get back to you soon.";
		EmailNotification.setMsgBody(msgBody);

		// send email
		startTime = System.currentTimeMillis();
		// send email
		emailNotSentFlag = false;
		while (emailNotSentFlag == false) {
			emailNotSentFlag = true;
			for (String responder : responders) {

				if (!EmailNotification.sendMail(responder, false, null)) {
					emailNotSentList.add(responder);
					emailNotSentFlag = false;
				}
				long estimatedTime = System.currentTimeMillis() - startTime;
				if (estimatedTime >= thresholdTime) {
					System.out.println("Please check network access! Shutting down program");

					System.exit(1);
				}

			}

			responders = new ArrayList<String>(emailNotSentList);
			emailNotSentList = new ArrayList<String>();

		}

	}

}
