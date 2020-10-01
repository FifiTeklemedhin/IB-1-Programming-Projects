/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;


import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TherapyServerDaemon extends Thread{
    
    // Instantiate the daemon and start it.
    public TherapyServerDaemon(){
        start();
    }
    
    public void run(){
      try{
         TherapyInterface.console.println("Server daemon starting");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         
         // Listen indefinitely for client requests
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            
            // Spawn a handler
            TherapyClient.console.print("Username (not case-sensitive): ");
            String username = TherapyClient.console.nextLine();
            TherapyClient.console.print("\n*********************************\n");
            new TherapyClientHandler (socketBackToClient, new Therapist(username));
         }
      }catch (Exception e){
         TherapyClient.console.println ("Daemon Error:\n" + e.toString());
      }
      TherapyClient.console.println ("Server daemon ending");
   }
}