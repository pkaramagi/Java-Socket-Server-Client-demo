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
import java.io.*;
import java.text.*;
public class AddData{
	//method to add client input to file
   public static void serverAdd(String details[] ){ 
	try{ //create PrintWriter object to add client input to file
         PrintWriter inFileStream = new PrintWriter(new FileWriter("QuestionnaireInfo.txt",true));
		 // add client input to file using for loop
		 for(int i = 1; i<details.length;i++){
		 inFileStream.print(details[i] + "\t");
		   }
			inFileStream.println();
			inFileStream.close(); //close inFileStream
			

}catch(IOException e)//catch IOException
	    {
		System.out.println(e.getMessage());
		System.out.println("Error writing Data to file QuestionnaireInfo.txt.txt");
		System.exit(-1);
		}}} 