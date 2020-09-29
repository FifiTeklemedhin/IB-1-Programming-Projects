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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Therapist 
{
    private Set<String> hedgeSet; //set of hedges
    private Set<String> qualifierSet; //set of qualifiers
    private Set<String> patientHistory; // set of user inputs
    private Map<String, String> replacementMap; //the map of replacement words
    private File userHistory;
    private File userTranscript;
    private String transcript = "";
    private String historyStr = "";
    private String username = "";
    
    public Therapist(String username)
    {
        this.username = username;
        
        patientHistory = new HashSet<String>();
        
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
        
        this.userHistory = new File("./Patient Input Histories/" + this.username.toLowerCase() + "-history.txt");
        this.userTranscript = new File("./Patient Transcripts/" +  this.username.toLowerCase() + "-transcript.txt");
    }
 
    
    public void writeToFile(File file, String newLine) 
    {
        FileWriter writer;
        try
        {
           writer = new FileWriter(file);
           writer.write(newLine);
           writer.close();
        }
        catch(IOException e)
        {
            System.out.println("io exception when instantiating to writer");
        }
    }
    public String greeting()
    {
        return "Hello!";
    }
    public String reply(String patientString) 
    {
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
        updateTranscript(patientString, reply);
        
        return reply;        
    }
    public void updateTranscript(String input, String output)
    {
   
        transcript += input + " --> " + output+ "\n";
        writeToFile(userTranscript, transcript);
        
        
    }
    public void printFiles()
    {
        Scanner reader;
        
        System.out.println("****************TRANSCRIPT*********************");
        try
        {
            reader = new Scanner(userTranscript);
        } catch (FileNotFoundException ex) 
        {
            System.out.println("no file found to read -- print transcript");
            return;
        }
        while(reader.hasNextLine())
            System.out.println(reader.nextLine());
        System.out.println("**********************************************");
        
        System.out.println("****************HISTORY***********************");

        try {
            reader = new Scanner(userHistory);
        } catch (FileNotFoundException ex) {
            System.out.println("no file found to read -- print history");
            return;
        }

        while(reader.hasNextLine())
            System.out.println(reader.nextLine());

        
        System.out.println("**********************************************");
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
        
        historyStr += latestInput.toLowerCase() + "\n";
        writeToFile(userHistory, historyStr);
                    
    }
    private String hedge(Set<String> hedgeSet)
    {
    
        return selectRandom(hedgeSet);
    }
    private String qualifier(Set<String> qualifierSet)
    {
        return selectRandom(qualifierSet);
    }
    private String changePerson(String str)
    {
        //Tokenize str
        Scanner tokens = new Scanner(str);
        String result = "";
        
        //Build the response from replacements of the tokens
        while(tokens.hasNext())
        {
            String keyWord = tokens.next();
            String replacement = findReplacement(keyWord);
            result = result + replacement + " ";
        }
        
        return result;
    }
    
    private String findReplacement(String keyWord)
    {
       
        keyWord = keyWord.toLowerCase();
        if(replacementMap.containsKey(keyWord))
            return (String) replacementMap.get(keyWord);
        
        else
            return keyWord;
    }
    
    private String selectRandom(Set<String> set)
    {
        
        int index =  randomInt(0, set.size() -1);
        Iterator<String> iter = set.iterator();
        for(int i = 0; i < index; i++)
            iter.next();
        return iter.next();
    }
    
    private int randomInt(int low, int high)
    {
         return (int) (low + Math.random() * (high - low + 1));
    }
}
