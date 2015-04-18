/* 
File Name: InfoServer.java
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
public class InfoServer extends Thread
{
   private ServerSocket serverSocket; //create private object of type ServerSocket
   
   public InfoServer(int port) throws IOException //constructor for InfoServer
   {
      serverSocket = new ServerSocket(port, 5); //create a new object serverSocket listening on port defined and number of clients that can wait in queue
      serverSocket.setSoTimeout(900000); //Time after which the Socket will time out or go off
   }

   public void run()
   { 
	//variables for integers that will be given by client later on   
	int questionnairenumber = 0;
	int questionnnariresreturned =0;
    String command;
   while(true)
      {
         try
         {
            System.out.println("Listening for client connections on port " + serverSocket.getLocalPort() + "...");
         
			Socket server = serverSocket.accept(); //Listen and Accept Connection to (this) server
            System.out.println( server.getRemoteSocketAddress() +" just connected to Server\n");
            DataInputStream in = new DataInputStream(server.getInputStream());
            String x = in.readUTF(); //Save Input from the client in variable
		   	 
			 //break down the input string at every single space
		    StringTokenizer st = new StringTokenizer(x, "= ");
			int numberTokens = st.countTokens(); //count number of tokens from string
			String [] currentToken = new String [numberTokens]; //create a String array in which to store the tokens
			 
			 //Add All Tokens in String To the Array
			 for(int i=0; i<numberTokens; i++ ){
			   String val = st.nextToken();
			   currentToken[i] = val; 
			 }
			 //initialize variables to used for processing and feedback 
             if(currentToken.length>0){
			  command = currentToken[0]; //save the first array element as command given by client 
			 }
			 else{
			 command = "nothing"; //assign command nothing if client input is empty or whitespace 
			 }
			 String answer;
			 String feed;
			 String feedserver = "The client has ";
			 String feedclient = "You have ";
			 
//Compare Command given and use appropiate function
if (command.equalsIgnoreCase("add")){
	answer = "add";
	System.out.println(feedserver + " selected " + answer);
	if (numberTokens == 5){
		try{	
			questionnairenumber = Integer.parseInt(currentToken[2]);
			questionnnariresreturned = Integer.parseInt(currentToken[3]);
		}
		catch(NumberFormatException nFFe){
			questionnairenumber = -4040;
			questionnnariresreturned =5248;
		}
		
		//Make sure number of questionnares meets our desired criteria
		if( questionnairenumber >= questionnnariresreturned && ((questionnnariresreturned>0) && (questionnairenumber>=0))){
			
			//Check to see if district already exists then add
			String searchResults = Searchfile.search(currentToken[1]);
			if(searchResults=="NONE"){
				AddData.serverAdd(currentToken);
				feed = "Data Has been Added to File: QuestionnaireInfo.txt";
			}	
			else
				feed = "Sorry District Information Already Exists." ;
					
		}
		else{
			if(questionnairenumber == -4040 && questionnnariresreturned ==5248 ||((questionnnariresreturned<0) || (questionnairenumber<0)))
				feed = "Only Positive Integers allowed";
			else	
				feed = "Cannot Return More Questionnares than were taken!";
		}
		System.out.println(feed);
	}
	else if(numberTokens < 5){
		feed =" Data given is insufficient ";
		System.out.println(feedserver + feed);
		}
	else if(numberTokens > 5){
		feed =" Error!! input is more than required |*_*| ";	
		System.out.println(feedserver + feed);
		}
	else
		feed="invalid input";
}
	
else if (command.equalsIgnoreCase("get")){
	answer = "get";
	System.out.println(feedserver + " selected " + answer);
	if (numberTokens == 2){ //if statements to check if number of inputs is accurate
		feed =" selected ";
		String searchResults = Searchfile.search(currentToken[1]);

		if(searchResults=="NONE"){ //check if there is no result
			feed = "\n No Results found \n";
		}
		else{
			feed = "Results \n" + searchResults;
		}
		System.out.println(feedserver + feed);
		}
	else if(numberTokens < 2){
		feed =" No district name given ";
		System.out.println(feedserver + feed);
		}
	else if(numberTokens > 2){
		feed =" More than a district given ";
		System.out.println(feedserver + feed);
		}
	else
		feed="Invalid input given ";
}
else if (command.equalsIgnoreCase("exists")){
	answer = "exists";
	System.out.println(feedserver + " selected " + answer);
	if (numberTokens == 2){
		feed =" selected "+ answer;
		
	String searchResults = Searchfile.search(currentToken[1]);
	if(searchResults=="NONE"){
		feed = "No Results Found." ;
	}	
	else
		feed = "Yes, Results Found." ;
	System.out.println(feed);
		}
	else if(numberTokens < 2){
		feed =" No district name given ";
		System.out.println( feed);
		}
	else if(numberTokens > 2){
		feed =" More than a district given ";
		System.out.println( feedclient + feed);
		}
	else
	feed="Invalid input given ";
}
else{
	answer = " an Invalid Command ";
	feed =" entered "+ answer;
	System.out.println(feedserver + feed);
}

			//Give Client Feedback on what has happened
			DataOutputStream feedback = new DataOutputStream(server.getOutputStream());
			feedback.writeUTF(feed);
			
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for trying to "+ answer /*+ server.getLocalSocketAddress()*/ + "\nGoodbye!");
			server.close();
			//end of.
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
		
      }
   }
 
public static void main(String [] args){ 
    int port = Integer.parseInt(args[0]);
    try
    {
        Thread t = new InfoServer(port);
        t.start();
    }
	catch(IOException e)
    {
        e.printStackTrace();
    }
   }
}