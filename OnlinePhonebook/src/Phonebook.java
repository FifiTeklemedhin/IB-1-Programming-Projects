
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fifiteklemedhin
 */
public class Phonebook 
{
    /*
        TODO: search a name
        TODO: add a name and number
        TODO: maintain a file with all numbers
        TODO: parse file data into a data structure with very fast searching
    */
    private File phonebookFile;
    protected HashMap<String, String> contacts = new HashMap<String, String>();
    public Phonebook(String serverName)
    {
        phonebookFile = new File("./" + serverName + "-file.txt");
        parseFile();
    }
    
    public void add(String name, String number)
    {
        if(this.contacts.containsKey(name))
        {
            System.out.println("Contact already exists. Try again");
            return;
        }
        
        this.contacts.put(name, number);
        String contact = name + ": " + number;
        FileWriter writer;
        try
        {
           writer = new FileWriter(phonebookFile, true);
           writer.write(contact + "\n");
           writer.close();
        }
        catch(IOException e)
        {
            System.out.println("IO exception when instantiating to writer: ");
        }
    }
    
    public String get(String name)
    {
        if(this.contacts.containsKey(name))
            return name + ": " + this.contacts.get(name);
        return "Contact does not exist. Try again.";
    }
    
    public void parseFile() 
    {
        if(this.phonebookFile.toString().isEmpty())
            return;
        try
        {
            Scanner reader = new Scanner(this.phonebookFile);
            
            while(reader.hasNextLine())
            {
                String[] contactInfo = reader.nextLine().split(":");
                contacts.put(contactInfo[0], contactInfo[1]);
                
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Phonebook file does not exist");
        }
    }
}
