package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

	/* 
	 *
	 *
	 */

	private static int port = 6500;
	// Listen for incoming connections and handle them
	static ArrayList<Client> clientlist = new ArrayList<Client>();

	public static void main(String[] args) {

		int i = 0;

		try {
			ServerSocket listener = new ServerSocket(port);
			Socket server;

			while (true) {

				// doComms connection;

				server = listener.accept();
		//		System.out.println("Main Server");
				doComms conn_c = new doComms(server);
				Thread t = new Thread(conn_c);
			//	System.out.println(clientlist.isEmpty());
				t.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}

}