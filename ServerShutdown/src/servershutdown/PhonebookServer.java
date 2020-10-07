package servershutdown;

import APClasses.APConsole;
import java.net.*;
import java.io.*;
import java.util.*;

public class PhonebookServer{
   public static APConsole console = new APConsole("Chatroom Server and Daemon");
   public static void main(String[] args){
      Scanner reader = new Scanner(System.in);
      try{
         console.println("Starting Server");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
      	    // Establish input and output streams with the client    	
            InputStream is = socketBackToClient.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);
        
       	    // Welcome the client
       	    os.println("Welcome to the phonebook");
            while(!br.readLine().equalsIgnoreCase("shutdown"))
            {
               // Read and echo a line of input from the client.
               String clientInput = br.readLine();
               console.println (clientInput);
               // Read a line of input from the server's user and send it to the client
            }
            console.println("\nShutting down threads.....");
            String threadInput = br.readLine();
            while(threadInput.equalsIgnoreCase("Thread shutdown complete"))
                threadInput = br.readLine();
            console.println("Thread shutdown complete");
            os.println("Server shut down");
            console.println("Server shut down");
            socketBackToClient.close();
         }
      }catch (Exception e){
         console.println ("Error:\n" + e.toString());
      }
   }
}