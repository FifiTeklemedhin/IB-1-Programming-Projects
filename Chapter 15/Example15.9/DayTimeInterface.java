// Example 15.9
// File: DayTimeInterface.java
// Start the day/time server daemon and stop

public class DayTimeInterface{
   public static void main(String[] args){
     System.out.println ("Main program starting the DayTimeServerDaemon");
     
     // Spawn the server daemon
     new DayTimeServerDaemon();
     
     System.out.println ("Main program ending");
   }
}

