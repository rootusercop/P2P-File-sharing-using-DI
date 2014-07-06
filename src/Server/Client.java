package Server;

import java.util.*;
import java.net.*;
import java.lang.*;

public class Client {
	public static String hostname;
	public static int cookie;
	public static boolean active;
	public static int TTL = 7200;
	public static int portno;
	public static int ActiveConn;
	public static CurrentTime timestamp;

	public static String getHostname() {
		return hostname;
	}

	public void setHostname(String temphostname) {
		hostname = temphostname;
		
	}

	public int getCookie() {
		return cookie;
	}

	public void setCookie(int tempcookie) {
		cookie = tempcookie;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean tempactive) {
		active = tempactive;
	}

	public int getTTL() {
		return TTL;
	}

	public void setTTL() throws Exception {
		TTL = 7200;
		Thread.sleep(1000);
		TTL = TTL - 1;
	}

	public int getPortno() {
		return portno;
	}

	public void setPortno(int tempportno) {
		portno = tempportno;
		
	}

	public int getActiveConn() {
		return ActiveConn;
	}

	public void setActiveConn(int tempactiveConn) {
		ActiveConn = tempactiveConn;
	}

	public CurrentTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(CurrentTime timestamp) {
		this.timestamp = timestamp;
	}

}
