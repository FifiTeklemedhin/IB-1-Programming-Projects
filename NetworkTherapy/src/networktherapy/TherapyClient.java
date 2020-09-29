/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;

/**
 *
 * @author fifiteklemedhin
 */
// Example 15.11
// File: TherapyClient.java
// Client for therapy application

import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class TherapyClient{
   public TherapyClient()
   {
       run();
   }
   public static void run ()
   {
      // Ask the user for the IP address of the chat server.
      Scanner reader = new Scanner(System.in);
      System.out.print ("Host name or IP number: ");
      String hostId = reader.nextLine();
      
      try{
      	 // Connect to port 5555 on the host using a socket.
         Socket socket = new Socket (hostId, 5555);
         
         // Establish input and output streams on the socket
         InputStream is = socket.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         PrintStream os = 
                new PrintStream(socket.getOutputStream(), true);
                                      
         // Read and display a line from the buffered input stream, 
         // which is assumed to be the server's greeting.
         String greeting = br.readLine(); 
         System.out.println (greeting);
         
         // Run a conversation loop until either party quits
         while (true){
            // Read and send the user's input to the server
            System.out.print("> ");
            String userInput = reader.nextLine();
            os.println(userInput);
            if (userInput.equalsIgnoreCase("bye"))
               break;
            // Read the input from the server and display it on the user's terminal
            String serverInput = br.readLine();
            System.out.println(serverInput);	
            if (serverInput.equalsIgnoreCase("bye"))
               break; 
         }          
         // Close the socket
         socket.close();
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
