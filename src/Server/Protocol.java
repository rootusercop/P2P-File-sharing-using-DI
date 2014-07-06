                                                                     
                                                                     
                                                                     
                                             
package Server;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;

public class Protocol {
	Socket server;
	ObjectOutputStream poos;
	ObjectInputStream pois;
	int inactivelocal=1;

	Protocol() {}


	public static int counter = 0;

	int register(int cookieTemp, int portTemp, String temphostname) {

		if (cookieTemp == -1) {
		//	System.out.println("Entered register protocol");
			Server.clientlist.get(counter).setHostname(temphostname); // have to see this code
		//	System.out.println("counter:"+counter);
		//	System.out.println("hostname"+ temphostname);
		//	System.out.println(Server.clientlist.get(0).getHostname());
			Server.clientlist.get(counter).setCookie(counter+1);
			Server.clientlist.get(counter).setPortno(portTemp);
			Server.clientlist.get(counter).setActive(true);
			counter=counter+1;
			try{
			Server.clientlist.get(counter).setTTL();}
			catch(Exception e){}
			
		}
		return Server.clientlist.get(counter-1).getCookie();
		// else
	}

	void leave(int cookieTemp) {
		//System.out.println(Server.clientlist.size());
		for (int i = 0; i < Server.clientlist.size(); i++) {
		//	System.out.println(Server.clientlist.get(i).getCookie());
			if (Server.clientlist.get(i).getCookie() == cookieTemp)
				Server.clientlist.get(i).setActive(false);// de register the peer
		}

	}

	void keepalive(int cookieTemp) throws Exception {
		for (int i = 0; i < Server.clientlist.size(); i++) {
			if (Server.clientlist.get(i).getCookie() == cookieTemp)
			{
			Server.clientlist.get(i).setTTL();	
			}
				 // TTL initialized to 7200
		}
	}

	String pquery() throws Exception
{
		String pdetails=new String();
	//	System.out.println("Entered protocol pquery");
	for (int i=0;i<Server.clientlist.size();i++)
		//for (int i=0;i<;i++)
	{     
        
		if(Server.clientlist.get(i).isActive()==true)
		{//System.out.println(Server.clientlist.get(i).getHostname());
		 pdetails= pdetails+" "+Server.clientlist.get(i).getHostname()+" "+Server.clientlist.get(i).getPortno();	 
		//System.out.println("processed details "+pdetails);
		 inactivelocal=0;
		 }		
	    }
   System.out.println("Active peer details : "+pdetails);
      return(pdetails+inactivelocal);

}// end of pquery

}// end of class