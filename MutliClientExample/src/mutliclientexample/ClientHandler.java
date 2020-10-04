/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutliclientexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author fifiteklemedhin
 */
public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    
    public ClientHandler(Socket clientSocket) throws IOException // still need server to actually accept request to make connection
    {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    
    
    @Override
    public void run() 
    {
        Scanner reader = new Scanner(System.in);
        out.println("Welcome to the chatroom. Type a message or 'bye' to quit.");
        try
        {
            while (true)
          {
             // Read and echo a line of input from the client.
             String clientInput = in.readLine();
             System.out.println (clientInput);
             if (clientInput.equalsIgnoreCase("bye")) 
                break;
             // Read a line of input from the server's user and send it to the client
             System.out.print("> ");
             String userInput = reader.nextLine();
             out.println(userInput);
             if (userInput.equalsIgnoreCase("bye"))
                break;
          }
        }
        catch(IOException e)
        {
            System.err.println("could not read client input");
        }
    }
    
}
