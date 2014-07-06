package Peers;

import java.io.*;
import java.net.*;
import java.util.*;



class Peer1
{
// private static int peerserverport=6790;
 static int cookie;
 static int portno;
 static String hostname;
 static InetAddress addr;
 static int choice;

static ArrayList<ActivePeer> actpeer = new ArrayList<ActivePeer>();
 
 //static ArrayList<RFCIndex> peerlist =new ArrayList<RFCIndex>();
 public static void main(String args[]) throws Exception
 {
do
 {
	String acthostname[]=new String[30];
	  int actportno[]=new int[30];
	Socket clientSocket = new Socket("127.0.0.1", 6789);
  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
  PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
  
  outToServer.println("Peer Requesting Connection");
  
  System.out.println("Enter the preferred choice");
  System.out.println("1. REGISTER");
  System.out.println("2. LEAVE");
  System.out.println("3. PQUERY");
  System.out.println("4. Do you want to EXIT");
  System.out.println("*********************************************");
  choice=Integer.parseInt(inFromUser.readLine());
  
  switch(choice)
  {
  case 1:
// System.out.println("Case 1 entered"); // testing statement
  outToServer.println("REG cookieno -1 portno 6789 hostname 1.1.1.2");
  cookie=Integer.parseInt(inFromServer.readLine());
  System.out.println("peer "+cookie); //peer cookie value
  System.out.println(inFromServer.readLine()); //server cookie value
  break;
  
  case 2:
 // System.out.println("Case 2 entered"); testing statement
  System.out.println("LEAVE cookieno "+cookie);
  outToServer.println("LEAVE cookieno "+cookie);
 // System.out.println("I am in peer");   testing statement
  System.out.println(inFromServer.readLine());
  break;
  
  case 3:
  System.out.println("Case 3 entered");
  outToServer.println("PQUERY cookieno "+cookie+" portno 6789");
 // System.out.println("Entered peer again");
 // outToServer.println("KEEP ALIVE cookieno "+cookie);
   String details= inFromServer.readLine();
   String[] darray= details.split(" ");
   int j=0;
  
  // System.out.println("Array length"+darray.length);
   for (int i=0; i<darray.length;i= i+2)
   {
	   acthostname[j]=darray[i];
	   System.out.println("hostname "+acthostname[j]);
	   actportno[j]= Integer.parseInt(darray[i+1]);
	   System.out.println("portno "+actportno[j]);
	   j=j+1;
	  
   }
		
   //System.out.println(InetAddress.getByName(acthostname[0]));
  //inFromServer
  break;
  }//end of switch
  clientSocket.close();
  System.out.println("Connection closed");
  
  
  // implementing rfc index as server
try{
  ServerSocket spsocket=new ServerSocket(6790);
  Socket psocket;
  while(true)
  {
	  pComms pconn;
	  psocket=spsocket.accept();
	  pComms conc= new pComms(psocket);
	  Thread t = new Thread(conc);
	  t.start();
  }
	  
  }// end of try
catch (IOException poe)
{
	System.out.println("IOException on socket listen"+poe);
	poe.printStackTrace();
} 
  
 }//end of do
 while(choice!=4); // end of do while




















import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

import Peers.*;

class pComms implements Runnable {
    private Socket pserver;
    
    /* int cookieTemp;
    int portTemp;
    String temphostname;
   
   */

    pComms(Socket psocket) {
      this.psocket=psocket;
    }
   
	public void run () {
       
		/*
	   String delims;
       System.out.println("a");
       Client c= new Client();
       Server.clientlist.add(c);
       System.out.println("a");
      
      */
      try {
        // Get input from the client
    	  
    
    	BufferedReader in = new BufferedReader(new InputStreamReader(pserver.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(pserver.getOutputStream()), true);
       
     
        
        String sstore = in.readLine();
        if(sstore.equalsIgnoreCase("RFCquery"))
        { RFCIndex rfc = new RFCIndex 
        
        }
        	for (int i=0;i<(Server.clientlist.size())/2;i++)
        	{     
                   System.out.println("size"+Server.clientlist.size());
        		if(Server.clientlist.get(i).isActive()==true)
        		{System.out.println("scgg"+Server.clientlist.get(i).getHostname());
        		 details= details+Server.clientlist.get(i).getHostname()+" "+Server.clientlist.get(i).getPortno()+" ";	 
        		}		
        	  }
            }
        }
        	
        	
        		}
/*
// Listen for incoming connections and handle them
  try{
    ServerSocket peerlistener = new ServerSocket(peerserverport);
    Socket p2psocket;

    while(true){  // threads for accepting multiple peer request at the same time 	  
      pComms connection;
      p2psocket = peerlistener.accept();
      pComms conn_c= new pComms(p2psocket);
      Thread t = new Thread(conn_c);
      t.start();
    }
  } catch (IOException ioe) {
    System.out.println("IOException on socket listen: " + ioe);
    ioe.printStackTrace();
  }
}
*/


 }// end of main
 }// end of class
