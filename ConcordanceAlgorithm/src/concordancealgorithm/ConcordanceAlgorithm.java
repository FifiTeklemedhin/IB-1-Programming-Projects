/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concordancealgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import APClasses.APConsole;

/**
 *
 * @author fifiteklemedhin
 */
public class ConcordanceAlgorithm 
{

    /**
     * @param args the command line arguments
     */
    static JFileChooser chooser = new JFileChooser();
    static File file;
    static String inputPath;
    static Scanner scanner;
    public static void main(String[] args)
    {
        Set<String> set = new LinkedHashSet<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        APConsole console = new APConsole("Concordance Algorithm");
        
        chooser = chooseFile();
        validateInput();
        
        
        while(scanner.hasNextLine())
        {
            Scanner reader = new Scanner(scanner.nextLine());
            while(reader.hasNext())
            {
                String curr = reader.next();
                
                if(map.get(curr) != null)
                    map.put(curr, map.get(curr) + 1);
                else
                    map.put(curr, 1);
            }
        }
        console.println("scanned");
        
        for(String s : map.keySet())
        {
            console.println(s + ": " + map.get(s));
        }
        
        console.println("finished");
      
    }
    public static JFileChooser chooseFile()
    {
        JOptionPane.showMessageDialog(null,"Give me a text input and and I will\n"
                                    + " determine unique words and their frequencies");
        chooser.showOpenDialog(null);
        return chooser;
    }
    public static void validateInput()
    {
        // loops until the input file is chosen
        while(true)
        {
            try
            {
                inputPath = chooser.getSelectedFile().getAbsolutePath();
                break;
            }
            catch(NullPointerException e)
            {
                JOptionPane.showMessageDialog(null, "No file chosen. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if a file was chosen
                chooser = chooseFile();
                continue;
            }
        }
        
        inputPath = chooser.getSelectedFile().getAbsolutePath();
        file = new File(inputPath); 
        
        // makes you choose a new file while file not found
        while(true)
        {
            try
            {
                scanner = new Scanner(new FileReader(file));
                break;
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "File not found. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if it is a file
                chooser = chooseFile();
                continue;
            }
        }

    }
    
}
