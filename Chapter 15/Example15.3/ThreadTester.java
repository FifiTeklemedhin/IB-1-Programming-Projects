// Example 15.3

// File: ThreadTester.java

import java.util.Scanner;

public class ThreadTester{
       
   public static final int SLEEP_INTERVAL = 2000;
      
   public static void main (String args[]){
    /* Get the number of accesses from the user, 
       create a shared cell, and create and start up        
       a producer and a consumer. */ 
      Scanner reader = new Scanner(System.in);
      System.out.print("Enter the number of accesses: ");    // Get # accesses
      int accessCount = reader.nextInt();
      SharedCell cell = new SharedCell();                    // Create shared cell
      Producer producer = new Producer(accessCount, cell);   // Create producer
      Consumer consumer = new Consumer(accessCount, cell);   // Create consumer
      System.out.println("Starting threads");
      producer.start();
      consumer.start();
   }
 
}