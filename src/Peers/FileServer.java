package Peers;

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.channels.*;

public class FileServer {
	 public static void main(String args[]) throws IOException
	   {
	     ServerSocket server = new ServerSocket(6790);
	     File myFile = new File("E:\\file1.pdf");
	     FileInputStream file = null;
	     OutputStream os = null;
	     Socket sock=null;

	     sock = server.accept();

	     try
	     {

	        byte[] mybytearray = new byte[1024000];
	        file = new FileInputStream(myFile);
	        os = sock.getOutputStream();


	        int count;
	        while ((count = file.read(mybytearray)) >= 0) {
	           os.write(mybytearray, 0, count);

	        }

	        os.flush();
	     }


	        catch(IOException e)
	        {
	           System.out.println("No file");
	        }
	        catch(IllegalBlockingModeException ea)
	        {
	           System.out.println("blah!");
	        }

	     finally
	     {
	        System.out.println("hello");
	        file.close();
	        os.close();
	        sock.close();

	        System.out.println("Socket closed");
	     }

	  }

}
