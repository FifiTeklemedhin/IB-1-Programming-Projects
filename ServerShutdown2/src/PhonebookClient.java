/**
 *
 * @author fifiteklemedhin
 */
import APClasses.APConsole;
import java.net.*;
import java.io.*;
import java.util.Scanner;
 
public class PhonebookClient{  
   
   public static void main (String[] args)
   {
       APConsole console = new APConsole("Phonebook Client");
      // Ask the user for the IP address of the chat room server
      // and a user name.
      Scanner reader = new Scanner(System.in);
      console.print("Host name or IP number: ");
      String hostId = console.nextLine();
      try{
      	 // Connect to port 5555 on the host using a socket.
         Socket socket = new Socket (hostId, 5555);
         
         // Establish input and output streams on the socket
         InputStream is = socket.getInputStream();
         BufferedReader br = 
            new BufferedReader(new InputStreamReader(is));
         PrintStream os = 
            new PrintStream(socket.getOutputStream(), true);
                             
         // Read a line from the buffered input stream, which
         // is assumed to be the server's greeting.
         String greeting = "To use this digial phonebook, submit either \"add\" or \"find\"\n"
                          + "To terminate this program, submit \"end\"\n";
         
         // Display the server's greeting
         
         // Repeatedly send user input to the handler.
         
         while (true) {
            console.println("\n" + greeting);
            console.print("> ");
            String userInput = console.nextLine();
            os.println(userInput);
            if (userInput.equalsIgnoreCase("end"))
            {
                console.println("Shutting client down...");
                break;
            }
            else if(userInput.equalsIgnoreCase("shutdown"))
            {
                String transcript = br.readLine();
                while(!transcript.equalsIgnoreCase("Thread shutdown complete"))
                {
                    console.println(transcript);
                    transcript = br.readLine();
                }
                console.println("Server shut down.");
                console.println("Program terminated");
            }
            // Read the multiline transcript from the handler and 
            // display it on the client's console
          
            while (true)
            {
               String transcript = br.readLine();
               console.println(transcript);
               
               // prompts the user if the client handler is asking for an input
               while(transcript.contains("Give me"))
               {
                   //console.println("RUNNING INNER LOOP");
                   console.print("> ");
                   userInput = console.nextLine();
                   os.println(userInput);
                   
                   transcript = br.readLine();
                   console.println(transcript);

               }
  
              break;
            }
         }
         // Close the socket
         socket.close();    
      }catch (Exception e){
         console.println ("Error in client:\n" + e.toString());
      }
   }
}
