package UserModule;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class InternetAvailabilityChecker {
	public static boolean isInternetAvailable() throws IOException {
		return isHostAvailable("google.com") || isHostAvailable("amazon.com") || isHostAvailable("facebook.com")
				|| isHostAvailable("apple.com");
	}

	private static boolean isHostAvailable(String hostName) throws IOException {
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 3000);

			return true;
		} catch (UnknownHostException unknownHost) {
			System.out.println("Internet is possibly down");
			return false;
		}
	}

	public static boolean isReachableByPing(String host) {
		try {
			String cmd = "";
			if (System.getProperty("os.name").startsWith("Windows")) {
				// For Windows
				cmd = "ping -n 1 " + host;
			} else {
				// For Linux and OSX
				cmd = "ping -c 1 " + host;
			}

			Process myProcess = Runtime.getRuntime().exec(cmd);
			myProcess.waitFor();

			if (myProcess.exitValue() == 0) {

				return true;
			} else {
				System.out.println(host + " is not reachable");
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("finally")
	public static boolean checkInternet(String host) {
		boolean returnVal = false;
		try {
			if (InternetAvailabilityChecker.isInternetAvailable()
					&& InternetAvailabilityChecker.isReachableByPing(host)) {
				returnVal = true;
			} else {
				returnVal = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			returnVal = false;
			e.printStackTrace();
		} catch (Exception e) {
			returnVal = false;
			e.printStackTrace();
		} finally {
			return returnVal;
		}

	}

}