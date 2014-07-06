package Server;

	
	import java.io.*;
	import java.net.*;
	import java.util.*;
	class BetaServer
	{
		public static void main(String args[])throws Exception
		{
		ServerSocket ws=new ServerSocket(6785);
		while(true)
		{
			Socket cs=ws.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
	        PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
	        System.out.println(in.readLine());
	        System.out.println(in.read());
	}
	}
	}


