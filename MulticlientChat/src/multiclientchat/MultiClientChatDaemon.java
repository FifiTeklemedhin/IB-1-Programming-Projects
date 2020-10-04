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
    public static Transcript transcript;
    // Instantiate the daemon and start it.
    public MultiClientChatDaemon(){
        start();
    }
    
    public void run()
    {
        transcript = new Transcript();
      try{
         ChatRoomServer.console.println("Server daemon starting");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         
         // Listen indefinitely for client requests
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            ChatRoomServer.console.println("new handler");
            // Spawn a handler
            new MultiClientChatHandler (socketBackToClient, transcript);
         }
      }catch (Exception e){
         ChatRoomServer.console.println ("Error:\n" + e.toString());
      }
      ChatRoomServer.console.println ("Server daemon ending");
   }
}