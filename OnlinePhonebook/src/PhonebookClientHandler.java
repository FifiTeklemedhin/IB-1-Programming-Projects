import java.io.*;
import java.net.Socket;
import java.util.*;
import APClasses.APConsole;
 
public class PhonebookClientHandler extends Thread{

   private Socket socketBackToClient;
   public Phonebook phonebook;
   private PhonebookClient client;
   
   private InputStream is;
   private BufferedReader br;
   private PrintStream os;
   
   private APConsole console = new APConsole("Chatroom Handler");
   
    // Instantiate and start the handler
   public PhonebookClientHandler(Socket socket, Phonebook phonebook){
      this.socketBackToClient = socket;
      this.client = new PhonebookClient();
      this.phonebook = phonebook;
      start();
   }
      
   public void run (){
      console.println("MultiClientChatHandler starting");
      String greeting = "To use this digial phonebook, submit either \"add\" or \"find\"\n"
                          + "To terminate this program, submit \"end\"\n";
      try{

      	 // Establish input and output streams with the client    	
         is = socketBackToClient.getInputStream();
         br = new BufferedReader(new InputStreamReader(is));
         os = new PrintStream(socketBackToClient.getOutputStream(), true);

         // Enter the conversational loop with the client
         while (true)
         {
            // Read a line of input from the client and test for the exit condition
            //console.println(greeting);
            String clientInput = br.readLine();
            console.println("br: \"" + clientInput + "\"");
            
            if (clientInput.equalsIgnoreCase("end")) 
               break;
            else if (clientInput.equalsIgnoreCase("add"))
               addContact();
            else if (clientInput.equalsIgnoreCase("find"))
                getContact();
            else
                os.println(clientInput + ": That is not one of the available commands. Try again");

         }
        
         // Close the socket
         socketBackToClient.close();
        
      }catch (Exception e){
         os.println ("ClientHandler Error:\n" + e.toString());
      }
      os.println("ClientHandler ending");
   }
   public void addContact()
   {
       
       try
       {
           os.println("Give me the name: ");
           String name = br.readLine();
           
           os.println("Give me the contact's number: ");
           String number = br.readLine();
           
           os.println(this.phonebook.add(name, number));
           console.println("\t>br: " + name);
           console.println("\t>br: " + number);
       }
       catch(IOException e)
       {
           os.println("Error when trying to read inputs: addContact(). try again");
       }

   }
   
   public void getContact()
   {
       try
       {
           os.println("Give me the name: ");
           String name = br.readLine();
           
           os.println(this.phonebook.get(name));
           console.println("\t>br: " + name);
       }
       catch(IOException e)
       {
           os.println("Error when trying to read input: getContact(). try again");
       }
   }

}
