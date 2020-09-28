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
/* Case Study 14.2: Therapist.java
1) This class emulates a nondirective psychotherapist.
2) The major method, reply, accepts user statements and generates
   a nondirective reply.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Therapist implements Serializable {
   private static final long serialVersionUID = 1L;
    
   private Set<String> hedgeSet;                 // The set of hedges
   private Set<String>  qualifierSet;            // The set of qualifiers
   private Map<String, String>   replacementMap; // The map of replacement words
   private Set<String> patientHistory = new HashSet<String>(); // set of user inputs
   private int responseInterval = (int) (Math.random() * (7 - 3 + 1));
   private int responses = 0;
   private File file;
   private String username;
   public Therapist(String username) {
      hedgeSet = new HashSet<String>();
      hedgeSet.add("Please tell me more");
      hedgeSet.add("Many of my patients tell me the same thing");
      hedgeSet.add("It is getting late, maybe we had better quit");
      qualifierSet = new HashSet<String>();
      qualifierSet.add("Why do you say that ");
      qualifierSet.add("You seem to think that ");
      qualifierSet.add("So, you are concerned that ");
      replacementMap = new HashMap<String, String>();
      replacementMap.put("i", "you");
      replacementMap.put("me", "you");
      replacementMap.put("my", "your");
      replacementMap.put("am", "are");
      
      
      this.username = username;
      this.file = new File("./TherapistInfo" + this.username + ".txt");
   }

   public String greeting(){
      return "Good day, "+ username + ". What is your problem?";
   }
   @Override
   public String toString()
   {
       return "Username: " + this.username + "\n" + patientHistory;
   }
   public String reply(String patientString){
   /*
        Replies to the patient's statement with either a hedge or
        a string consisting of a qualifier concatenated to a trasnformed
        version of the patient's statement.
        
        Preconditions: none
        Postconditions: returns a reply
        */
   
        if(responses >= responseInterval)
            {
                if(canReferBack())
                {   
                   responses = 0;
                   responseInterval = (int) (Math.random() * (7 - 3 + 1));
                   return referBack();
                }
                responseInterval = (int) (Math.random() * (5 - 2 + 1));
            }
        
        String reply = "";
        int choice = randomInt(1,3); //generates a random number, makes getting a hedge a 1/3 chance
        
        // If the patient says nothing, then encourage him.
        if(patientString.trim().equals(""))
        {
            updatePatientHistory(patientString);
            return "Take your time. Some things are difficult to talk about.";
        }
           
        //else reply with a hedge or a qualified response
        if(choice == 1)
            reply = hedge(hedgeSet);
        else if(choice == 2 || choice == 3)
            reply = qualifier(qualifierSet) + changePerson(patientString);
      
        updatePatientHistory(patientString);
        
        return reply;        
   }

   private String hedge(Set<String> hedgeSet){
   // Selects a hedge at random
   //  Preconditions -- the hedge set has been initialized
   //  Postconditions -- returns a randomly selected hedge
      return selectRandom(hedgeSet);
   }

   private String qualifier(Set<String> qualifierSet){
   // Selects a qualifier at random
   //  Preconditions  -- the qualifier set has been initialized
   //  Postconditions -- returns a randomly selected qualifier
      return selectRandom(qualifierSet);
   }
 public boolean canReferBack()
    {
        return patientHistory.size() >= 3;
    }
    public String referBack()
    {
        return "Referring back, I remember you said " + changePerson(selectRandom(patientHistory)) + ". Lets go back to that";
    }
    // adds the patients latest reply to their history if they haven't said it before
    private void updatePatientHistory(String latestInput)
    {
        if(this.patientHistory.contains(latestInput.toLowerCase()))
            return;
        this.patientHistory.add(latestInput.toLowerCase());
                    
    }
   private String changePerson(String str){
   // Returns a string created by swapping i, me, etc. for you, your, etc.
   // in the string str
   //  Preconditions  -- none
   //  Postconditions -- returns the created string
   // Tokenize str
      Scanner tokens = new Scanner(str);       
      String result = "";                     // Create a response string
      // Build the response from replacements of the tokens
      while (tokens.hasNext()){
         String keyWord = tokens.next();
         String replacement = findReplacement(keyWord);
         result = result + replacement + " ";
      }
      return result;
   }

   private String findReplacement(String keyWord){
   // Returns the value associated with the keyword or the keyword itself
   // if the keyword is not in the map.
   //  Preconditions  -- the replacement map has been initialized
   //  Postconditions -- returns the replacement
      keyWord = keyWord.toLowerCase();
      if (replacementMap.containsKey(keyWord))
         return (String) replacementMap.get(keyWord);
      else
         return keyWord;
   }

   private String selectRandom(Set<String> set){
   // Selects an entry at random from the set
   //  Preconditions  -- the set is not empty
   //  Postconditions -- returns the random entry
      int index = randomInt(0, set.size() - 1);
      Iterator<String> iter = set.iterator();
      for (int i = 0; i < index; i++)
         iter.next();
      return iter.next();
   }

   private int randomInt(int low, int high){
   // Generate a random number between low and high
   //  Preconditions  -- low <= high
   //  Postconditions -- returns the random number
      return (int) (low + Math.random() * (high - low  + 1));
   }
   
   public void serialize() {
 
       try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(this);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			oi.readObject();

			System.out.println(this.toString());

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
