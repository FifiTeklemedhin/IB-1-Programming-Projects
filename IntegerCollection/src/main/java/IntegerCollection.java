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

public class IntegerCollection 
{
    // @TODO: validate that all inputs in the file are integers
    // @TODO: don't crash if a file is not chosen
    public static void main(String[] args) throws FileNotFoundException
    {
        APConsole console = new APConsole("Integer Collection");
        Set<Integer> parsedInts = new HashSet<Integer>();
        
        
        // ************************* Input Selector *************************
        JButton inputOpen = new JButton();
        JFileChooser inputChooser = new JFileChooser();
        JOptionPane notifier = new JOptionPane();
        
        File inputFile = new File("");
        Scanner reader = new Scanner("");

        String inputPath = "";

        JOptionPane.showMessageDialog(inputOpen, "Select your input file");
        inputChooser = chooseFile(inputOpen, inputChooser);
        
        
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
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        
        inputPath = inputChooser.getSelectedFile().getAbsolutePath();
        inputFile = new File(inputPath); 
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
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        reader = new Scanner(new FileReader(inputFile));
        // prints out what user clicked on

        console.println("Input File: " + inputPath);
        
        // ************************* Delimiting String and Filling Set *************************

        while(reader.hasNextLine())
        {
            String currentLine = reader.nextLine();
            String delimitedLine[] = currentLine.split(" ");
            
            for(String s: delimitedLine)
                parsedInts.add(Integer.valueOf(s));
        }
        
        // ************************* Traversing Set *************************
        for(Integer i: parsedInts)
            console.print(i + "\n");
    }
    public static JFileChooser chooseFile(JButton inputOpen, JFileChooser inputChooser)
    {
        // need starting directory for file chooser "." sets it to the project location 
        inputChooser.setCurrentDirectory(new java.io.File("."));
        inputChooser.setDialogTitle("Select Input File");
        
        //setting it so that file chooser will only show directories
        inputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
       
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        inputChooser.showDialog(inputOpen, "Select Input File");
        
        return inputChooser;
    }
    
    public static void validateFile(JButton inputOpen, JFileChooser inputChooser, String inputPath, File inputFile, Scanner reader)
    {
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
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        
        inputPath = inputChooser.getSelectedFile().getAbsolutePath();
        inputFile = new File(inputPath); 
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
                inputChooser = chooseFile(inputOpen, inputChooser);
                continue;
            }
        }
        
    }
}
