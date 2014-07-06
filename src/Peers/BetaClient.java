package Peers;

import java.io.*;
import java.net.*;
import java.util.*;

class BetaClient
{
public static void main(String args[]) throws Exception
 {
  Socket clientSocket = new Socket("127.0.0.1", 6785);
  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
  PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
  outToServer.println("Hello");
  outToServer.print(10);
  
 }
}




