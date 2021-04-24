package UserModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class NotifyUsers {
	private static final Logger logger = Logger.getLogger(NotifyUsers.class.getName());
	User user;
	ArrayList<String> emailNotSentList;
	boolean emailNotSentFlag;
	long thresholdTime = 1200000;//20 minutes timeout

	public NotifyUsers(User user) {
		this.user = user;
		this.emailNotSentList = new ArrayList<String>();
		this.emailNotSentFlag = false;
	}

	public void notifyByEmail() {
		ArrayList<String> dependents = getUser().getDependents();
		ArrayList<String> doctors = getUser().getDoctors();

		// set subject
		String msgSubject = "A Fall Occured to " + user.getName();
		EmailNotification.setMsgSubject(msgSubject);

		// set body
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(sdf.format(cal.getTime()));
		String msgBody = "A Fall Occured to " + user.getName() + " at " + sdf.format(cal.getTime());
		EmailNotification.setMsgBody(msgBody);

		long startTime = System.currentTimeMillis();
		// send email
		while (emailNotSentFlag == false) {
			emailNotSentFlag = true;
			for (String dependent : dependents) {
				if (!EmailNotification.sendMail(dependent, false, user)) {
					this.emailNotSentList.add(dependent);
					emailNotSentFlag = false;
				}
				long estimatedTime = System.currentTimeMillis() - startTime;
				if (estimatedTime >= thresholdTime) {
					System.out.println("Please check network access! Shutting down program");
					System.exit(1);
				}

			}

			dependents = new ArrayList<String>(emailNotSentList);
			this.emailNotSentList = new ArrayList<String>();

		}

		startTime = System.currentTimeMillis();
		// send email
		emailNotSentFlag = false;
		while (emailNotSentFlag == false) {
			emailNotSentFlag = true;
			for (String doctor : doctors) {

				if (!EmailNotification.sendMail(doctor, true, user)) {
					this.emailNotSentList.add(doctor);
					emailNotSentFlag = false;
				}
				long estimatedTime = System.currentTimeMillis() - startTime;
				if (estimatedTime >= thresholdTime) {
					System.out.println("Please check network access! Shutting down program");
					System.exit(1);
				}

			}

			doctors = new ArrayList<String>(emailNotSentList);
			this.emailNotSentList = new ArrayList<String>();

		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
