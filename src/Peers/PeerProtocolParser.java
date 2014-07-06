package Peers;

import java.io.*;
import java.util.*;

public class PeerProtocolParser {

	

	public static void main(String args[]){
		

		// append this with the protocol

		System.out.println("P2P-DI/1.1");
		System.out.println("OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version"));
		System.out.println("User: " + System.getProperty("user.name"));
		System.out.println("Java Vendor: " + System.getProperty("java.vendor")+ "/" + "Java Version: " + System.getProperty("java.version"));
		 System.getProperties().list(System.out);
		String res = "200 OK";
		System.out.println("P2P-DI/1.1");
		System.out.println("OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version"));
		System.out.println("User: " + System.getProperty("user.name"));
		System.out.println("Java Vendor: " + System.getProperty("java.vendor")+ "/" + "Java Version: " + System.getProperty("java.version"));
		 System.getProperties().list(System.out);
	}

}
