/*
FileName: InfoClient.java
Authors
kamugisha maurice 10/U/9243/PS  210015398
karamagi phillip  10/U/15599/PS 210021293
lubega mark       10/U/1806/PS  210000411
mukasa samuel     10/U/9337/PS  210007604
mutungi stephen   10/U/9352/PS  210007639
semakula kraiba   10/U/9446/PS  210006461
ziwa ali          10/U/9477/PS  210006468   
niwagaba aaron	  09/U/12306/PS 209008071
*/
/*
FileName: Searchfile.java
Authors
kamugisha maurice 10/U/9243/PS  210015398
karamagi phillip  10/U/15599/PS 210021293
lubega mark       10/U/1806/PS  210000411
mukasa samuel     10/U/9337/PS  210007604
mutungi stephen   10/U/9352/PS  210007639
semakula kraiba   10/U/9446/PS  210006461
ziwa ali          10/U/9477/PS  210006468   
niwagaba aaron	  09/U/12306/PS 209008071
*/
import java.net.*;
import java.io.*;
import java.util.*;

public class InfoClient
{
   public static void main(String [] args)
   { //declare variables 
      String msg;
	  String serverName = args[0];
	  Scanner red = new Scanner(System.in);
      int port = Integer.parseInt(args[1]);
      try
      { //client connection to server 
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         //output if connection is established
		 System.out.println("Just connected to "+ client.getRemoteSocketAddress());
         
		 OutputStream outToServer = client.getOutputStream(); // store commands to server in variable
        
		 DataOutputStream out = new DataOutputStream(outToServer);
         msg = red.nextLine();
         out.writeUTF(msg);
         InputStream inFromServer = client.getInputStream();//get replys from server
         DataInputStream in = new DataInputStream(inFromServer);
         System.out.println("Server reply:  " + in.readUTF());//output server reply to client 
         client.close(); //close client
      }catch(IOException e)//catch IOException
      {
         e.printStackTrace();
      }
   }
}