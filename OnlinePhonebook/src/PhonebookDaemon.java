/**
 *
 * @author fifiteklemedhin
 */
import java.net.*;
import java.io.*;

public class PhonebookDaemon extends Thread{
    public static Phonebook phonebook;
    // Instantiate the daemon and start it.
    public PhonebookDaemon(){
        start();
    }
    
    public void run()
    {
        this.phonebook = new Phonebook();
      try{
         PhonebookServer.console.println("Server daemon starting");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         
         // Listen indefinitely for client requests
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            PhonebookServer.console.println("new handler");
            // Spawn a handler
            new PhonebookClientHandler (socketBackToClient, this.phonebook);
         }
      }catch (Exception e){
         PhonebookServer.console.println ("Error:\n" + e.toString());
      }
      PhonebookServer.console.println ("Server daemon ending");
   }
}