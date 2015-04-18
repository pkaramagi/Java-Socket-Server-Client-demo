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

// Import io so we can use file objects
import java.io.*;

public class Searchfile {
//method to searching and returning client request information	
	public static String search(String args) {
		String result= "NONE";
		args = String.format("%s	",args);
		try {
			// Open the file with the data as a buffered reader
			BufferedReader bf = new BufferedReader(new FileReader("QuestionnaireInfo.txt"));
			
			// Start a line count and declare a string to hold our current line.
			int linecount = 0;
    			String line;
				

			// Let the user know what we are searching for
			System.out.println("Searching for " + args + " in file...");

			// Loop through each line, putting the line into our line variable.
			while (( line = bf.readLine()) != null)
			{
    				// Increment the count and find the index of the word
    				linecount++;
    				int indexfound = line.indexOf(args);

    				// If greater than -1, means we found the word
    				if (indexfound > -1 && indexfound == 0) {
					result = String.format("%5s",line);
    				  
    				}
			}

			// Close the file after done searching
			bf.close();
		}
		catch (IOException e) { //catch IOExecption
			System.out.println("IO Error Occurred: " + e.toString());
		}
	return result;
	}
}