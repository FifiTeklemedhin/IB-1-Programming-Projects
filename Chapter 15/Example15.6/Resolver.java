// Example 15.6
// File: Resolver.java
// Get the IP address of any machine by name
 
import java.net.*;
import java.util.Scanner;
 
public class Resolver{
  
   public static void main(String args[]){
      System.out.print("Name: ");
      Scanner reader = new Scanner(System.in);
      try{
         String name = reader.next();
         InetAddress ipAddress = InetAddress.getByName(name);
         System.out.println("IP address:\n" + ipAddress);
      }
      catch (Exception e){
         System.out.println("Unknown host:\n" + e.toString());
      }
   }
} 

