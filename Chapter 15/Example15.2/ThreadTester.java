// Example 15.2

// File: ThreadTester.java

import java.util.Scanner;

public class ThreadTester{

   public static void main(String args[]){
      // Ask the user for the number of threads and
      // the maximum sleep time.
      Scanner reader = new Scanner(System.in);
      System.out.print("Enter the number of threads: ");
      int numThreads = reader.nextInt();
      System.out.print("Enter the maximum sleep time: ");
      int sleepMax = reader.nextInt();
      
      // Create an array of thread objects.
      SleepyThread[] threads = new SleepyThread[numThreads];
      for (int count = 1; count <= numThreads; count++)
         threads[count - 1] = new SleepyThread(count, sleepMax);
         
      // Tell each thread to start
      for (SleepyThread thread: threads)
         thread.start();
   }
} 
 