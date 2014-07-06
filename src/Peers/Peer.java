package Peers;

import java.io.*;
import java.net.*;
import java.util.*;



class Peer
{
 //private static int peerserverport=6790;
 static int cookie;
 static int portno;
 static String hostname;
 static InetAddress addr;
 static int choice;
  // for distributed test case, we can change this value if required
 
//static ArrayList<ActivePeer> actpeer = new ArrayList<ActivePeer>();
 
 //static ArrayList<RFCIndex> peerlist =new ArrayList<RFCIndex>();
 public static void main(String args[]) throws Exception
 {
	 
	  try{                   // for the server peer
		
		 ServerSocket plistener = new ServerSocket(6793);
	      Socket pserver;              
          pComms pconn_c= new pComms();
	      Thread t1 = new Thread(pconn_c);
	      t1.start();
	      
	//      Socket pclientSocket = new Socket("127.0.0.1", 6500);    // for the client peer 127.0.0.1 is main server's address
	      cComms cconn_c=new cComms();
	      Thread t2= new Thread(cconn_c);
	      t2.start();      
	      }
	      
	      
	      catch (IOException ioe) {
	      System.out.println("IOException on socket listen: " + ioe);
	      ioe.printStackTrace();
	    }
	      
	      
 } // end of main
 
}// end of class
