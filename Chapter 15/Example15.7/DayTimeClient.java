// Example 15.7
// File: DayTimeClient.java
// Request day and time

import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class DayTimeClient{
     
   public static void main (String[] args){
      // Ask the user for the IP address of the day/time server.
      Scanner reader = new Scanner(System.in);
      System.out.print ("Host name or IP number: ");
      String hostId = reader.nextLine();
      
      try{
      	 // Connect to port 5555 on the host using a socket.
         Socket socket = new Socket (hostId, 5555);
         
         // Establish a buffered input stream on the socket
         InputStream is = socket.getInputStream();
         BufferedReader serverInput = new BufferedReader(new InputStreamReader(is));
                                      
         // Read a line from the buffered input stream, which
         // is assumed to be the day and time.
         String time = serverInput.readLine();
         
         // Display the day and time 
         System.out.println ("The time is:\n" + time);
         
         // Close the socket
         socket.close();
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
