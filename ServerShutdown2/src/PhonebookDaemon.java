/**
 *
 * @author fifiteklemedhin
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class PhonebookDaemon extends Thread{
    public static Phonebook phonebook;
    public ArrayList<PhonebookClientHandler> handlers = new ArrayList<PhonebookClientHandler>();
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
         
         Socket socketBackToClient = socketOnWhichToListenForClients.accept();
         InputStream is = socketBackToClient.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         PrintStream os = new PrintStream(socketBackToClient.getOutputStream(), true);
         
        // Listen indefinitely for client requests
         String clientTranscript = br.readLine();
         while(!clientTranscript.equalsIgnoreCase("shutdown")){
            
            PhonebookServer.console.println("new handler");
            // Spawn a handler
            handlers.add(new PhonebookClientHandler (socketBackToClient, this.phonebook));
         }
         for(Thread thread:handlers)
             thread.interrupt();
         os.println("Handlers/threads shutdown");
      }catch (Exception e){
         PhonebookServer.console.println ("Error:\n" + e.toString());
      }
      PhonebookServer.console.println ("Server daemon ending");
   }
}