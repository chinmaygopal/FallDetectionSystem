package UserModule;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.lang.Object;

public class EmailNotification {
	private static String msgBody;
	private static String msgSubject;
	private static Session session;
	private static boolean isSessionSet = false;
	private static Properties properties;

	public String getMsgBody() {
		return msgBody;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		EmailNotification.session = session;
	}

	public EmailNotification() {
		session = null;
	}

	public static void setMsgSubject(String msgSubjectExt) {
		msgSubject = msgSubjectExt;
	}

	public String getMsgSubjectBody() {
		return msgSubject;
	}

	public static void setMsgBody(String msgBodyExt) {
		msgBody = msgBodyExt;
	}

	public static void login(String to) {
		boolean returnNoError = false;

		// Sender's email ID needs to be mentioned
		String from = "collegeprojects195@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties

		properties = System.getProperties();
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.user", to);
		properties.setProperty("mail.password", "chInmAy123");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.sendpartial", "true");

	}

	public static boolean sendMail(String to, boolean dependentIsADoctor, User user) {
		// Sender's email ID needs to be mentioned
		String from = "collegeprojects195@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";
		String password = "Infosys1234";

		boolean returnNoError = false;
		returnNoError = InternetAvailabilityChecker.checkInternet(host);
		if (!isSessionSet) {
			isSessionSet = true;
			login(to);
		}

		if (returnNoError) {
			// Get the default Session object.
			session = Session.getDefaultInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject(msgSubject);

				// Now set the actual message
				// message.setText(");

				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Now set the actual message
				messageBodyPart.setText(msgBody);

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				if (dependentIsADoctor) {
					// Part two is attachment
					messageBodyPart = new MimeBodyPart();

					String localDir = System.getProperty("user.dir");

					String filename = localDir + "\\src\\UserModule\\" + user.getId() + ".txt";
					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName("Fall Data for " + user.getId() + ".txt");
					multipart.addBodyPart(messageBodyPart);
				}

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");
				returnNoError = true;
			}
			// catch (SMTP)
			catch (MessagingException mex) {
				returnNoError = false;
				mex.printStackTrace();
				return returnNoError;
			}
		} else {
			returnNoError = false;
		}
		return returnNoError;

	}

}
