// Example 15.7
// File: DayTimeServer.java
// Provide day and time

import java.net.*;
import java.io.*;
import java.util.*;

public class DayTimeServer{

   public static void main(String[] args){
      try{
      	 // Inform the user that the sever is starting
         System.out.println("Starting Server");
         
         // Set up a listening socket on port 5555. 
         ServerSocket socketOnWhichToListenForClients = new ServerSocket(5555);
                         
         // Wait for a client to make a connection to port 5555. 
         // When the request arrives, establish a socket back to the client.
         Socket socketBackToClient = socketOnWhichToListenForClients.accept();
         
         // Establish an output stream on the socket back to the client.
         // Write the day and date to this stream.
         PrintStream clientOutput = 
             new PrintStream(socketBackToClient.getOutputStream(), true);
         clientOutput.println(new Date());
         
         // Close the socket back to the client.
         socketBackToClient.close();
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
