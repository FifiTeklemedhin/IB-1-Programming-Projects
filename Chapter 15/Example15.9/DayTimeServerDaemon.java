// Example 15.9
// File: DayTimeSeverDaemon.java
// Listen for client requests and spawn handlers

import java.net.*;
import java.io.*;

public class DayTimeServerDaemon extends Thread{
    
    // Instantiate the daemon and start it.
    public DayTimeServerDaemon(){
        start();
    }
    
    public void run(){
      try{
         System.out.println("Server daemon starting");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         
         // Listen indefinitely for client requests
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            
            // Spawn a handler
            new DayTimeClientHandler (socketBackToClient);
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println ("Server daemon ending");
   }
}


