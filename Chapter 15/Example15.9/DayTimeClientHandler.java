// Example 15.9
// File: DayTimeClientHandler.java
// Send the day and time to the client

import java.net.*;
import java.io.*;
import java.util.*;
 
public class DayTimeClientHandler extends Thread{

   private Socket socketBackToClient;

   // Instantiate and start the handler
   public DayTimeClientHandler(Socket socket){
      socketBackToClient = socket;
      start();
   }
      
   public void run (){
      System.out.println("DayTimeClientHandler starting");
      try{
        
        // Send the day and time to the client
        PrintStream clientOutput = 
            new PrintStream(socketBackToClient.getOutputStream(), true);
        clientOutput.println(new Date());
        
        // Close the socket
        socketBackToClient.close();
        
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println("DayTimeClientHandler ending");
   }
}
