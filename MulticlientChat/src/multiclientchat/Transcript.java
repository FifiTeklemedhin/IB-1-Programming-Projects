/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientchat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author fifiteklemedhin
 */
public class Transcript{
   private String str;
   private File file;
   public Transcript(){
      this.file = new File("./localhost-transcript.txt");
      getTranscript();
      
   }
	
   public void add (String more){
      str +=  more +"\n";
      writeToFile(more);
   }
	
   public String toString(){
      return str;
   }
   
   public void writeToFile(String newLine) 
    {
        FileWriter writer;
        try
        {
           writer = new FileWriter(this.file, true);
           writer.write(newLine+"\n");
           writer.close();
        }
        catch(IOException e)
        {
            System.out.println("io exception when instantiating to writer: ");
        }
    }
   public void getTranscript()
    {
        Scanner reader;
        try
        {
            reader = new Scanner(this.file);
            while(reader.hasNextLine())
                str += reader.nextLine() + "\n";
        } catch (FileNotFoundException ex) 
        {
            System.out.println("no file found to read -- print transcript");
            return;
        }
    }
}
