/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;

// Example 15.11
// File: TherapyClientHandler.java
// Conduct conversation between the therapist the client

import java.net.*;
import java.io.*;
import java.util.*;
 
public class TherapyClientHandler extends Thread{

   private Socket socketBackToClient;
   private Therapist therapist;
   public static String username = "";

   // Instantiate and start the handler
   public TherapyClientHandler(Socket socket, Therapist therapist){
      socketBackToClient = socket;
      this.therapist = therapist;
      start();
   }

   public void run (){
      TherapyInterface.console.println("TherapyClientHandler starting");
      try{

      	 // Establish input and output streams with the client    	
         InputStream is = socketBackToClient.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);
         
         // Send the therapist's greeting to the client
         os.println(therapist.greeting());

         // Enter the conversational loop with the client
         while (true){
            // Read a line of input from the client and test for the exit condition
            String clientInput = br.readLine();
            if (clientInput.equalsIgnoreCase("bye")) 
                break;
            // Send the therapist's reply to the client
            int randomInterval = (int) (Math.random() * (7 - 3 + 1));
            int iterate = 0;
            if(iterate >= randomInterval)
            {
                if(therapist.canReferBack())
                {   
                   iterate = 0;
                   randomInterval = (int) (Math.random() * (7 - 3 + 1));
                   String referringBack = therapist.referBack();
                   
                   therapist.updateTranscript(clientInput, referringBack);
                   os.println(referringBack);
                   continue;
                }
                randomInterval = (int) (Math.random() * (5 - 2 + 1));
                iterate += 1;
            }
            os.println(therapist.reply(clientInput));
         }
        
        
        
      }catch (Exception e){
         TherapyInterface.console.println ("Client Handler Error:\n" + e.toString());
      }
      TherapyInterface.console.println("TherapyClientHandler ending");
   }
}

