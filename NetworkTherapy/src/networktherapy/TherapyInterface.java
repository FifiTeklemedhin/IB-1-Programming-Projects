/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networktherapy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import APClasses.APConsole;
/**
 *
 * @author fifiteklemedhin
 */
// Example 15.11
// File: TherapyInterface.java
// Start the therapy server daemon and stop

public class TherapyInterface
{
   public static APConsole console = new APConsole("Therapy Interface");
   public static void main(String[] args)
   {
     console.println ("Main program starting the TherapyServerDaemon");
     JFrame frame = new JFrame();
     JOptionPane.showMessageDialog(frame, "Chat virually with a therapist.\n"
                                           + "Input your IP address/port number\n"
                                           + "and a new/previous username. Type \"bye\"\n"
                                           + " to terminate the program.");
     // Spawn the server daemon
     new TherapyServerDaemon();
     new TherapyClient();
     
     console.println ("Main program ending");
   }
}
