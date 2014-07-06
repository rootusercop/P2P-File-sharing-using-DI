                                                                     
                                                                     
                                                                     
                                             
                                                                     
                                                                     
                                                                     
                                             
package Peers;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

import Peers.pComms;

public class cComms implements Runnable {
	private Socket clientSocket;
	String temphostname;
	static private int TTL;
	static String acthostname[] = new String[60];
	int actportno[] = new int[60];
	public static String ip;
	cComms() {
	}


	static int z = 0;
	static int j = 0;
	static int cookie;
	static int portno;
	static String hostname;
	static InetAddress addr;
	static int choice;
	int arraybound=0;
/*
	int reqrfc;
	static int[] rfcno = new int[100];
	static String[] peername = new String[100];
	static String[] rfctitle = new String[100];
	static int[] pTTL = new int[100];
    CurrentTime ct= new CurrentTime();
	*/
	int reqrfc;
	static int[] rfcno = new int[3600];
	static String[] peername = new String[3600];
	static String[] rfctitle = new String[3600];
	static int[] pTTL = new int[3600];
    CurrentTime ct= new CurrentTime();
    int inactive=0;
    
	int[] trfcno = new int[60];
	String[] trfctitle = new String[60];
	String[] tpeername = new String[60];
	int[] tpTTL = new int[60];

	static int counter1 = 0;
	static int counter2 = 0;

