package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

import Peers.*;

class doComms implements Runnable {
	private Socket server;
	int cookieTemp;
	int portTemp;
	String temphostname;
	int counter = 0;
	int globalactive=0;
	CurrentTime ct=new CurrentTime();
ServerProtocolParser spp=new ServerProtocolParser();
	doComms(Socket server) {
		this.server = server;
	}

	public void run() {
		String delims;
		// System.out.println("a");

		// System.out.println("a");
		try {
			// Get input from the client

			// System.out.println("I am in the server second time");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					server.getOutputStream()), true);

			// System.out.println(in.readLine());
			// if()
			String line = in.readLine();
			String[] msgarray = line.split(" ");

			// System.out.println(msgarray.length);
			int size = msgarray.length;
			// Protocol q=new Protocol(out);
			// System.out.println("a");
			// System.out.println(in.readLine());

			Protocol p = new Protocol();

			System.out.println(line);
			while (true) {
			//	System.out.println("Entered split function");
				// System.out.println("b");
				delims = " ";
				String[] tokens = line.split(" ");
				// cookieTemp=Integer.parseInt(tokens[2]);
				// portTemp=Integer.parseInt(tokens[4]);
				// temphostname= tokens[6];
				if (size == 13 && tokens[0].equalsIgnoreCase("REG")) {
					cookieTemp = Integer.parseInt(tokens[2]);
					portTemp = Integer.parseInt(tokens[4]);
					Client c = new Client();
					Server.clientlist.add(c);
					counter = counter + 1;
					temphostname = tokens[6];
					int a = p.register(cookieTemp, portTemp, temphostname);
					out.println(a);// Return the cookie to the client
					out.println("P2P-DI 200 OK You are registered with cookie number: " + a );
					
				}
				if (size == 10 && tokens[0].equalsIgnoreCase("LEAVE")) {
					cookieTemp = Integer.parseInt(tokens[3]);
					System.out.println("in server" + cookieTemp);
					p.leave(cookieTemp);
				
					out.println("P2P-DI 200 OK You are De-registered");
				}
				if (size == 12 && tokens[0].equalsIgnoreCase("PQUERY"))

				{
				//	System.out.println("Entered server leave");

					cookieTemp = Integer.parseInt(tokens[3]);
					portTemp = Integer.parseInt(tokens[5]);
					try {
						String details = p.pquery();
						// System.out.println(details);
						out.println(details);
						// dooos.writeObject(storehostname);
					} catch (Exception e) {
					}
				}
				if (size == 11 && tokens[0].equalsIgnoreCase("KEEP")) {
					cookieTemp = Integer.parseInt(tokens[4]);
					try {
						p.keepalive(cookieTemp);
					} catch (Exception e) {
					}
					out.println("P2P-DI 200 OK Your TTL is intialized to 7200");
				}
				break;
				// out.println("I got:" + line);
				
				
			}// end of while

			/*
			 * Now write to the client System.out.println("Overall message is:"
			 * + input); out.println("Overall message is:" + input);
			 */
		}// end of try
		catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}// end of run
}// end of class