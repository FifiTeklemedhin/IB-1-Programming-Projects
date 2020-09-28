// Case Study
// File: ChatRoomClient.java

import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class ChatRoomClient{  
     
   public static void main (String[] args){
      // Ask the user for the IP address of the chat room server
      // and a user name.
      Scanner reader = new Scanner(System.in);
      System.out.print("Host name or IP number: ");
      String hostId = reader.nextLine();
      System.out.print("your name: ");
      String userName = reader.nextLine();
      
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
         os.println(userName);
                                      
         // Read a line from the buffered input stream, which
         // is assumed to be the server's greeting.
         String greeting = br.readLine();
         
         // Display the server's greeting 
         System.out.println(greeting);
         
         // Repeatedly send user input to the handler.
         // Read the transcript from the handler.
         // Display the current transcript.
         while (true) {
            System.out.print("> ");
            String userInput = reader.nextLine();
            os.println(userInput);
            if (userInput.equals("bye"))
               break;
         	
            // Read the multiline transcript from the handler and 
            // display it on the client's console
            String transcript = br.readLine();
            while (! transcript.equals("")){
               System.out.println(transcript);
               transcript = br.readLine();
            }
         }
         // Close the socket
         socket.close();    
      }catch (Exception e){
         System.out.println ("Error in client:\n" + e.toString());
      }
   }
}