	public void run() {

		do {
			try {
				BufferedReader inFromUser = new BufferedReader(
						new InputStreamReader(System.in));
				File file = new File("time.txt");
				FileWriter fw = new FileWriter(file);
				Long start = 0l;
				Long end = 0l;
				BufferedWriter bw = new BufferedWriter(fw);
				System.out.println("Enter the preferred choice");
				System.out.println("1. REGISTER");
				System.out.println("2. LEAVE");
				System.out.println("3. SEARCH FOR RFC");
				System.out.println("4. KEEPALIVE");
				System.out.println("5. Do you want to EXIT");
				System.out
						.println("*********************************************");

				choice = Integer.parseInt(inFromUser.readLine());
				// System.out.println(" Client requesting for connection");
				clientSocket = new Socket("192.168.15.103", 6500);
				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter outToServer = new PrintWriter(
						new OutputStreamWriter(clientSocket.getOutputStream()),
						true);

				// outToServer.println("Peer Requesting Connection");

				switch (choice) {
				case 4: outToServer.println("KEEP ALIVE P2P-DI Cookieno " + cookie+" OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version")+ " USER: "+System.getProperty("user.name"));
                        System.out.println(inFromServer.readLine());
                        System.out.println("*********************************************");
                        break;
				case 1:
					try {
						//System.out.println("Case 1 entered"); // testing
																// statement
						System.out.println("Please enter your IP addres");
						ip= inFromUser.readLine();	
						outToServer.println("REG P2P-DI/1.1 -1 Portno 6789 Hostname "+ip+ " OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version")+ " USER: "+System.getProperty("user.name") );
						cookie = Integer.parseInt(inFromServer.readLine());
						TTL = 7200;
						

						System.out.println(inFromServer.readLine());
						System.out.print("You are registered at time : ");
						ct.currenttime();
						System.out.println("Peer " + cookie); // peer cookie value
						System.out.println("TTL Value :" + TTL);				
						System.out.println("*********************************************");
					
						break;
					} catch (Exception e) {
					}
				case 2:

					// System.out.println("Case 2 entered"); testing statement					
					outToServer.println("LEAVE P2P-DI Cookieno " + cookie+" OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version")+ " USER: "+System.getProperty("user.name"));
					// System.out.println("I am in peer"); testing statement
					System.out.println(inFromServer.readLine());
					System.out.println("*********************************************");
					break;

				case 3:
					outToServer.println("PQUERY P2P-DI Cookieno " + cookie+ " Portno 6789"+" OS: " + System.getProperty("os.name") + " " + "v"+ System.getProperty("os.version")+ " USER: "+System.getProperty("user.name") );
					System.out.println("Which RFC number do you wish to have ?");
					reqrfc = Integer.parseInt(inFromUser.readLine());
					
					
					// System.out.println("Entered peer again");
					// outToServer.println("KEEP ALIVE cookieno "+cookie);
			
					String details = inFromServer.readLine();
					
					String[] parray = details.split(" ");
					int inactive=Integer.parseInt(parray[(parray.length-1)]);
					// System.out.println(darray.length);
					if((parray.length==3)&&(ip.equals(parray[1])))	
					{
						System.out.println("P2P-DI No Active Peers available");
						System.out.println("*********************************************");
					}
					else
						{
						System.out.println("****<POPULATING THE ACTIVE PEER LIST>****");
						System.out.println();
						System.out.println("The active peer list is as follows:");
						System.out.println();
						
						String[] darray = details.split(" ");
						
					// System.out.println("Array length"+darray.length);
					for (int i = 0; i < (darray.length - 2); i = i + 2) {

						acthostname[j] = darray[i + 1];
						System.out.println("Hostname :" + acthostname[j]);

						actportno[j] = Integer.parseInt(darray[i + 2]);
						System.out.println("Portno :" + actportno[j]);

						System.out.println("*****************************");
						j = j + 1;
					}

					System.out.println("Connecting to the active peers for its RFC Index");
					for(int x=0; x<j; x++)
					{
						//System.out.println(ip);
						if(!(acthostname[x].equals(ip)))
						{	
					System.out.println("Connecting to "+acthostname[x]);
					
		        	Socket peersocket = new Socket(acthostname[x], 6791);// implement
		  																// a for
																		// loop
					
					
					
					BufferedReader inFromPeer = new BufferedReader(
							new InputStreamReader(peersocket.getInputStream()));
					PrintWriter outToPeer = new PrintWriter(
							new OutputStreamWriter(peersocket.getOutputStream()),
							true);
					
					
					outToPeer.println("RFCIndex");
					// System.out.println(inFromServer.readLine());
					// int searchrfc=Integer.parseInt(inFromUser.readLine());
					// outToServer.println(searchrfc);

					String rfcindex = inFromPeer.readLine(); // tell server to
																// send rfc in
																// string
					String rfcarray[] = rfcindex.split(" ");
				//  System.out.println(rfcindex);
					for (int i = 1; i< rfcarray.length; i = i + 4) {
						trfcno[z] = Integer.parseInt(rfcarray[i]);
					//	System.out.println("RFC number " + trfcno[z]);
						trfctitle[z] = rfcarray[i + 1];
					//	System.out.println("RFC Title " + trfctitle[z]);
						tpeername[z] = rfcarray[i + 2];
					//	System.out.println("Peer Ip Address " + tpeername[z]);
						tpTTL[z] = Integer.parseInt(rfcarray[i + 3]);
					//	System.out.println("TTL value :" + tpTTL[z]);

						counter1 = counter1 + 1;
						
						z = z + 1;
					}
					z=0;
          //         if(arraybound==0)
          //         {
					System.arraycopy(trfcno, 0, rfcno, counter2, trfcno.length);
					System.arraycopy(trfctitle, 0, rfctitle, counter2,
							trfctitle.length);
					System.arraycopy(tpeername, 0, peername, counter2,
							tpeername.length);
					System.arraycopy(tpTTL, 0, pTTL, counter2, tpTTL.length);
					z=0;
					counter2 = counter1;
                    counter1=0;
           //         arraybound=arraybound+1;
           //        }
                  
					System.out.println();
					System.out.println();
					System.out.println("*************************************************");
					System.out.println("RFC Index received from the Peer");
			//		System.out.println();
					System.out.println("\n-----------------------------------------");
					System.out.println("RFC Index System - Display RFC Idex");
					System.out.println("-------------------------------------------");
					System.out.format("%10s%15s%15s%10s", "RFC No", "RFC Title", "Peer Name", "TTL Value");
					System.out.println();
					// StudentNode current = top;
					// while (current != null){
					// Student read = current.getStudentNode();
					for (int i = 0; i < 60; i++) {
						System.out.format("%10s%15s%15s%10s", " " + rfcno[i],
								rfctitle[i], peername[i], " " + pTTL[i]);
						System.out.println();
					}
					// This will output with a set number of character spaces
					// per field, giving the list a table-like quality
					// }
					
					peersocket.close();
					} // end of if
						
						
						 for(int i=0; i<rfcno.length;i++)
		                    {
		                    	
		                    if(rfcno[i]==reqrfc)
		                    {
							String taddress = InetAddress.getByName(peername[i]).toString();
							String[] taddr = taddress.split("/");
							InetAddress tproperaddress = InetAddress.getByName(taddr[1]);
						//	System.out.println("Inetaddress" + tproperaddress);

							Socket peersocket1 = new Socket(tproperaddress, 6791);// implement
																					// a
																					// for
																					// loop
							System.out.println("The connection to the Active Peer is establshed");
							BufferedReader inFromP2P = new BufferedReader(
									new InputStreamReader(peersocket1.getInputStream()));
							PrintWriter outToP2P = new PrintWriter(
									new OutputStreamWriter(
											peersocket1.getOutputStream()), true);
							
							
							
							System.out
							.println("Requested the RFC to the Active Peer Server");
			       
							
							start = System.currentTimeMillis();
												
							outToP2P.println("GETRFC " +reqrfc);
														

							// Socket socket = ;

							try {

								// Socket socket = null;
								InputStream is = null;
								FileOutputStream fos = null;
								BufferedOutputStream bos = null;
								int bufferSize = 0;

								try {
									is = peersocket1.getInputStream();

									bufferSize = 64;
									//System.out.println("Buffer size: " + bufferSize);
								} catch (IOException ex) {
									System.out
											.println("Can't get socket input stream. ");
								}

								try {
									fos = new FileOutputStream("E:\\rfc" + reqrfc+ "copy.txt");
									bos = new BufferedOutputStream(fos);

								} catch (FileNotFoundException ex) {
									System.out.println("File not found. ");
								}

								byte[] bytes = new byte[bufferSize];

								int count;

								while ((count = is.read(bytes)) > 0) {
								//	System.out.println(count);
									bos.write(bytes, 0, count);
								}
								System.out.println("P2P-DI 200 OK The RFC is copied");
								end = System.currentTimeMillis();
                                System.out.println("Total Time to download file   "+(end - start)+ " milliseconds");
								
								bos.flush();
								
								bos.close();
								is.close();
								peersocket1.close();
								break;
								
								
									
								
								
								
							} catch (SocketException e) {
								System.out.println("Socket exception");
							}
							
						} // end of if
		                    else
		                    {
		                  //  	System.out.println("No Peer with the required RFC could be found");
		                    	
		                    }
						clientSocket.close();
					//	System.out.println("Connection closed");
						bw.close();
						fw.close();
		                    }		// end of inner for
						
					
					} // end of outer for
					System.out.println("Connection closed");	
				}// end of switch
				}// end of else which checks the inactive conditions
			}// end of try
			catch (IOException ioe) {
				System.out.println("IOException on socket listen: " + ioe);
				ioe.printStackTrace();
			}

		}// end of do
		while (choice != 5);
		

	}// end of run
}// end of class