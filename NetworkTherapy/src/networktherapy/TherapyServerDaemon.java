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
// File: TherapyServerDaemon.java
// Listen for client requests and spawn handlers

import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class TherapyServerDaemon extends Thread{
    
    public static Map<String, Therapist> allClients = new HashMap<String, Therapist>();
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
            if(allClients.containsKey(TherapyClient.username))
            {
                System.out.println("reinitializing therapist");
                new TherapyClientHandler (socketBackToClient, allClients.get(TherapyClient.username));
            }
                
            else
            {
                System.out.println("initializing new therapist");
                Therapist newTherapist = new Therapist(TherapyClient.username);
                allClients.put(TherapyClient.username, newTherapist);
                new TherapyClientHandler (socketBackToClient, newTherapist);
            }
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println ("Server daemon ending");
   }
}