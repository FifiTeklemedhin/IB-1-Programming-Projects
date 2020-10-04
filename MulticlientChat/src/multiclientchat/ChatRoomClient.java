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
import java.util.Scanner;
 
public class ChatRoomClient{  
   
   public static void main (String[] args)
   {
       APConsole console = new APConsole("Chatroom Client");
      // Ask the user for the IP address of the chat room server
      // and a user name.
      Scanner reader = new Scanner(System.in);
      console.print("Host name or IP number: ");
      String hostId = console.nextLine();
      console.print("your name: ");
      String userName = console.nextLine();
      
      try{
      	 // Connect to port 5555 on the host using a socket.
         Socket socket = new Socket (hostId, 5555);
         
         // Establish input and output streams on the socket
         InputStream is = socket.getInputStream();
         BufferedReader br = 
            new BufferedReader(new InputStreamReader(is));
         PrintStream os = 
            new PrintStream(socket.getOutputStream(), true);

         // Send this user's name to the server
         os.println("Name: " + userName);
                                      
         // Read a line from the buffered input stream, which
         // is assumed to be the server's greeting.
         String greeting = br.readLine();
         
         // Display the server's greeting 
         console.println(greeting);
         
         // Repeatedly send user input to the handler.
         // Read the transcript from the handler.
         // Display the current transcript.
         while (true) {
            console.print("> ");
            String userInput = console.nextLine();
            os.println(userInput);
            if (userInput.equals("bye"))
               break;
         	
            // Read the multiline transcript from the handler and 
            // display it on the client's console
            String transcript = br.readLine();
            while (! transcript.equals("")){
               console.println(transcript);
               transcript = br.readLine();
            }
         }
         // Close the socket
         socket.close();    
      }catch (Exception e){
         console.println ("Error in client:\n" + e.toString());
      }
   }
}
