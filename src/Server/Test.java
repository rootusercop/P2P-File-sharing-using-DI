package Server;

import java.lang.*;
import java.io.*;

public class Test {
	public static void main(String[] args)
	{
		System.out.println("OS: "+System.getProperty("os.name")+" "+"v"+System.getProperty("os.version"));
		System.out.println("User: "+System.getProperty("user.name"));
		System.out.println("Java Version: "+System.getProperty("java.version")+" "+"Java Vendor: "+System.getProperty("java.vendor"));
		System.out.println("Java Vendor: "+System.getProperty("java.vendor"));
		System.out.println();
		System.getProperties().list(System.out);
		
	}

}
