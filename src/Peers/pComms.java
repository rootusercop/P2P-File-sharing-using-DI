                                                                     
                                                                     
                                                                     
                                             
package Peers;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

import Peers.cComms;

class pComms implements Runnable {
	// private Socket p2pSocket;

	static int counter1 = 0;
	static int counter2 = 0;

	String temphostname;
	int indexcounter = 0;
//PeerProtocolParser ppp=new PeerProtocolParser();	
	
	pComms() {
	}

	// pComms(Socket p2pSocket) {
	// this.p2pSocket=p2pSocket;
	// }

	public void run() // run method of server peer
	{

		try {
			// BufferedReader inFromUser = new BufferedReader( new
			// InputStreamReader(System.in));

			// implementing rfc index as peerserver

			ServerSocket plistener = new ServerSocket(6791);
			Socket pserver;
			while (true) {

				// doComms connection;

				pserver = plistener.accept();
				BufferedReader inFromPeer = new BufferedReader(
						new InputStreamReader(pserver.getInputStream()));
				PrintWriter outToPeer = new PrintWriter(new OutputStreamWriter(
						pserver.getOutputStream()), true);
				// FileInputStream file = null;
				// OutputStream os = null;

			//	System.out.println("I am the peer server");
				// doComms conn_c= new doComms(server);
				// Thread t = new Thread(conn_c);
				// t.start();

				// message from peer client

			//	System.out.println("Waiting for request from Peer");

				// outToPeer.println("RFCQUERY");
				String pmsg = inFromPeer.readLine();
				String[] pmsgarray = pmsg.split(" ");

				// Code to be merged with the main program

				if (pmsgarray[0].equalsIgnoreCase("RFCIndex")) {
					int j = 0;
					int[] trfcno = new int[60];
					String[] trfctitle = new String[60];
					String[] tpeername = new String[60];
					int[] tpTTL = new int[60];

					
					// database of this peer
					int number=6561;
				for(int i=0;i<20; i++)
					{
					trfcno[i] = number;
					trfctitle[i]="RFC"+number;
					tpeername[i]=cComms.ip;
					tpTTL[i]=7200;
					number=number+1;
					}
				
				//	trfctitle[0] = "FTP";
				//	String temphost = InetAddress.getLocalHost().toString();
			    //	String[] temphostarray = temphost.split("/");
				//	tpeername[0] = cComms.ip;
				//	tpTTL[0] = 7200;

					cComms.rfcno[0] = trfcno[0];
					cComms.rfctitle[0] = trfctitle[0];
					cComms.peername[0] = tpeername[0];
					cComms.pTTL[0] = tpTTL[0];

					// indexcounter is the counter of the data elements in each
					// peer
					// String rfcindex="12 title hostname 10.139.58.43";
					String srfcindex = new String();
					for (int i = 0; i < trfcno.length; i++) // running till the no of rfc's that this peer has
					{

						srfcindex = srfcindex + " " + trfcno[i] + " "
								+ trfctitle[i] + " " + tpeername[i] + " "
								+ tpTTL[i];
					}
					//System.out.println(srfcindex);
					// have to write other rfc indexes
					System.out.println("RFC Index given");
					outToPeer.println(srfcindex);
				}

				if (pmsgarray[0].equalsIgnoreCase("GETRFC")) {
					
					
                    int reqrfc=Integer.parseInt(pmsgarray[1]);
					// serching for a particular rfc after the user input
					File file = new File("C:\\rfc"+reqrfc+".txt");
					// Get the size of the file
					long length = file.length();
					if (length > Integer.MAX_VALUE) {
						System.out.println("File is too large.");
					}
					byte[] bytes = new byte[(int) length];
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					BufferedOutputStream out = new BufferedOutputStream(
							pserver.getOutputStream());

					int count;

					while ((count = bis.read(bytes)) > 0) {
						out.write(bytes, 0, count);
					}
              //      System.out.println("RFC given to the Peer");
					out.flush();
					out.close();
					fis.close();
					bis.close();
					pserver.close();

				}
				/*
				 * { System.out.println("Accepted connection : "); File
				 * transferFile = new File ("D:\\rfc959.pdf"); byte [] bytearray
				 * = new byte [(int)transferFile.length()+1]; FileInputStream
				 * fin = new FileInputStream(transferFile); BufferedInputStream
				 * bin = new BufferedInputStream(fin);
				 * System.out.println("Bytearray length"+bytearray.length);
				 * 
				 * bin.read(bytearray,0,bytearray.length); OutputStream os =
				 * pserver.getOutputStream();
				 * System.out.println("Sending Files...");
				 * 
				 * os.write(bytearray,0,bytearray.length); int count; // while
				 * ((count = fin.read(bytearray)) >= 0) // {
				 * //System.out.println("Entered the while loop"); //
				 * os.write(bytearray, 0, count); // }
				 * 
				 * os.flush(); pserver.close();
				 * System.out.println("File transfer complete");
				 * 
				 * 
				 * }
				 */

				// File myFile = new File("D:\\"+cComms.rfcno[i]+".txt");
				/*
				 * File myFile = new File("D:\rfc959.txt"); FileInputStream file
				 * = null;
				 * 
				 * byte[] mybytearray = new byte[10240000]; file = new
				 * FileInputStream(myFile);
				 * 
				 * os = pserver.getOutputStream(); int count;
				 * 
				 * while ((count = file.read(mybytearray)) >= 0) {
				 * System.out.println("Entered the while loop");
				 * os.write(mybytearray, 0, count); } os.flush();
				 */
			}// end of while
		} // try closed */

		// if end
		// break;

		//
		// try end

		catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}

	}// end of run

}