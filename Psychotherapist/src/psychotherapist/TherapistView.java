/**
 *
 * @author fifiteklemedhin
 */
package psychotherapist;

import APClasses.APConsole;
import java.util.Scanner;

public class TherapistView 
{
    APConsole console = new APConsole("Psychotherapist");

    private Therapist therapist = new Therapist();
    
    public TherapistView() 
    {
        console.println("Good day. What is your problem?");
        
        while(true)
        {
            console.print("\nEnter your response here or Q to quit: ");
            String patientString = console.nextLine();
            
            if(patientString.equalsIgnoreCase("Q"))
                break;
            String therapistString = therapist.reply(patientString);
            console.println("\n" + therapistString);
        }
    }
}
