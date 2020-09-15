/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uppercase;

/**
 *
 * @author fifiteklemedhin
 */
import APClasses.APConsole;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Uppercase 
{
    // @TODO: test
    
    static JButton inputOpen = new JButton();
    static JButton outputOpen = new JButton();
    static JFileChooser inputChooser = new JFileChooser();
    static JFileChooser outputChooser = new JFileChooser();
    static JOptionPane notifier = new JOptionPane();

    static File inputFile;
    static File outputFile;
    static Scanner reader;
    static FileWriter writer;

    static String inputFileName;
    static String inputPath;
    static String outputPath; 
    static ArrayList<String> outputLines;
    
    public Uppercase()
    {
        String[] args = {""};
        try {
            main(args);
        } 
        catch (IOException ex)
        {
            System.out.println("\"******* I/O EXCEPTION ******");
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Give me an input and output file,\n"
                                           + "and I'll convert the input's content to uppercase,\n"
                                           + "then print the results in the output file");
        
        // Reading and Validating Files
        inputChooser = chooseFile(inputOpen, inputChooser, "input");
        inputPath = validateInput();
        
        outputChooser = chooseFile(outputOpen, outputChooser, "output");
        outputPath = validateOutput();
        
        // Reading Input
        outputLines = new ArrayList<String>();
        while(reader.hasNextLine())
            outputLines.add(reader.nextLine());

        // Writing Output
        for(int i = 0; i < outputLines.size(); i++)
        {  
            writer.write(outputLines.get(i).toUpperCase() + "\n");
        }
         
        writer.close();
    } 
    
    public static JFileChooser chooseFile(JButton open, JFileChooser chooser, String type)
    {
        JOptionPane.showMessageDialog(inputOpen, "Select your " + type + " file");
        // need starting directory for file chooser "." sets it to the project location 
        inputChooser = new JFileChooser();
        //inputChooser.setCurrentDirectory(new java.io.File("./src/main/java/"));
        inputChooser.setDialogTitle("Select " + type + " file");
        
        //setting it so that file chooser will only show directories
        inputChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
       
        // need a button component to open it up, even though it doesn'tactually need to be pressed
        inputChooser.showDialog(inputOpen, "Select your " + type + " file");
        
        return inputChooser;
    }
    
    public static String validateInput()
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
                inputChooser = chooseFile(inputOpen, inputChooser, "input");
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
                inputChooser = chooseFile(inputOpen, inputChooser, "input");
                continue;
            }
        }
        
        /*
          path of the file is a string, and therefore immutable, so the method returns a string
          that inputPath can assign itself to in the main method
        */
        return inputPath;
    }
    public static String validateOutput()
    {
         // loops until the ouput file is chosen
        while(true)
        {
            try
            {
                outputPath = outputChooser.getSelectedFile().getAbsolutePath();
                break;
            }
            catch(NullPointerException e)
            {
                JOptionPane.showMessageDialog(outputOpen, "No file chosen. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if a file was chosen
                outputChooser = chooseFile(outputOpen, outputChooser, "output");
                continue;
            }
        }
        
        outputPath = outputChooser.getSelectedFile().getAbsolutePath();
        outputFile = new File(outputPath); 
        
        // makes you choose a new file while file not found
         while(true)
        {
            try
            {
                writer = new FileWriter(outputFile);
                break;
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(outputOpen, "Can't write into file. Select a valid input file");
                // goes back to choosing file. every time, input file changes before checking if it is a file
                outputChooser = chooseFile(outputOpen, outputChooser, "output");
                continue;
            }
        }
        
         /*
          path of the file is a string, and therefore immutable, so the method returns a string
          that outputPath can assign itself to in the main method
        */
        return outputPath;
        
    }
}