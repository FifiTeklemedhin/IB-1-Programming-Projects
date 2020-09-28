// Example 15.8
// File: DayTimeServer.java
// Provide day and time

import java.net.*;
import java.io.*;
import java.util.*;

public class DayTimeServer{
   public static void main(String[] args){
      try{
         System.out.println("Starting Server");
         ServerSocket socketOnWhichToListenForClients = new ServerSocket (5555);
         while(true){
            Socket socketBackToClient = socketOnWhichToListenForClients.accept();
            PrintStream clientOutput = 
                new PrintStream(socketBackToClient.getOutputStream(), true);
            clientOutput.println(new Date());         
            socketBackToClient.close();
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}

