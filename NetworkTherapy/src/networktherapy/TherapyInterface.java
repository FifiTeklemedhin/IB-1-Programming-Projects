/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;

/**
 *
 * @author fifiteklemedhin
 */
// Example 15.11
// File: TherapyInterface.java
// Start the therapy server daemon and stop

public class TherapyInterface{
   public static void main(String[] args){
     System.out.println ("Main program starting the TherapyServerDaemon");
     
     // Spawn the server daemon
     new TherapyServerDaemon();
     
     System.out.println ("Main program ending");
   }
}
