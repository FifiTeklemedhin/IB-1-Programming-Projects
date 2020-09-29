/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;


import java.net.*;
import java.io.*;

public class TherapyServerDaemon extends Thread{
    
    // Instantiate the daemon and start it.
    public TherapyServerDaemon(){
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
            new TherapyClientHandler (socketBackToClient, new Therapist("Fifi"));
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println ("Server daemon ending");
   }
}