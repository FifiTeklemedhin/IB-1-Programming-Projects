/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliclientexample;

/**
 *
 * @author fifiteklemedhin
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatRoomServer{
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
   public static void main(String[] args)
   {
      
      Scanner reader = new Scanner(System.in);
      try
      {
        System.out.println("Starting Server");
        ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
        while(true)
        {
          Socket socketBackToClient = socketOnWhichToListenForClients.accept();
          // Establish input and output streams with the client    	
          InputStream is = socketBackToClient.getInputStream();
          BufferedReader br = new BufferedReader(new InputStreamReader(is));
          PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);
          
          // Welcome the client
          os.println("Welcome to the chatroom. Type a message or 'bye' to quit.");
          while (true)
          {
             // Read and echo a line of input from the client.
             String clientInput = br.readLine();
             System.out.println (clientInput);
             if (clientInput.equalsIgnoreCase("bye")) 
                break;
             // Read a line of input from the server's user and send it to the client
             System.out.print("> ");
             String userInput = reader.nextLine();
             os.println(userInput);
             if (userInput.equalsIgnoreCase("bye"))
                break;
           }
          
           socketBackToClient.close();
        }
      }
      catch (Exception e)
      {
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
