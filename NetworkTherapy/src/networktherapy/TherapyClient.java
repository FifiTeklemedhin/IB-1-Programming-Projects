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
import APClasses.APConsole;

public class TherapyClient{
   public static APConsole console = new APConsole("Therapy Client");
   public TherapyClient()
   {
       run();
   }
   public static void run ()
   {
      
      // Ask the user for the IP address of the chat server.
      console.print ("Host name or IP number: ");
      String hostId = console.nextLine();
      
      try{
          //create input strem, buffered reader, output stream
          //to get input from client, in something dealing with server, use br from server
          //one for client and one for server, no static console
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
         console.println (greeting);
         
         // Run a conversation loop until either party quits
         while (true){
            // Read and send the user's input to the server
            console.print("> ");
            String userInput =  console.nextLine();
            os.println(userInput);
            if (userInput.equalsIgnoreCase("bye"))
               break;
            // Read the input from the server and display it on the user's terminal
            String serverInput = br.readLine();
            console.println(serverInput);	
            if (serverInput.equalsIgnoreCase("bye"))
               break; 
         }          
         // Close the socket
         socket.close();
      }catch (Exception e){
         console.println ("Error:\n" + e.toString());
      }
   }
}
