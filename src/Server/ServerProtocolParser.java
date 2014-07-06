package Server;

import java.io.*;
import java.util.*;

public class ServerProtocolParser {

	String protocol = "P2P-DI/1.1";

	public void reqparser(String s) {
		String[] sarray = s.split(" ");

		String first = sarray[0]; // append this with the protocol

		System.out.println(first + " " + protocol);
		System.out.println("OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version"));
		System.out.println("User: " + System.getProperty("user.name"));
		System.out.println("Java Vendor: " + System.getProperty("java.vendor")+ "/" + "Java Version: " + System.getProperty("java.version"));
	}

	public void resparser() {
		String res = "200 OK";
		System.out.println(res + " " + protocol);
		System.out.println("OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version"));
		System.out.println("User: " + System.getProperty("user.name"));
		System.out.println("Java Vendor: " + System.getProperty("java.vendor")+ "/" + "Java Version: " + System.getProperty("java.version"));

	}

}
