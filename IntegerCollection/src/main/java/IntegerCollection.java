/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fifiteklemedhin
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import APClasses.APConsole;
import javax.swing.JFrame;

public class IntegerCollection 
{
    // @TODO: test
    
    static APConsole console = new APConsole("Integer Collection");
    static Set<Integer> parsedInts = new LinkedHashSet<Integer>();
    
    static JButton inputOpen = new JButton();
    static JFileChooser inputChooser = new JFileChooser();
    static JOptionPane notifier = new JOptionPane();

    static File inputFile = new File("");
    static Scanner reader;

    static String inputPath = "";
    
    public static void main(String[] args) throws FileNotFoundException
    {
        // introduction
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Give me an input file and I'll \n"
                                           + "print the unique integers in the file \n"
                                           + "to the console in order of appearance");
        
        // choosing file, validating, processing data
        validate();
        
        // traversing and printing set
        console.println("\n\n*************** Results ***************");
        for(Integer i: parsedInts)
            console.print(i + "\n");
    }
    
    
    
    public static JFileChooser chooseFile(JButton inputOpen, JFileChooser inputChooser)
    {
        JOptionPane.showMessageDialog(inputOpen, "Select your input file");
        // need starting directory for file chooser "." sets it to the project location 
        inputChooser = new JFileChooser();
        inputChooser.setCurrentDirectory(new java.io.File("./src/main/Random Integer Samples"));
        inputChooser.setDialogTitle("Select Input File");
        
        //setting it so that file chooser will only show directories
        inputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
       
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        inputChooser.showDialog(inputOpen, "Select Input File");
        
        return inputChooser;
    }
    
    public static String validateFile()
    {
        // loops until the input file is chosen
        while(true)
        {
            try
            {
                inputPath = inputChooser.getSelectedFile().getAbsolutePath();
                break;
            }
            catch(NullPointerException e)
            {
                JOptionPane.showMessageDialog(inputOpen, "No file chosen. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if a file was chosen
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        
        inputPath = inputChooser.getSelectedFile().getAbsolutePath();
        inputFile = new File(inputPath); 
        
        // makes you choose a new file while file not found
        while(true)
        {
            try
            {
                reader = new Scanner(new FileReader(inputFile));
                break;
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(inputOpen, "File not found. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if it is a file
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        
         /*
          path of the file is a string, and therefore immutable, so the method returns a string
          that inputPath can assign itself to in the main method
        */
        return inputPath;
        
    }
    
    public static void validate()
    {
        
        console.println("choosing file...");
        inputChooser = chooseFile(inputOpen, inputChooser);
        console.println("validating file...");
        inputPath = validateFile();
        console.println("Input File Path: " + inputPath);
        
        console.println("parsing file inputs...");
        
        while(reader.hasNextLine())
        {
            // splits the string with a space as its delimiter, gathering all ints
            String currentLine = reader.nextLine();
            String delimitedLine[] = currentLine.split(" ");
            
            for(String s: delimitedLine)
            {
                try
                {
                    parsedInts.add(Integer.valueOf(s));
                }
                
                catch(NumberFormatException e)
                {
                    // if a line has a char in it, then the set is wiped and the user has to repeat the process
                    parsedInts.removeAll(parsedInts);
                    JOptionPane.showMessageDialog(inputOpen, "File has non-integer values. Please input another file.");
                    console.print("\n\n");
                    validate();
                    break;
                }
            }
              
                
        }
    }
}
