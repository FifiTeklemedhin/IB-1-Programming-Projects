/**
 *
 * @author fifiteklemedhin
 */
package psychotherapist;

import APClasses.APConsole;

public class TherapistView 
{
    APConsole console = new APConsole("Psychotherapist");
    private Therapist therapist = new Therapist();
    
    public TherapistView() 
    {
        console.println("Good day. What is your problem?");
        
        int randomInterval = (int) (Math.random() * (7 - 3 + 1));
        int iterate = 0;
        while(true)
        {
            console.print("\nEnter your response here or Q to quit: ");
            String patientString = console.nextLine();
            if(patientString.equalsIgnoreCase("Q"))
                break;
            
            if(iterate >= randomInterval)
            {
                if(therapist.canReferBack())
                {   
                   iterate = 0;
                   randomInterval = (int) (Math.random() * (7 - 3 + 1));
                   console.println("\nRefering back: " + therapist.referBack());
                   continue;
                }
                randomInterval = (int) (Math.random() * (5 - 2 + 1));
            }
            String therapistString = therapist.reply(patientString);
            console.println("\n" + therapistString);
            
            iterate += 1;
        }
    }
}
