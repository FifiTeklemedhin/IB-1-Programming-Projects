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
         System.out.println("Server daemon starting");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         
         // Listen indefinitely for client requests
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            
            // Spawn a handler
            Scanner reader = new Scanner(System.in);
            System.out.print("Username (not case-sensitive): ");
            String username = reader.nextLine();
            new TherapyClientHandler (socketBackToClient, new Therapist(username));
         }
      }catch (Exception e){
         System.out.println ("Daemon Error:\n" + e.toString());
      }
      System.out.println ("Server daemon ending");
   }
}