/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

/**
 *
 * @author fifiteklemedhin
 */
import java.net.*;
import java.io.*;

public class MultiClientChatDaemon extends Thread{
    
    // Instantiate the daemon and start it.
    public MultiClientChatDaemon(){
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
            new TherapyClientHandler (socketBackToClient, new Therapist());
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println ("Server daemon ending");
   }
}


