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
import APClasses.APConsole;
import java.net.*;
import java.io.*;
import java.util.*;
 
public class MultiClientChatHandler extends Thread{

   private Socket socketBackToClient;
   private ChatRoomClient ChatRoomClient;
   public Transcript transcript;
   public String name = "NO_NAME_USER";
   private APConsole console = new APConsole("Chatroom Handler");
   // Instantiate and start the handler
   public MultiClientChatHandler(Socket socket, Transcript transcript){
      this.socketBackToClient = socket;
      this.ChatRoomClient = new ChatRoomClient();
      this.transcript = transcript;
      start();
   }
      
   public void run (){
      console.println("MultiClientChatHandler starting");
      try{

      	 // Establish input and output streams with the client    	
         InputStream is = socketBackToClient.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);

         // Send the ChatRoomClient's greeting to the client
         os.println("Hello!");

         // Enter the conversational loop with the client
         while (true){
            // Read a line of input from the client and test for the exit condition
            String clientInput = br.readLine();
            console.println("br: " + clientInput);
            if (clientInput.contains("Name: "))
            {
                this.name = clientInput.replace("Name: ", "");
                continue;
            }
              
            if (clientInput.equalsIgnoreCase("bye")) 
               break;
   
            this.transcript.add(this.name + ": " + clientInput);
            
            // Send the ChatRoomClient's reply to the client
            os.println(this.transcript);
         }
        
         // Close the socket
         socketBackToClient.close();
        
      }catch (Exception e){
         console.println ("Error:\n" + e.toString());
      }
      console.println("MultiClientChatHandler ending");
   }
}