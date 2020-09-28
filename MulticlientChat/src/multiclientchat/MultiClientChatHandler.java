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
import java.util.*;
 
public class MultiClientChatHandler extends Thread{

   private Socket socketBackToClient;
   private ChatRoomClient ChatRoomClient;

   // Instantiate and start the handler
   public MultiClientChatHandler(Socket socket, ChatRoomClient ChatRoomClient){
      socketBackToClient = socket;
      this.ChatRoomClient = ChatRoomClient;
      start();
   }
      
   public void run (){
      System.out.println("MultiClientChatHandler starting");
      try{

      	 // Establish input and output streams with the client    	
         InputStream is = socketBackToClient.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);

         // Send the ChatRoomClient's greeting to the client
         os.println(ChatRoomClient.transcript());

         // Enter the conversational loop with the client
         while (true){
            // Read a line of input from the client and test for the exit condition
            String clientInput = br.readLine();
            if (clientInput.equalsIgnoreCase("bye")) 
               break;
            // Send the ChatRoomClient's reply to the client
            os.println(ChatRoomClient.reply(clientInput));
         }
        
         // Close the socket
         socketBackToClient.close();
        
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
      System.out.println("MultiClientChatHandler ending");
   }
}
