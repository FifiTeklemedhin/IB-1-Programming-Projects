/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fifiteklemedhin
 */
public class Tester 
{
    // current issue: says files not found
    // current issue: sometimes file choosers just don't open
    
    // strings with correct outputs
     static String letItGoOut = "the snow glows white on the mountain tonight\n" 
                        +"not a footprint to be seen\n" 
                        +"a kingdom of isolation, and it looks like i'm the queen\n" 
                        +"\n" 
                        +"the wind is howling like the swirling storm inside \n" 
                        +"couldn't keep it in\n" 
                        +"heaven knows i tried....".toUpperCase();

    static String iOwedOut = "I owed $2.29, Jo owed $5.27, and Lu owed $10.31 and paid $6.98., She ordered 150# of #1\n" 
                   +"potatoes (which retail for 50# @ $10.), The parents believe 4 + 4, 2 - 2, and 2 - 4 are\n" 
                   +"easy to calculate., Catalog #327 lists 4 @ $1.07, 31 @ $3.30, 64 @ $2.10, and 19 @ $2.60.,\n" 
                   +"I know that 2 - 2 = 0., I need 3# of #2 nails, 2# of #8 bolts, and 79# of #5 clips., On 1/25 \n" 
                   +"the Hartly Company* stock decreased 14 1/8 points!".toUpperCase();

    static String randomActsOut = "Many Americans today are blind to what life is like in other countries. After watching Hollywood movies where Chinese are only portrayed as either Kung Fu masters or super geniuses, it is no wonder so few Americans know about the reality of China today. But novels like Gold Boy, Emerald Girl, written by Yiyun Li, help to show some of the more somber realities of contemporary China. When reading this novel, it seems as if Yiyun Li is portraying China as being filled with loneliness and despair, bringing about an overall tone of complacency for the novella “Kindness.” But contrary to the tone of this story, Li unveils hope hidden within the encounters of others and their positive and negative influences.\n" 
                           +"Li writes this novella in the form of a narrative, therefore the reader is never left wondering what Moyan is thinking. This is beneficial in determining the complacency in Moyan’s life. For the duration of this novella, Moyan is left with the feeling that she was meant to be lonely in life, this being shown with her thoughts about her family, “[W]e were the loneliest family in the world because we were meant to be that way” (Li 53). Even from the beginning Moyan believed she was meant to be alone and away from people as shown from the quote above, and this belief was influenced by her mentor, Professor Shan.\n" 
                           +"Moyan had met Professor Shan as a young girl, she was twelve while Professor Shan was in her mid-sixties. Professor Shan had intended on teaching Moyan English, she did so by reading her English novels, like Charles Dickens. Along with slowly teaching Moyan to read English, she also taught Moyan to have a bleak outlook on life. Professor Shan taught Moyan that it is better to be alone than to surround oneself with people. Pr...\n" 
                           +"\n" 
                           +"... middle of paper ...\n" 
                           +"\n" 
                           +"...rson that has come into [her] life” (Li 79). Acts of kindness make a positive imprint on a person, even a person whose life has been as melancholy as Moyans, “a random act of kindness that will continue living on in [my] memory” (Li 80).\n" 
                           +"Overall, the novel Gold Boy, Emerald Girl is filled with sorrow and regret, with the novella “Kindness” showing a complacent tone. But despite all the sorrow and loss in this novella, a ray of hope can be found in Moyan’s words. She might say things about having no hope and being meant to be lonely, but with her words of kindness, it is shown that she is a believer that things can get better. Overall, even though this book is filled with sadness, it also shows that even amongst sadness, there can always be hope found in the kindness of others.".toUpperCase();       

    public static void main(String[] args)
    {
        // uppercase instances for each testing sample
        Uppercase letItGo = new Uppercase();
        Uppercase iOwed = new Uppercase();
        Uppercase randomActs = new Uppercase();

        // prints out test 
        System.out.println("Let it go: " + isCorrect(letItGoOut, "./src/main/java/Input sample files/let it go input sample.txt"));
        System.out.println("I owed: " + isCorrect(iOwedOut, "./src/main/java/Input sample files/i owed input sample.txt"));
        System.out.println("Random acts: " + isCorrect(randomActsOut, "./src/main/java/Input sample files/random acts input sample.txt"));
    }

    // prints out whether the file is not found, or does/does not match the string with the desired outputs
    public static String isCorrect(String correct, String path) 
    {
        Scanner scanner;
        String output = "";
         try 
         {
             scanner = new Scanner(new File(path));
         } 
         catch (FileNotFoundException ex) 
         {
             return "******* FILE NOT FOUND ******";
         }
         
        
        while(scanner.hasNextLine())
            output += scanner.nextLine();

        
        if(output.equals(correct))
            return "correct";
        
        return "INCORRECT";
    }
}



